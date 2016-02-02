package ua.artcode.service;

import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchUserException;
import ua.artcode.model.common.User;
import ua.artcode.model.common.UserType;

import java.util.Set;

public interface UserService {

    boolean authenticate(String username, String password) throws AppException;

    User register(String username, String password, String email) throws AppException;

    User register(String username, String password, String email, UserType userType) throws AppException;

    User getUserInfo(String username);

    Set<String> getAllUser();

    User getUser(String email) throws NoSuchUserException;

}
