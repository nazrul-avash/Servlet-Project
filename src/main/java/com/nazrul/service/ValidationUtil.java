package com.nazrul.service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Map;
import java.util.stream.Collectors;

public class ValidationUtil {
    private static  final ValidationUtil INSTANCE = new ValidationUtil();
    private final Validator validator;
    private ValidationUtil(){
        var validationFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validationFactory.getValidator();
    }
    public static ValidationUtil getInstance(){
        return INSTANCE;
    }
    public <T>Map<String, String> validate(T object){
        var violations = validator.validate(object);
        return violations.stream().collect(Collectors.toMap(
                violation->violation.getPropertyPath().toString(),
                ConstraintViolation::getMessage,
                (errorMessage1,errorMessage2)->errorMessage1+"<br/>"+errorMessage2));
    }
}
