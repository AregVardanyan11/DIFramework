package core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that an annotated class is a configuration class within a
 * dependency injection framework. Classes annotated with {@code @Configuration}
 * typically define one or more {@code @Bean}-annotated methods that produce
 * managed objects (beans) to be registered in the application context.
 *
 * The {@code @Configuration} annotation denotes that the class serves as a
 * source of bean definitions, allowing the application context to detect
 * and process the configured beans at runtime.
 *
 * This annotation is commonly used for programmatically configuring beans
 * and their dependencies, often as an alternative or complement to XML
 * configuration files.
 *
 * Retention: Runtime - the annotation metadata is retained in the class
 * file and available during runtime for reflection-based processing.
 *
 * Target: Applicable only to class-level elements (types).
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Configuration {}