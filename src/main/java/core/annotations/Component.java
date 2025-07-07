package core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that an annotated class is a "component" managed by a dependency injection framework.
 * Applying this annotation to a class denotes it as a candidate for automatic detection
 * and registration in the application context as a managed bean.
 *
 * This annotation typically works in conjunction with a classpath scanning mechanism to discover
 * and manage annotated classes.
 *
 * Classes annotated with {@link Component} may also make use of other related annotations such
 * as {@link Inject}, {@link Scope}, {@link Lazy}, and {@link Primary} to modify or refine the
 * behavior of how the component is managed.
 *
 * The {@link Component} annotation is similar in purpose to the {@link Configuration} annotation,
 * with the latter focusing more on defining bean creation methods.
 *
 * Applicable only to class-level elements (types).
 *
 * Retention: Runtime - the annotation metadata is available during runtime for reflection-based
 * processing by the dependency injection framework.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Component {
}
