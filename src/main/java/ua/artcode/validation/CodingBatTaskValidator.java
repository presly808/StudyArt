package ua.artcode.validation;

import ua.artcode.exception.AppValidationException;
import ua.artcode.model.codingbat.CodingBatTask;


public class CodingBatTaskValidator implements Validator<CodingBatTask> {

    @Override
    public boolean validate(CodingBatTask entity) throws AppValidationException {
        return false;
    }
}
