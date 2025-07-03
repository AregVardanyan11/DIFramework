package container;

import enums.ScopeType;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * Represents the definition of a bean in the context of a dependency injection framework.
 * This class contains metadata about the bean, including its class type, scope, instantiation
 * behavior, and additional attributes used to manage and retrieve the bean.
 *
 * Key aspects of this definition include:
 *
 * - The class type of the bean that will be managed by the container.
 * - The scope of the bean (e.g., singleton or prototype).
 * - Whether the bean is lazily initialized or eagerly instantiated.
 * - Whether the bean is marked as primary, which affects how it is used during dependency injection.
 * - A qualifier that provides a unique identifier for the bean, enabling named injections.
 * - An optional factory method, which specifies how the bean is created if it is associated
 *   with a configuration method annotated in a Spring-style @Bean method.
 *
 * This class is typically constructed during the scanning or configuration phase of a dependency
 * injection container and is used internally to manage bean lifecycle and assembly of object graphs.
 */
public class BeanDefinition {
    private final Class<?> beanClass;
    private final ScopeType scope;
    private final boolean lazy;
    private final boolean isPrimary;
    private final String qualifier;
    private final Optional<Method> factoryMethod; // used for @Bean methods

    public BeanDefinition(Class<?> beanClass,
                          ScopeType scope,
                          boolean lazy,
                          boolean isPrimary,
                          String qualifier,
                          Optional<Method> factoryMethod) {
        this.beanClass = beanClass;
        this.scope = scope;
        this.lazy = lazy;
        this.isPrimary = isPrimary;
        this.qualifier = qualifier;
        this.factoryMethod = factoryMethod;
    }

    public Class<?> getBeanClass() { return beanClass; }
    public ScopeType getScope() { return scope; }
    public boolean isLazy() { return lazy; }
    public boolean isPrimary() { return isPrimary; }
    public String getQualifier() { return qualifier; }
    public Optional<Method> getFactoryMethod() { return factoryMethod; }
}
