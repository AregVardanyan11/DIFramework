package core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a class as a primary candidate for dependency injection when multiple beans
 * of the same type are available. The {@code @Primary} annotation indicates that
 * the annotated type should be given preference during dependency resolution,
 * simplifying the configuration in cases where multiple beans meet injection requirements.
 *
 * This annotation is typically used in conjunction with annotations such as {@link Component},
 * {@link Configuration}, or {@link Bean} to mark the primary implementation or configuration
 * for a given dependency.
 *
 * Retention: Runtime - the annotation metadata is retained and accessible during runtime
 * for reflection-based processing by the dependency injection framework.
 *
 * Target: Applicable to class-level elements.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Primary {}