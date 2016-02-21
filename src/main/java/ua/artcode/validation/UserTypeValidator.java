package ua.artcode.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Maxim on 21.02.2016.
 */
public class UserTypeValidator implements ConstraintValidator<User_Type, String> {
    @Override
    public void initialize(User_Type constraintAnnotation) {

    }

    @Override
    public boolean isValid(String userType, ConstraintValidatorContext context) {
        if (userType.toString().equals("ROLE_USER") || userType.toString().equals("ROLE_TEACHER")) {
            return true;
        }
        return false;
    }
}
