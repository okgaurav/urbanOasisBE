package com.mongo.backend.model.entity;

import com.mongo.backend.config.UserRoles;
import com.mongo.backend.config.ValidateUserRole;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class UserRoleValidator implements ConstraintValidator<ValidateUserRole,String> {
    @Override
    public boolean isValid(String roles, ConstraintValidatorContext context) {
        List<String> userList = Arrays.asList(UserRoles.values().toString());
        return userList.contains(roles);
    }
}
