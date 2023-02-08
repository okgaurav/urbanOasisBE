package com.mongo.backend.config;

import com.example.urbanoasisproducts.model.entity.UserRoleValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UserRoleValidator.class)
public @interface ValidateUserRole {
    public String message() default "Invalid User Role : It should be One from the User Roles";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
