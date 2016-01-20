package ua.artcode.service;

import ua.artcode.exception.AppException;
import ua.artcode.model.common.User;

import java.util.Set;

public interface UserService {

    boolean authenticate(String username, String password) throws AppException;

    User register(String username, String password, String email) throws AppException;

    User getUserInfo(String username);

    Set<String> getAllUser();

}
