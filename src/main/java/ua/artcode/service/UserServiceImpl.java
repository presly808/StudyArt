package ua.artcode.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.ApplicationContext;
import ua.artcode.dao.UserDao;
import ua.artcode.dao.UserDaoMongoImpl;
import ua.artcode.exception.AppException;
import ua.artcode.exception.UserAuthenticationFailException;
import ua.artcode.model.common.User;
import ua.artcode.utils.SpringContext;

import java.util.Set;

/**
 * Created by Razer on 18.01.16.
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private ApplicationContext context;

    public UserServiceImpl() {
        context = SpringContext.getContext();
        this.userDao = context.getBean(UserDaoMongoImpl.class);
    }

    @Override
    public boolean authenticate(String username, String password) throws AppException {
        User user = userDao.findByUserEmail(username);
        if (!user.getPassword().equals(DigestUtils.md5Hex(password))) {
            throw new UserAuthenticationFailException("Wrong username or password");
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
