package ua.artcode.service;

import ua.artcode.dao.UserDao;
import ua.artcode.exception.AppException;
import ua.artcode.exception.UserAuthenticationFailException;
import ua.artcode.model.common.AccountType;
import ua.artcode.model.common.User;
import ua.artcode.validation.Validator;

import java.util.Set;

public class SimpleUserServiceImpl implements UserService {


    private Validator<User> accountValidator;
    private UserDao userDao;

    public SimpleUserServiceImpl(UserDao userDao, Validator<User> accountValidator) {
        this.userDao = userDao;
        this.accountValidator = accountValidator;
    }

    @Override
    public User authenticate(String username, String password) throws AppException {
        User user = userDao.findByUserName(username);

        if(!user.getPassword().equals(password)){
            throw new UserAuthenticationFailException("wrong username or pass");
        }

        return user;
    }

    @Override
    public User register(String username, String password, String email, AccountType accountType) throws AppException {

        accountValidator.validate(new User(username,password,email));

        return userDao.create(new User(username, password, email, accountType));
    }

    @Override
    public User getUserInfo(String username) {
        return null;
    }

    @Override
    public Set<String> getAllUser() {
        return null;
    }


}
