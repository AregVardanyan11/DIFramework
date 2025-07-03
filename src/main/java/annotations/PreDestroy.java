package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that a method should be invoked prior to the destruction of a bean
 * by the dependency injection framework. The {@code @PreDestroy} annotation
 * is typically used for releasing resources or performing cleanup tasks, such
 * as closing connections or freeing up memory, before the bean instance is
 * discarded.
 *
 * Methods annotated with {@code @PreDestroy} should not accept any parameters
 * and must return void. These methods are automatically called by the framework
 * during the destruction phase of the bean lifecycle.
 *
 * Retention: Runtime - the annotation metadata is retained in the class file
 * and is available during runtime for reflection-based processing.
 *
 * Target: Applicable only to methods.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PreDestroy {
}
