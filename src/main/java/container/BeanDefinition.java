package container;

import enums.ScopeType;

import java.lang.reflect.Method;
import java.util.Optional;

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
