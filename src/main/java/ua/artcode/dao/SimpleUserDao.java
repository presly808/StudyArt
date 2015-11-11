package ua.artcode.dao;

import ua.artcode.exception.NoSuchUserException;
import ua.artcode.exception.UserAccountExistException;
import ua.artcode.model.UserAccount;

import java.util.Set;


public interface SimpleUserDao {

    UserAccount create(UserAccount user) throws UserAccountExistException;

    UserAccount findByUserName(String username) throws NoSuchUserException;

    boolean delete(String username) throws NoSuchUserException;

    Set<UserAccount> getAllUser();

    Set<String> getAllUserNames();

}
