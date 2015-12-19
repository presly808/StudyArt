package ua.artcode.dao;

import ua.artcode.exception.NoSuchUserException;
import ua.artcode.exception.UserAccountExistException;
import ua.artcode.model.common.User;

import java.util.List;


public interface UserDao {

    User addUser(User user) throws UserAccountExistException;

    User findByUserName(String username) throws NoSuchUserException;

    boolean delete(String username) throws NoSuchUserException;

    List<User> getAllUser();

    int size();

}
