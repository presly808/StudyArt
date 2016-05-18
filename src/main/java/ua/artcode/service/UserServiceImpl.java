package ua.artcode.service;

import com.mongodb.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.artcode.dao.UserDao;
import ua.artcode.exception.AppException;
import ua.artcode.exception.DuplicateDataException;
import ua.artcode.exception.NoSuchUserException;
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

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserDao userDao) {
        this.userDao=userDao;
    }

    @Override
    public User findUserByEmail(String email) throws NoSuchUserException {
        return userDao.findByEmail(email);
    }

    @Override
    public void update(String email, User user) throws NoSuchUserException,DuplicateDataException {
        userDao.update(email,user);
    }

    @Override
    public User findUser(String name) throws NoSuchUserException {
       return userDao.find(name);
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
    public void register(User user) throws DuplicateKeyException {
        userDao.add(user);
    }

    @Override
    public void register(String username, String password, String email) throws DuplicateKeyException {
        userDao.add(new User(username, password, email));
    }

    @Override
    public void register(String username, String password, String email, UserType userType) throws DuplicateKeyException {
        userDao.add(new User(username, password, email, userType));
    }

    @Override
    public User getUserInfo(String username) {
        try {
            return userDao.find(username);
        } catch (NoSuchUserException e) {
            return null;
        }
    }

    @Override
    public int size() {
        return userDao.size();
    }

    @Override
    public List<User> search(String keyWord) {
        return userDao.search(keyWord);
    }

    @Override
    public boolean delete(String email) throws NoSuchUserException {
       return userDao.delete(email);
    }


    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }
}
