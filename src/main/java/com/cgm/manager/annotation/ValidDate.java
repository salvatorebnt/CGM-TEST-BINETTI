package com.cgm.manager.annotation;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Validate that the annotated string is in input Date format
 * @author salvatore.binetti
 */

@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Constraint(validatedBy = ValidDateValidator.class)
public @interface ValidDate {
    String message() default "invalid date";
    String format() default "dd-MM-yyyy";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    boolean optional() default false;
}