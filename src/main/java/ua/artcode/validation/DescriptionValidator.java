package ua.artcode.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Maxim on 24.02.2016.
 */
public class DescriptionValidator implements ConstraintValidator<Description, String> {
    @Override
    public void initialize(Description constraintAnnotation) {

    }

    @Override
    public boolean isValid(String description, ConstraintValidatorContext context) {
        if (description == null) {
            return false;
        }
        return description.matches("[\\s\\S]{10,1000}");
    }
}
