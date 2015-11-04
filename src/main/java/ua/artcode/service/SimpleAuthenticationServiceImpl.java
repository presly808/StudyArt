package ua.artcode.service;

import ua.artcode.dao.SimpleUserDao;
import ua.artcode.exception.AppException;
import ua.artcode.model.AccountType;
import ua.artcode.model.UserAccount;

import java.util.Set;

public class SimpleAuthenticationServiceImpl implements SimpleAuthenticationService {

    private SimpleUserDao userDao;

    public SimpleAuthenticationServiceImpl(SimpleUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserAccount authenticate(String username, String password) throws AppException {
        UserAccount user = userDao.authenticate(username, password);
        return user;
    }

    public UserAccount registrate(String username, String password, String email, AccountType accountType) throws AppException {
        return userDao.create(new UserAccount(username, password, email, accountType));
    }

    public UserAccount registrate(String username, String password, String email) throws AppException {
        return userDao.create(new UserAccount(username, password, email));
    }

    public Set<String> getAllUser(){
        return userDao.getAllUserNames();
    }
}
