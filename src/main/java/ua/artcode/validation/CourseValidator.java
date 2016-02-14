package ua.artcode.validation;

import ua.artcode.exception.AppValidationException;
import ua.artcode.exception.UserAccountExistException;

/**
 * Created by Razer on 13.02.16.
 */
//TODO
public class CourseValidator implements Validator {
    @Override
    public boolean validate(Object entity) throws AppValidationException, UserAccountExistException {
        return false;
    }
}
