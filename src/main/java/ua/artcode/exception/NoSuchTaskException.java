package ua.artcode.exception;

/**
 * Created by serhii on 28.10.15.
 */
public class NoSuchTaskException extends AppException {

    public NoSuchTaskException() {
    }

    public NoSuchTaskException(String message) {
        super(message);
    }

}
