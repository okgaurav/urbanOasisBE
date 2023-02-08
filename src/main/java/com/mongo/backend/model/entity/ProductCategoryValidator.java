package com.mongo.backend.model.entity;


import com.mongo.backend.config.ProductCategory;
import com.mongo.backend.config.ValidateProductCategory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class ProductCategoryValidator implements ConstraintValidator<ValidateProductCategory,String> {
    @Override
    public boolean isValid(String product, ConstraintValidatorContext constraintValidatorContext){
        List<String> productsCategory = Arrays.asList(ProductCategory.values().toString());
        return productsCategory.contains(product);
    }
}
