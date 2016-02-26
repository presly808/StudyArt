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
        return title.matches("[\\w-]{3,30}");
    }
}
