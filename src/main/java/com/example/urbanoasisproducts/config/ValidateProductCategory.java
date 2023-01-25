package com.example.urbanoasisproducts.config;

import com.example.urbanoasisproducts.model.entity.ProductCategoryValidator;

import java.lang.annotation.*;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ProductCategoryValidator.class)
public @interface ValidateProductCategory {
    public String message() default "Invalid Product-Category : It should be One from the Product Category";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
