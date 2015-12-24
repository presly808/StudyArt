package ua.artcode.validation;

import ua.artcode.exception.AppValidationException;
import ua.artcode.model.common.User;

import java.util.regex.Pattern;

// TODO finish validator
public class UserValidator implements Validator<User> {

    private static final String EMAIL_PATTERN = "([a-z0-9]+([\\-_.']?[a-z0-9])+)"
            + "@([a-z0-9]+([\\-_.]?[a-z0-9])+)"
            + "(\\.[a-z]{2,4})+";

    private static final String NAME_PATTERN = "[a-z0-9[\\-_.]]{6,20}";

    private static final String PASSWORD_PATTERN = "[a-z0-9[\\-_.]]{6,20}";

    private Pattern namePattern = Pattern.compile(NAME_PATTERN);
    private Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
    private Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);

    public UserValidator() {
    }

    @Override
    public boolean validate(User entity) throws AppValidationException {



        AppValidationException exceptionMessageContainer = new AppValidationException();

        if (!isValidUserName(entity.getUserName())) {
            exceptionMessageContainer.addMessage(String.format("userName %s is invalid, recommendation %s",
                    entity.getUserName(), "can contains letters from a-z\n" +
                            "can contains digits from 0-9\n" +
                            "can contains special symbols \"_ . -\"\n" +
                            "length at least 6 characters and maximum of 20\te"
            ));
        }

        if (!isValidPassword(entity.getPassword())) {
            exceptionMessageContainer.addMessage(String.format("password %s is invalid, recommendation %s",
                    entity.getPassword(), "can contains letters from a-z\n" +
                            "can contains digits from 0-9\n" +
                            "can contains special symbols \"_ . -\"\n" +
                            "length at least 6 characters and maximum of 20\te"));
        }

        if (!isValidEmail(entity.getEmail())) {
            exceptionMessageContainer.addMessage(String.format("email %s is invalid, recommendation %s",
                    entity.getUserName(), "example of valid email  something@mail.ua"));
        }

        if (!exceptionMessageContainer.getExceptionMessageList().isEmpty()) {
            throw exceptionMessageContainer;
        }


        return true;
    }

    private boolean isValidUserName(String userName) {
        return namePattern.matcher(userName.toLowerCase()).matches();
    }

    private boolean isValidPassword(String password) {
        return passwordPattern.matcher(password.toLowerCase()).matches();
    }

    private boolean isValidEmail(String email) {
        return emailPattern.matcher(email.toLowerCase()).matches();
    }


}
