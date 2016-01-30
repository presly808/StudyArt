package ua.artcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.artcode.dao.UserDao;
import ua.artcode.exception.AppException;
import ua.artcode.exception.UserAuthenticationFailException;
import ua.artcode.model.common.User;
import ua.artcode.model.common.UserType;
import ua.artcode.utils.Security;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    @Qualifier("userDaoMongoMongoImpl")
    private UserDao userDao;

    @Override
    public boolean authenticate(String username, String password) throws AppException {
        User user = userDao.findByUserEmail(username);
        if (!user.getPassword().equals(Security.toMd5(password))) {
            throw new UserAuthenticationFailException("Wrong username or password");
        }
        return true;
    }

    @Override
    public User register(String username, String password, String email) throws AppException {
        return userDao.addUser(new User(username, password, email));
    }

    @Override
    public User register(String username, String password, String email, UserType userType) throws AppException {
        return userDao.addUser(new User(username, password, email, userType));
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
