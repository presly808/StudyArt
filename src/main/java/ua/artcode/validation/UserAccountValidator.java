package ua.artcode.validation;

import ua.artcode.exception.AppValidationException;
import ua.artcode.model.UserAccount;

import java.util.regex.Pattern;

// http://www.mkyong.com/regular-expressions/how-to-validate-password-with-regular-expression/
// TODO finsih validator
public class UserAccountValidator implements Validator<UserAccount> {

    // took that from inet
    private static final String EMAIL_PATTERN = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"" +
            "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
            "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|" +
            "[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:" +
            "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    private static final String NAME_PATTERN = ""; // TODO find name pattern

    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

    private Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
    private Pattern namePattern = Pattern.compile(NAME_PATTERN);
    private Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);

    public UserAccountValidator() {}

    @Override
    public boolean validate(UserAccount entity) throws AppValidationException {

        AppValidationException exceptionMessageContainer = new AppValidationException();

        if(!isValidUserName(entity.getUsername())){
            exceptionMessageContainer.addMessage(String.format("username %s is invalid, recomendation %s",
                    entity.getUsername(), "example of valid username"));
        }

        if(!isValidPassword(entity.getPassword())){
            exceptionMessageContainer.addMessage(String.format("password %s is invalid, recomendation %s",
                    entity.getPassword(), "must contains one digit from 0-9\n" +
                            " must contains one lowercase characters\n" +
                            " must contains one uppercase characters\n" +
                            " must contains one special symbols in the list \"@#$%\"\n" +
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

    private boolean isValidEmail(final String email) {
        return emailPattern.matcher(email).matches();
    }

    private boolean isValidPassword(final String password) {
        return passwordPattern.matcher(password).matches();
    }

    private boolean isValidUserName(final String username) {
        return true;
    }


}
