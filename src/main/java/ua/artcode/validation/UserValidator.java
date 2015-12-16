package ua.artcode.validation;

import ua.artcode.exception.AppValidationException;
import ua.artcode.model.common.User;

import java.util.regex.Pattern;

// TODO finish validator
public class UserValidator implements Validator<User> {

    private static final String EMAIL_PATTERN = "([a-z0-9]+([\\.-_]?[a-z0-9])+)"
            +"@([a-z0-9]+([\\.-_]?[a-z0-9])+)"
            + "([\\.][a-z]{2,4})+";

    private static final String NAME_PATTERN = "[a-z0-9[\\._-]]{6,20}";

    private static final String PASSWORD_PATTERN = "[a-z0-9[\\._-]]{6,20}";

    private Pattern namePattern = Pattern.compile(NAME_PATTERN);
    private Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
    private Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);

    public UserValidator() {}

    @Override
    public boolean validate(User entity) throws AppValidationException {

        AppValidationException exceptionMessageContainer = new AppValidationException();

        if(!isValidUserName(entity.getUsername())){
            exceptionMessageContainer.addMessage(String.format("username %s is invalid, recomendation %s",
                    entity.getUsername(), "example of valid username"));
        }

        if(!isValidPassword(entity.getPassword())){
            exceptionMessageContainer.addMessage(String.format("password %s is invalid, recomendation %s",
                    entity.getPassword(), "can contains only digits from 0-9\n" +
                            " can contains one special symbols in the list \"_ . -\"\n" +
                            " length at least 6 characters and maximum of 20\te"));
        }

        if(!isValidEmail(entity.getEmail())){
            exceptionMessageContainer.addMessage(String.format("email %s is invalid, recomendation %s",
                    entity.getUsername(), "example of valid email"));
        }

        if(!exceptionMessageContainer.getExceptionMessageList().isEmpty()){
            throw exceptionMessageContainer;
        }

        return true;
    }

    private boolean isValidUserName(final String username) {
        return namePattern.matcher(username.toLowerCase()).matches();
    }

    private boolean isValidPassword(final String password) {
        return passwordPattern.matcher(password.toLowerCase()).matches();
    }

    private boolean isValidEmail(final String email) {
        return emailPattern.matcher(email.toLowerCase()).matches();
    }


}
