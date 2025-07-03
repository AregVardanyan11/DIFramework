package enums;

/**
 * Defines the scope for a component or bean managed by a dependency injection
 * framework. The scope determines the lifecycle and instantiation behavior of
 * the managed object.
 *
 * Types of scopes:
 * - SINGLETON: A single instance of the component or bean is created and shared
 *   throughout the lifecycle of the application context. This is the default scope
 *   in most dependency injection frameworks.
 * - PROTOTYPE: A new instance of the component or bean is created each time it
 *   is requested from the application context.
 *
 * These scope types are typically used in conjunction with dependency injection
 * configuration mechanisms such as annotations or framework APIs.
 */
public enum ScopeType {
    SINGLETON,
    PROTOTYPE
}
