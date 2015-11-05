package ua.artcode.service;

import ua.artcode.dao.SimpleUserDao;
import ua.artcode.exception.AppException;
import ua.artcode.exception.UserAuthenticationFailException;
import ua.artcode.model.AccountType;
import ua.artcode.model.UserAccount;

import java.util.Set;

public class SimpleUserServiceImpl implements SimpleUserService {

    private SimpleUserDao userDao;

    public SimpleUserServiceImpl(SimpleUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserAccount authenticate(String username, String password) throws AppException {
        UserAccount user = userDao.findByUserName(username);

        if(!user.getPassword().equals(password)){
            throw new UserAuthenticationFailException("wrong username or pass");
        }

        return user;
    }

    @Override
    public UserAccount register(String username, String password, String email, AccountType accountType) throws AppException {
        return userDao.create(new UserAccount(username, password, email, accountType));
    }

    @Override
    public UserAccount getUserInfo(String username) {
        return null;
    }

    @Override
    public Set<String> getAllUser() {
        return null;
    }


}
