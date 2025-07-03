package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that a field or parameter is considered optional for dependency injection
 * by the dependency injection framework.
 *
 * The {@code @OptionalInject} annotation signals that the annotated field or parameter
 * should be injected if a suitable dependency is available, but it will not cause
 * initialization to fail if no qualifying dependency is found. This is particularly useful
 * in scenarios where the absence of certain beans or components is acceptable or optional.
 *
 * Retention: Runtime - the annotation metadata is retained in the compiled class file
 * and available during runtime for reflective processing by the dependency injection
 * framework.
 *
 * Supported targets:
 * - Field: Marks a field for optional injection, allowing the injection framework to
 *   attempt to set its value only if a suitable dependency is available.
 * - Parameter: Marks a constructor or method parameter as optional, enabling injection
 *   of the parameter only if a qualifying dependency exists.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
public @interface OptionalInject {}