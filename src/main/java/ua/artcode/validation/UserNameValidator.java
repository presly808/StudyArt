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
        // Must start with letter, then allowed[a-zA-Z0-9_-]. Length 3 - 16.
        return userName.matches("^[a-zA-z][\\w-]{3,15}$");
    }
}
