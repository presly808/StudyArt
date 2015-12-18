package ua.artcode.validation;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.artcode.exception.AppValidationException;
import ua.artcode.model.common.User;
import static org.junit.Assert.*;

public class UserValidatorTest {

    private static UserValidator validator;

    @BeforeClass
    public static void initValidator() {
        validator = new UserValidator();
    }

    @Test
    public void testCorrectUser() throws AppValidationException {
        User user = new User("user1879", "password", "some_mail@gmail.com");
        assertTrue(validator.validate(user));
    }

    @Test(expected = AppValidationException.class)
    public void testIncorrectEmail() throws AppValidationException {
        User user = new User("user1897", "password", "somegmail.com");
        validator.validate(user);
    }
}
