package core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a constructor, field, or method to be considered for automatic dependency injection
 * by a dependency injection framework.
 *
 * The {@code @Inject} annotation indicates that the annotated member requires an instance
 * of a specific dependency, which is managed and provided by the dependency injection framework.
 *
 * Supported targets:
 * - Constructor: Indicates that the constructor should be used to initialize a class with its
 *   required dependencies.
 * - Field: Marks a field for injection, allowing the framework to set its value during initialization.
 * - Method: Identifies a method to be invoked by the framework to supply dependencies, typically
 *   for setter-based injection or custom initialization logic.
 *
 * Retention: Runtime - the annotation metadata is retained in the compiled class file and is
 * available during runtime for reflection-based processing by a dependency injection framework.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD})
public @interface Inject {}
