package container;

import java.util.*;

public class BeanRegistry {
    private final Map<Class<?>, BeanDefinition> beanDefinitions = new HashMap<>();
    private final Map<String, BeanDefinition> qualifiers = new HashMap<>();

    public void register(Class<?> clazz, BeanDefinition definition) {
        beanDefinitions.put(clazz, definition);
        if (!definition.getQualifier().isEmpty()) {
            qualifiers.put(definition.getQualifier(), definition);
        }
    }

    public BeanDefinition getByType(Class<?> clazz) {
        return beanDefinitions.get(clazz);
    }

    public Optional<BeanDefinition> getByQualifier(String name) {
        return Optional.ofNullable(qualifiers.get(name));
    }

    public Collection<BeanDefinition> getAllDefinitions() {
        return beanDefinitions.values();
    }
}

