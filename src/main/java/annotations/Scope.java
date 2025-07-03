package annotations;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Scope {
    ScopeType value() default ScopeType.SINGLETON;
}