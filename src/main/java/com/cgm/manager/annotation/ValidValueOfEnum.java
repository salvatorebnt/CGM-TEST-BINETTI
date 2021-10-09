package com.cgm.manager.annotation;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;

/**
 * Validate that the annotated string is in yyyy-MM-dd'T'HH:mm:ss.SSSZ Date format
 */

@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = ValidEnumValidator.class)
public @interface ValidValueOfEnum {
    Class<? extends Enum<?>> enumClass();
    String message() default "Must be any of enum {enumClass}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}