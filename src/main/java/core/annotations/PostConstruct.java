package core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the annotated method should be executed after the dependency
 * injection framework has completed processing the bean. The {@code @PostConstruct}
 * annotation is used to specify an initialization method that should run only once
 * after the bean's dependencies have been injected and before it is made available for use.
 *
 * This annotation is typically used for post-initialization activities, such as
 * custom setup logic that is required before the bean is fully operational within
 * the application context.
 *
 * Retention: Runtime - the annotation metadata is retained in the class file and is
 * available during runtime for reflection-based processing.
 *
 * Target: Applicable to method-level elements. The method must be void, must not
 * accept any parameters, and must not throw any checked exceptions.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PostConstruct {}