package container;

import java.util.*;

/**
 * The BeanRegistry class is responsible for maintaining a registry of bean definitions
 * within a dependency injection framework. It serves as the central repository for
 * managing metadata about beans and providing lookup capabilities for registered beans
 * by their type or qualifier.
 *
 * Responsibilities:
 * - Registering bean definitions associated with their respective class types.
 * - Allowing bean definitions to be queried by their class type.
 * - Providing optional lookup support for bean definitions using a qualifier,
 *   which is a unique name or identifier for the bean within the container.
 * - Retrieving all registered bean definitions for further processing.
 *
 * Key Features:
 * - Efficient lookup using both class type and qualifier.
 * - Internally uses two maps: one to store bean definitions by type and another to store
 *   definitions by their qualifier.
 *
 * Thread Safety:
 * This class is not thread-safe. Concurrent accesses to the registry for
 * registering or retrieving bean definitions must be externally synchronized.
 */
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

