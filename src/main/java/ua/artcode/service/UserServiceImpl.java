package ua.artcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.artcode.dao.UserDao;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchUserException;
import ua.artcode.exception.UserAccountExistException;
import ua.artcode.exception.UserAuthenticationFailException;
import ua.artcode.model.common.User;
import ua.artcode.model.common.UserType;
import ua.artcode.utils.Security;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    @Qualifier("userDaoMongoMongoImpl")
    private UserDao userDao;

    @Override
    public User findUser(String email) throws NoSuchUserException {
       return userDao.find(email);
    }

    @Override
    public boolean authenticate(String username, String password) throws AppException {
        User user = userDao.find(username);
        if (!user.getPassword().equals(Security.toMd5(password))) {
            throw new UserAuthenticationFailException("Wrong username or password");
        }
        return true;
    }

    @Override
    public User register(String username, String password, String email) throws AppException {
        return userDao.add(new User(username, password, email));
    }

    @Override
    public User register(String username, String password, String email, UserType userType) throws AppException {
        return userDao.add(new User(username, password, email, userType));
    }

    @Override
    public User getUserInfo(String username) {
        User user = null;
        List<User> ListOfUsers = userDao.getAll();
        for (User listOfUser : ListOfUsers) {
            if (listOfUser.getName().equals(username)) {
                user = listOfUser;
            }

        }
        return user;
    }

    @Override
    public boolean delete(String email) throws NoSuchUserException {
       return userDao.delete(email);
    }

    @Override
    public User register(User user) throws UserAccountExistException {
        return userDao.add(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }
}
