package ua.artcode.exception;

/**
 * Created by Razer on 04.04.16.
 */

public class DuplicateDataException extends AppException {

    public DuplicateDataException() {
    }

    public DuplicateDataException(String message) {
        super(message);
    }
}
