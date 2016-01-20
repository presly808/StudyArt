package ua.artcode.service;

import ua.artcode.dao.UserDao;
import ua.artcode.exception.AppException;
import ua.artcode.exception.UserAuthenticationFailException;
import ua.artcode.model.common.UserType;
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
    public boolean authenticate(String username, String password) throws AppException {
        User user = userDao.findByUserEmail(username);

        if (!user.getPassword().equals(password)) {
            throw new UserAuthenticationFailException("wrong username or pass");
        }
        return true;
    }

    @Override
    public User register(String username, String password, String email) throws AppException {
        return userDao.addUser(new User(username, password, email));
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
