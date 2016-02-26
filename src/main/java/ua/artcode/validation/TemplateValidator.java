package ua.artcode.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by Maxim on 25.02.2016.
 */
public class TemplateValidator implements ConstraintValidator<Template, String> {
    @Override
    public void initialize(Template constraintAnnotation) {

    }

    @Override
    public boolean isValid(String template, ConstraintValidatorContext context) {
        if (template == null) {
            return false;
        }
        return template.matches("(public\\s+|private\\s+|protected\\s+)?(static\\s+)?" +
                ".+\\s+[\\w\\$]+\\s*\\(.*\\)\\s*\\{[\\s\\S]*\\}");
    }
}
