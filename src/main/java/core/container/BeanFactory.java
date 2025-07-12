package core.container;

import core.annotations.PostConstruct;
import core.enums.ScopeType;
import core.injection.ConstructorInjector;
import core.injection.FieldInjector;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class BeanFactory {

    private final BeanRegistry registry;
    private final Map<Class<?>, Object> singletonCache = new HashMap<>();

    public BeanFactory(BeanRegistry registry) {
        this.registry = registry;
    }

    public Object getBean(Class<?> clazz) {
        List<BeanDefinition> candidates = registry.getAllDefinitions().stream()
                .filter(def -> clazz.isAssignableFrom(def.getBeanClass()))
                .collect(Collectors.toList());

        if (candidates.isEmpty()) {
            throw new RuntimeException("No bean definition found for: " + clazz.getName());
        }

        BeanDefinition def;

        if (candidates.size() == 1) {
            def = candidates.get(0);
        } else {
            // Look for @Primary
            List<BeanDefinition> primaries = candidates.stream()
                    .filter(BeanDefinition::isPrimary)
                    .collect(Collectors.toList());

            if (primaries.size() == 1) {
                def = primaries.get(0);
            } else if (primaries.isEmpty()) {
                throw new RuntimeException("Multiple beans found for type " + clazz.getName() +
                        ", but none marked as @Primary");
            } else {
                throw new RuntimeException("Multiple @Primary beans found for type " + clazz.getName());
            }
        }

        if (def.getScope() == ScopeType.SINGLETON && singletonCache.containsKey(def.getBeanClass())) {
            return singletonCache.get(def.getBeanClass());
        }

        Object instance;
        if (def.getFactoryMethod().isPresent()) {
            try {
                Object configInstance = def.getFactoryInstance()
                        .orElseThrow(() -> new RuntimeException("Missing @Configuration instance for factory method"));
                Method factoryMethod = def.getFactoryMethod().get();
                factoryMethod.setAccessible(true);
                instance = factoryMethod.invoke(configInstance);
            } catch (Exception e) {
                throw new RuntimeException("Failed to create bean via factory method", e);
            }
        } else {
            instance = ConstructorInjector.createWithConstructorInjection(def);
        }

        FieldInjector.injectFields(instance, this);

        runPostConstruct(instance);

        if (def.getScope() == ScopeType.SINGLETON) {
            singletonCache.put(def.getBeanClass(), instance);
        }

        return instance;
    }

    private void runPostConstruct(Object instance) {
        for (Method method : instance.getClass().getDeclaredMethods()) {
            if (method.isAnnotationPresent(PostConstruct.class)) {
                try {
                    method.setAccessible(true);
                    method.invoke(instance);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to execute @PostConstruct method: " + method.getName(), e);
                }
            }
        }
    }
}
