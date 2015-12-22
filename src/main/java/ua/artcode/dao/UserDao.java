package ua.artcode.dao;

import ua.artcode.exception.AppValidationException;
import ua.artcode.exception.NoSuchUserException;
import ua.artcode.exception.UserAccountExistException;
import ua.artcode.model.common.User;

import java.util.List;


public interface UserDao {

    User addUser(User user) throws AppValidationException,UserAccountExistException;

    User findByUserEmail(String email) throws NoSuchUserException;

    boolean delete(String email) throws NoSuchUserException;

    List<User> getAllUser();

    boolean isExist(User user);

    int size();

}
