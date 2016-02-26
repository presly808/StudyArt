package ua.artcode.validation;

import ua.artcode.model.common.UserType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Maxim on 21.02.2016.
 */
public class UserTypeValidator implements ConstraintValidator<User_Type, UserType> {
    @Override
    public void initialize(User_Type constraintAnnotation) {

    }

    @Override
    public boolean isValid(UserType userType, ConstraintValidatorContext context) {
        if (userType!=null && (userType.equals(UserType.ROLE_USER) || userType.equals(UserType.ROLE_TEACHER))) {
            return true;
        }
        return false;
    }
}
