package ua.artcode.exception;


public class NoSuchUserException extends AppException {

    public NoSuchUserException() {
    }

    public NoSuchUserException(String message) {
        super(message);
    }
}
