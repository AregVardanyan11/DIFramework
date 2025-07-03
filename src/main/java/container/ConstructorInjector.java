package container;

import annotations.Inject;

import java.lang.reflect.Constructor;

/**
 * The ConstructorInjector class is responsible for creating instances of beans through
 * constructor injection. This class plays a crucial role in the dependency injection process
 * by identifying and utilizing constructors annotated with {@code @Inject} to provide
 * dependencies to the bean during instantiation.
 *
 * Primary responsibilities:
 * - Scans the bean's declared constructors to find one annotated with {@code @Inject}.
 * - Instantiates the bean using the annotated constructor if found.
 * - Falls back to using the default no-argument constructor if no annotated constructor is found.
 * - Ensures that constructors are accessible, even if they are declared as private.
 *
 * Error Handling:
 * - If instantiation fails due to constructor accessibility, argument mismatches,
 *   or other reflective operation exceptions, a {@code RuntimeException} is thrown with
 *   details of the failure, including the problematic bean class.
 *
 * Notes:
 * - The {@code createWithConstructorInjection} method is typically used by a dependency injection
 *   container to handle the creation of beans, ensuring their dependencies are resolved and injected
 *   at the time of instantiation.
 * - This class assumes that the {@link BeanDefinition} parameter provides the necessary metadata
 *   about the bean, including its type, from which the appropriate constructor can be determined.
 */
public class ConstructorInjector {

    public static Object createWithConstructorInjection(BeanDefinition def) {
        try {
            Constructor<?>[] constructors = def.getBeanClass().getDeclaredConstructors();
            for (Constructor<?> constructor : constructors) {
                if (constructor.isAnnotationPresent(Inject.class)) {
                    constructor.setAccessible(true);
                    return constructor.newInstance();
                }
            }

            Constructor<?> defaultConstructor = def.getBeanClass().getDeclaredConstructor();
            defaultConstructor.setAccessible(true);
            return defaultConstructor.newInstance();

        } catch (Exception e) {
            throw new RuntimeException("Failed to create bean: " + def.getBeanClass(), e);
        }
    }
}
