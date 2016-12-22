package com.expertsoft.core.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumberValidator implements ConstraintValidator<IntegerNumber, String> {

    private IntegerNumber number;

    @Override
    public void initialize(IntegerNumber number) {
        this.number = number;
    }

    @Override
    public boolean isValid(String param, ConstraintValidatorContext context) {
        try {
            int value = Integer.parseInt(param);
            return (number.min() <= value) && (value <= number.max()) ? true : false;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
