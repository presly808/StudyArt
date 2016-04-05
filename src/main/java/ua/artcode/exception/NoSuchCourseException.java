package ua.artcode.exception;

/**
 * Created by Razer on 08.02.16.
 */
public class NoSuchCourseException extends AppException {

    public NoSuchCourseException() {
    }

    public NoSuchCourseException(String message) {
        super(message);
    }
}
