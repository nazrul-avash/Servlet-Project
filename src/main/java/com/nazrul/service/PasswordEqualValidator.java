package com.nazrul.service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class PasswordEqualValidator implements ConstraintValidator<PasswordEqual,Object> {
    private String firstFieldName;
    private String secondFieldName;
    private String message;
    public void initialize(PasswordEqual constraint){
        firstFieldName = constraint.first();
        secondFieldName = constraint.second();
        message = constraint.message();
    }
    public boolean isValid(Object value, ConstraintValidatorContext context){
        boolean valid = true;
        try{
            final Object firstObj = getValue(value, firstFieldName);
            final Object secondObj = getValue(value, secondFieldName);
        }catch ( final Exception ignore){

        }
        if(valid){
            context.buildConstraintViolationWithTemplate(message).addPropertyNode(firstFieldName).addConstraintViolation().disableDefaultConstraintViolation();
        }
        return valid;
    }
    private Object getValue(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException{
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }
}
