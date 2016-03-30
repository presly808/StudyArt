package ua.artcode.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by Maxim on 25.02.2016.
 */
@Documented
@Constraint(validatedBy = TemplateValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Template {
    String message() default  "{template.error}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
