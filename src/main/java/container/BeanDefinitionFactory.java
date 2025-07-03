package container;

import annotations.*;
import enums.ScopeType;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * Factory class for creating {@link BeanDefinition} instances based on class-level
 * or method-level metadata within a dependency injection framework.
 *
 * This factory provides methods to generate metadata representations of beans
 * from classes annotated with {@link Component} or {@link Configuration} and
 * from methods annotated with {@link Bean}.
 *
 * The resulting {@link BeanDefinition} encapsulates information about the bean,
 * such as its type, scope, lazy initialization, primary designation, qualifier,
 * and, if applicable, the method from which the bean is constructed.
 *
 * Responsibilities of this factory include:
 * - Validating annotations on classes or methods to ensure they meet the
 *   requirements for bean registration.
 * - Extracting metadata from annotations such as {@link Scope}, {@link Lazy},
 *   {@link Primary}, or {@link Qualifier}.
 * - Constructing {@link BeanDefinition} instances with the appropriate metadata.
 *
 * This class is typically used during the initialization phase of a dependency
 * injection container, supporting processes like classpath scanning and
 * configuration-based bean registration.
 */
public class BeanDefinitionFactory {

    public static BeanDefinition fromClass(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(Component.class) && !clazz.isAnnotationPresent(Configuration.class)) {
            throw new IllegalArgumentException("Class " + clazz.getName() + " must be annotated with @Component or @Configuration");
        }

        ScopeType scope = ScopeType.SINGLETON; // default
        if (clazz.isAnnotationPresent(Scope.class)) {
            scope = clazz.getAnnotation(Scope.class).value();
        }

        boolean lazy = clazz.isAnnotationPresent(Lazy.class);

        boolean isPrimary = clazz.isAnnotationPresent(Primary.class);

        String qualifier = "";
        if (clazz.isAnnotationPresent(Qualifier.class)) {
            qualifier = clazz.getAnnotation(Qualifier.class).value();
        }

        return new BeanDefinition(
                clazz,
                scope,
                lazy,
                isPrimary,
                qualifier,
                Optional.empty()
        );
    }

    public static BeanDefinition fromBeanMethod(Class<?> configClass, Method method) {
        if (!method.isAnnotationPresent(Bean.class)) {
            throw new IllegalArgumentException("Method " + method.getName() + " is not annotated with @Bean");
        }

        ScopeType scope = ScopeType.SINGLETON;
        if (method.isAnnotationPresent(Scope.class)) {
            scope = method.getAnnotation(Scope.class).value();
        }

        boolean lazy = method.isAnnotationPresent(Lazy.class);
        boolean isPrimary = method.isAnnotationPresent(Primary.class);
        String qualifier = method.getName();
        if (method.isAnnotationPresent(Qualifier.class)) {
            qualifier = method.getAnnotation(Qualifier.class).value();
        }

        return new BeanDefinition(
                method.getReturnType(),
                scope,
                lazy,
                isPrimary,
                qualifier,
                Optional.of(method)
        );
    }
}
