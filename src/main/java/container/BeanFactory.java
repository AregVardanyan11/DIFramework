package container;

import enums.ScopeType;

import java.util.HashMap;
import java.util.Map;

/**
 * The BeanFactory is responsible for managing the creation and lifecycle of beans
 * within the dependency injection framework. It acts as the central component
 * from which beans can be retrieved based on their type. The factory delegates
 * the responsibility of retrieving bean metadata to a BeanRegistry and handles
 * the instantiation, field injection, and caching of beans.
 *
 * Key functionalities include:
 *
 * - Retrieving bean definitions from the BeanRegistry.
 * - Handling both singleton and prototype bean scopes.
 * - Creating bean instances using constructor injection via the ConstructorInjector class.
 * - Performing field injection to resolve dependencies within a bean via the FieldInjector class.
 * - Caching singleton beans for reuse across requests.
 *
 * The lifecycle for bean retrieval is as follows:
 * 1. Query the BeanRegistry for a bean definition using the provided class type.
 * 2. If the bean is scoped as a singleton and exists in the cache, return it.
 * 3. Otherwise, create a new instance of the bean using constructor injection.
 * 4. Perform field injection for dependencies within the bean.
 * 5. Cache the newly created instance if it is scoped as a singleton.
 * 6. Return the created and initialized instance.
 *
 * Exceptions:
 * - Throws a RuntimeException if no bean definition is found for the requested class type.
 */
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

        if (def.getScope() == ScopeType.SINGLETON) {
            if (singletonCache.containsKey(clazz)) {
                return singletonCache.get(clazz);
            }
        }

        Object instance = ConstructorInjector.createWithConstructorInjection(def);
        FieldInjector.injectFields(instance, this);

        if (def.getScope() == ScopeType.SINGLETON) {
            singletonCache.put(clazz, instance);
        }

        return instance;
    }
}
