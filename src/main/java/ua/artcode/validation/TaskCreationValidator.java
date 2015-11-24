package ua.artcode.validation;

import ua.artcode.exception.AppValidationException;
import ua.artcode.model.codingbat.CodingBatTask;

/**
 * Created by Family on 11/8/2015.
 */
public class TaskCreationValidator implements Validator {
    @Override
    public boolean validate(Object entity) throws AppValidationException {
        CodingBatTask codingBatTask = (CodingBatTask)entity;
        return false;
    }
}
