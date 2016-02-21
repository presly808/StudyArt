package ua.artcode.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by Maxim on 21.02.2016.
 */

@Documented
@Constraint(validatedBy = UserTypeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface User_Type {
    String message() default  "Chose user type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
