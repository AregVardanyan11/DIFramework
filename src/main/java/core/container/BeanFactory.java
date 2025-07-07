package core.container;

import core.annotations.PostConstruct;
import core.enums.ScopeType;
import core.injection.ConstructorInjector;
import core.injection.FieldInjector;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


public class BeanFactory {

    private final BeanRegistry registry;
    private final Map<Class<?>, Object> singletonCache = new HashMap<>();

    public BeanFactory(BeanRegistry registry) {
        this.registry = registry;
    }

    public Object getBean(Class<?> clazz) {
        BeanDefinition def = registry.getByType(clazz);
        if (def == null) {
            throw new RuntimeException("No bean definition found for: " + clazz);
        }

        if (def.getScope() == ScopeType.SINGLETON && singletonCache.containsKey(clazz)) {
            return singletonCache.get(clazz);
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
            singletonCache.put(clazz, instance);
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
