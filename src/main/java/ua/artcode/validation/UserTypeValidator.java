package ua.artcode.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Maxim on 21.02.2016.
 */
public class UserTypeValidator implements ConstraintValidator<UserType, ua.artcode.model.common.UserType> {
    @Override
    public void initialize(UserType constraintAnnotation) {

    }

    @Override
    public boolean isValid(ua.artcode.model.common.UserType userType, ConstraintValidatorContext context) {
        if (userType!=null && (userType.equals(ua.artcode.model.common.UserType.ROLE_STUDENT) || userType.equals(ua.artcode.model.common.UserType.ROLE_TEACHER))) {
            return true;
        }
        return false;
    }
}
