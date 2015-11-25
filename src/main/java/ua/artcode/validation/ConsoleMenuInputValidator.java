package ua.artcode.validation;

import org.apache.log4j.Logger;
import ua.artcode.exception.AppValidationException;

/**
 * Created by Family on 11/8/2015.
 */
public class ConsoleMenuInputValidator implements Validator {
    private static final Logger LOGGER = Logger.getLogger(ConsoleMenuInputValidator.class);

    @Override
    public boolean validate(Object entity) throws AppValidationException {
        try {
            Integer.parseInt((String) entity);
            return true;
        } catch (NumberFormatException nfe) {
            LOGGER.error(nfe);
            System.out.println("String " + entity + " is not a number");
            return false;
        }
    }
}