package annotations;

import enums.ScopeType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies the scope of a component or bean within a dependency injection framework.
 * The {@code @Scope} annotation is used to define the lifecycle and visibility of a bean,
 * determining how and when the framework creates and manages instances of the annotated type.
 *
 * By default, the scope is set to {@code ScopeType.SINGLETON}, which means that a single
 * instance of the bean will be created and shared across the application context. Other
 * scope types, such as {@code ScopeType.PROTOTYPE}, can be specified to customize the
 * component's lifecycle behavior.
 *
 * Retention: Runtime - the annotation metadata is available during runtime for
 * reflection-based processing by the dependency injection framework.
 *
 * Target: Applicable to type-level elements (classes or interfaces).
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Scope {
    ScopeType value() default ScopeType.SINGLETON;
}