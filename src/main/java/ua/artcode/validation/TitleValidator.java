package ua.artcode.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Maxim on 24.02.2016.
 */
public class TitleValidator implements ConstraintValidator<Title, String> {
    @Override
    public void initialize(Title constraintAnnotation) {

    }

    @Override
    public boolean isValid(String title, ConstraintValidatorContext context) {
        if (title == null) {
            return false;
        }
        // Must start with letter, then allowed[a-zA-Z0-9_-]. Length 3 - 30.
        return title.matches("[a-zA-z][\\w-]{2,29}");
    }
}
