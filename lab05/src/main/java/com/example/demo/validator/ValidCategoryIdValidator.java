package com.example.demo.validator;

import com.example.demo.entity.CategoryEntity;
import com.example.demo.validator.annotation.ValidCategoryId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidCategoryIdValidator implements ConstraintValidator<ValidCategoryId, CategoryEntity> {
    @Override
    public boolean isValid(CategoryEntity category, ConstraintValidatorContext constraintValidatorContext) {
        return category != null && category.getId() != null;
    }
}
