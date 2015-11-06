package ua.artcode.validation;

import ua.artcode.exception.AppValidationException;

/**
 * Created by serhii on 06.11.15.
 */
public interface Validator<T> {

    boolean validate(T entity) throws AppValidationException;

}
