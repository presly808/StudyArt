package ua.artcode.exception;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Created by serhii on 28.10.15.
 */
public class AppException extends Exception {

    private Collection<String> exceptionMessageList;

    public AppException() {
        exceptionMessageList = new LinkedList<>();
    }

    public AppException(String message) {
        super(message);
        exceptionMessageList = new LinkedList<>();
        exceptionMessageList.add(message);
    }

    public void addMessage(String message){
        exceptionMessageList.add(message);
    }

    public Collection<String> getExceptionMessageList(){
        return exceptionMessageList;
    }

}
