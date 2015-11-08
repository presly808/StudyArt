package ua.artcode.validation;

import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.artcode.exception.AppValidationException;

public class ConsoleMenuInputValidatorTest extends TestCase {
    private static final Logger LOGGER = Logger.getLogger(ConsoleMenuInputValidator.class);
    private static   Validator validator;
    private static String rightInput;
    private static String wrongInput;

    @BeforeClass
    public static void initValidatorItems() {
        validator = new ConsoleMenuInputValidator();
        rightInput = "4";
        wrongInput = "not a number";
    }
    @Test
    public void InputTestPositive(){
        try {
            assertEquals(true,validator.validate(rightInput));
        } catch (AppValidationException e) {
            LOGGER.error(e);
        }
    }
    @Test
    public void InputTestNegative(){
        try {
            assertEquals(false, validator.validate(wrongInput));
        } catch (AppValidationException e) {
            LOGGER.error(e);
        }
    }


}