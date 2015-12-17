package ua.artcode.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Maxim on 16.12.2015.
 */
public class TestUserValidator {
    public static void main(String[] args) {
        String regex = "[a-z0-9&&[0-9]+]+";

        String userName = "oihuhhuhguh76uh";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userName);

        boolean result = matcher.matches();
        System.out.println("result - " + result);
    }
}
