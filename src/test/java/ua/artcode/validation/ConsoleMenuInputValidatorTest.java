package ua.artcode.validation;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.artcode.exception.AppValidationException;

import static org.junit.Assert.assertEquals;

public class ConsoleMenuInputValidatorTest{
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