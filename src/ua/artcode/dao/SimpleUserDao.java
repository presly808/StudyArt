package ua.artcode.dao;

import ua.artcode.exception.NoSuchUserException;
import ua.artcode.exception.UserAccountExistException;
import ua.artcode.exception.UserAuthenticationFailException;
import ua.artcode.model.UserAccount;
import ua.artcode.to.TaskFilter;

import java.util.List;
import java.util.Set;


public interface SimpleUserDao {

    UserAccount create(UserAccount user) throws UserAccountExistException;

    UserAccount findByUserName(String username) throws NoSuchUserException;

    boolean delete(String username) throws NoSuchUserException;

    Set<String> getAllUserNames();

    List<UserAccount> search(TaskFilter filter);

    public UserAccount authenticate(String username, String password) throws NoSuchUserException, UserAuthenticationFailException;

}
