package ua.artcode.exception;

/**
 * Created by Razer on 08.02.16.
 */
public class NoSuchLessonException extends AppException {

    public NoSuchLessonException() {
    }

    public NoSuchLessonException(String message) {
        super(message);
    }
}
