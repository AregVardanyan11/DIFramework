package core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that a specific implementation or definition should be used when multiple
 * candidates match during dependency injection. The {@code @Qualifier} annotation
 * provides a means to differentiate between multiple beans of the same type by associating
 * a qualifier value with the required bean.
 *
 * This annotation is typically used in conjunction with {@code @Inject}, {@code @Bean}, or
 * other dependency injection mechanism annotations to explicitly specify which bean
 * should be injected or instantiated.
 *
 * The {@code value} element is required to assign a unique identifier to the corresponding
 * bean. This value can then be used in injection points to resolve ambiguities.
 *
 * Retention: Runtime - the annotation metadata is available during runtime for reflective
 * processing by the dependency injection framework.
 *
 * Target: Applicable to fields, parameters, methods, and types. For fields and parameters,
 * it specifies the qualified bean to be injected. For methods and types, it defines the
 * qualifier applied to the resulting bean.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD, ElementType.TYPE})
public @interface Qualifier {
    String value();
}