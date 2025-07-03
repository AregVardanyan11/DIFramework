package container;

import annotations.Inject;

import java.lang.reflect.Field;

/**
 * A utility class responsible for performing field-based dependency injection.
 * The {@code FieldInjector} class scans all declared fields of the given instance
 * and resolves dependencies annotated with {@code @Inject}. The resolved
 * dependencies are provided by the {@code BeanFactory}.
 *
 * This class relies on reflection to access and inject dependencies, making all
 * annotated fields accessible regardless of their visibility modifiers.
 *
 * Typical usage of this method occurs during the initialization of beans via
 * the {@code BeanFactory}.
 *
 * Exceptions:
 * - Throws a {@link RuntimeException} if field injection fails for any reason,
 *   such as no available bean matching the field type or access issues.
 */
public class FieldInjector {

    public static void injectFields(Object instance, BeanFactory factory) {
        Field[] fields = instance.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                try {
                    field.setAccessible(true);
                    Object dependency = factory.getBean(field.getType());
                    field.set(instance, dependency);
                } catch (Exception e) {
                    throw new RuntimeException("Field injection failed for: " + field.getName(), e);
                }
            }
        }
    }
}
