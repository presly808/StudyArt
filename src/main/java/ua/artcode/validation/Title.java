package ua.artcode.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by Maxim on 24.02.2016.
 */
@Documented
@Constraint(validatedBy = TitleValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Title {
    String message() default  "Invalid title";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
