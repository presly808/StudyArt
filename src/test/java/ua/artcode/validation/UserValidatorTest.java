package ua.artcode.validation;

import org.junit.BeforeClass;
import org.junit.Test;
import ua.artcode.exception.AppValidationException;
import ua.artcode.model.common.User;

import static org.junit.Assert.assertTrue;

public class UserValidatorTest {

    private static UserValidator validator;

    @BeforeClass
    public static void initValidator() {
        validator = new UserValidator();
    }

    @Test
    public void testCorrectUser() throws AppValidationException {
        User user = new User("user1879", "password", "test_mail@gmail.com");
        assertTrue(validator.validate(user));
    }

    @Test
    public void testCorrectDifficultEmail() throws AppValidationException {
        User user = new User("user1879", "password", "t'test.test-test_mail@gmail_test-test.test.com.ua");
        assertTrue(validator.validate(user));
    }

    @Test(expected = AppValidationException.class)
    public void testInvalidEmail_1() throws AppValidationException {
        User user = new User("user1897", "password", "testgmail.com");
        validator.validate(user);
    }

    @Test(expected = AppValidationException.class)
    public void testInvalidEmail_2() throws AppValidationException {
        User user = new User("user1897", "password", "some@test@gmail.com");
        validator.validate(user);
    }

    @Test(expected = AppValidationException.class)
    public void testInvalidEmail_3() throws AppValidationException {
        User user = new User("user1897", "password", "some test@gmail.com");
        validator.validate(user);
    }

    @Test(expected = AppValidationException.class)
    public void testInvalidEmail_4() throws AppValidationException {
        User user = new User("user1897", "password", "test@gmailcom");
        validator.validate(user);
    }

    @Test(expected = AppValidationException.class)
    public void testInvalidEmail_5() throws AppValidationException {
        User user = new User("user1897", "password", "test$gmail.com");
        validator.validate(user);
    }

    @Test(expected = AppValidationException.class)
    public void testInvalidEmail_6() throws AppValidationException {
        User user = new User("user1897", "password", "test@gmail.c");
        validator.validate(user);
    }

    @Test(expected = AppValidationException.class)
    public void testInvalidEmail_7() throws AppValidationException {
        User user = new User("user1897", "password", "test@gmail.longcom");
        validator.validate(user);
    }

    @Test(expected = AppValidationException.class)
    public void testInvalidEmail_8() throws AppValidationException {
        User user = new User("user1897", "password", "test@gmail.long-com");
        validator.validate(user);
    }

    @Test(expected = AppValidationException.class)
    public void testInvalidEmail_9() throws AppValidationException {
        User user = new User("user1897", "password", "test@gmail.");
        validator.validate(user);
    }
}
