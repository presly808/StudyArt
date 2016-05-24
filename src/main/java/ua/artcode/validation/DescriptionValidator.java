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
        // allowed 0 - 5 white spaces, then must be at least one letter,
        // after that any characters. Length 10 - 1000.
        // Can use special symbols as (.,:;!@#$%?-=+{}[]).
        //return description.matches("\\s{0,5}[a-zA-z][\\w\\s\\n-.,;:→=?@{}\\(\\)\\[\\]]{9,1000}");
        return description.matches("[\\w\\s\\n-.,;:→=?@{}\\(\\)\\[\\]]{10,1000}");
    }
}
