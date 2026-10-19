package com.training.validationdemo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

    private String coursePrefix;

    @Override
    public void initialize(CourseCode constraintAnnotation) {
        this.coursePrefix = constraintAnnotation.value();
    }
    
    @Override
    public boolean isValid(String code, ConstraintValidatorContext constraintValidatorContext) {
        
        boolean result = false;
        if (code != null) {
            result = code.startsWith(coursePrefix);
        }

        return result;
    }

}
