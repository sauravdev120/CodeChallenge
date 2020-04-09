package com.example.demo.validator;

import java.util.Arrays;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TransmissionTypeValidator implements ConstraintValidator<TransmissionType, String> {

    List<String> types = Arrays.asList("MANUAL", "AUTO");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return types.contains(value);
    }
}
