package ua.artcode.exception;

public class UserAuthenticationFailException extends AppException {

    public UserAuthenticationFailException() {
    }

    public UserAuthenticationFailException(String message) {super(message);
    }
}
