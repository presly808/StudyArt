package ua.artcode.service;

import ua.artcode.dao.SimpleUserDao;
import ua.artcode.exception.AppException;
import ua.artcode.exception.UserAuthenticationFailException;
import ua.artcode.model.AccountType;
import ua.artcode.model.UserAccount;
import ua.artcode.validation.Validator;

import java.util.Set;

public class SimpleUserServiceImpl implements SimpleUserService {


    private Validator<UserAccount> accountValidator;
    private SimpleUserDao userDao;

    public SimpleUserServiceImpl(SimpleUserDao userDao, Validator<UserAccount> accountValidator) {
        this.userDao = userDao;
        this.accountValidator = accountValidator;
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

        if (accountType == null) {
            accountType = AccountType.USER;
        }

        //accountValidator.validate(new UserAccount(username,password,email));
        UserAccount user = userDao.create(new UserAccount(username, password, email, accountType));

        return user;
    }

    @Override
    public UserAccount getUserInfo(String username) {
        return null;
    }

    @Override
    public Set<String> getAllUser() {

        return userDao.getAllUserNames();

    }


}
