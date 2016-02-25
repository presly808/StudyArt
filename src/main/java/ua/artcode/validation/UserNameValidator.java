package ua.artcode.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Maxim on 21.02.2016.
 */
public class UserNameValidator implements ConstraintValidator<UserName, String> {

    @Override
    public void initialize(UserName constraintAnnotation) {

    }

    @Override
    public boolean isValid(String userName, ConstraintValidatorContext context) {
        if(userName == null) {
            return false;
        }
        return userName.matches("^[A-Za-z0-9_-]{3,16}$");

        //    "/^[a-z0-9_-]{3,16}$/"
    }
}
