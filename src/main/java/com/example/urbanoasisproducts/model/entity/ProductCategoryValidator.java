package com.example.urbanoasisproducts.model.entity;

import com.example.urbanoasisproducts.config.ProductCategory;
import com.example.urbanoasisproducts.config.ValidateProductCategory;

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
