package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that a method produces a bean to be managed by the dependency injection framework.
 * Methods annotated with {@code @Bean} typically reside within classes annotated with
 * {@code @Configuration}, and the returned object is registered in the application context.
 *
 * The lifecycle and scope of the bean can be further influenced by other annotations or
 * framework configurations.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Bean {}