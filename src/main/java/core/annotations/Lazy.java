package core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that a component or bean should be lazily initialized by the dependency
 * injection framework. Lazy initialization defers the creation of the instance until
 * it is explicitly requested, rather than during the application context startup.
 *
 * The {@code @Lazy} annotation is typically used to optimize resource usage during
 * application startup by avoiding the initialization of beans that may never be used.
 * This can improve application startup time, especially in large applications with multiple
 * dependencies.
 *
 * Can be applied to:
 * - Classes: When applied at the class level, the entire component is lazily initialized.
 * - Methods: When applied to a method annotated with {@link Bean}, the returned bean
 *   instance is lazily initialized.
 *
 * Usage of {@code @Lazy} is often combined with annotations like {@link Component},
 * {@link Configuration}, or {@link Scope} to control the lifecycle and initialization
 * behavior of components or beans.
 *
 * Retention: Runtime - the annotation metadata is retained in the class file and available
 * during runtime for reflective processing by the dependency injection framework.
 *
 * Target: Applicable to class-level elements and methods.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Lazy {}