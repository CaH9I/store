package com.expertsoft.core.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = NumberValidator.class)
@Target(FIELD)
@Retention(RUNTIME)
public @interface IntegerNumber {

    int min() default Integer.MIN_VALUE;

    int max() default Integer.MAX_VALUE;

    String message() default "The string is not an integer number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
