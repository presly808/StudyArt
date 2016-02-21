package ua.artcode.dao;

import org.apache.log4j.Logger;
import org.mongodb.morphia.Datastore;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchUserException;
import ua.artcode.exception.UserAccountExistException;
import ua.artcode.model.common.User;
import ua.artcode.utils.Security;
import ua.artcode.validation.UserValidator;

import java.util.List;

/**
 * Created by Maxim on 17.12.2015.
 */

public class UserDaoMongoImpl implements UserDao {

    private static final Logger LOG = Logger.getLogger(UserDaoMongoImpl.class);

    private Datastore datastore;

    public UserDaoMongoImpl() {
    }

    public UserDaoMongoImpl(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public User addUser(User user) throws AppException {
        if (!isExist(user.getEmail())) {
            UserValidator validator = new UserValidator();
            validator.validate(user);
            user.setPassword(Security.toMd5(user.getPassword()));
            datastore.save(user);
            LOG.info("User with email: " + user.getEmail() + " was added to data base.");
            return user;
        }
        throw new UserAccountExistException("Failed registration");
    }

    @Override
    public User findByUserEmail(String userEmail) throws NoSuchUserException {
        User user = datastore.find(User.class, "email", userEmail).get();
        if (user == null) {
            throw new NoSuchUserException("There is no user with the email: " + userEmail);
        }
        return user;
    }

    @Override
    public boolean delete(String userEmail) throws NoSuchUserException {
        User user = datastore.find(User.class).field("email").equal(userEmail).get();
        // TODO use next code line datastore.findAndDelete(datastore.find(User.class,"email",userEmail));
        if (user == null) {
            throw new NoSuchUserException("There are no user with email: " + userEmail);
        }
        datastore.delete(User.class, user.getId());
        LOG.info("User with email -  " + userEmail + " was deleted from data base.");
        return true;
    }

    @Override
    public User update(String email, User newUser) throws AppException {
        User oldUser = findByUserEmail(email);
        newUser.setEmail(oldUser.getEmail());
        delete(email);
        addUser(newUser);
        return newUser;
    }

    @Override
    public List<User> getAllUser() {
        return datastore.find(User.class, "userType", "ROLE_USER").asList();
    }

    @Override
    public boolean isExist(String email) {
        User existUser = datastore.find(User.class).field("email").equal(email).get();
        if (existUser == null) {
            return false;
        }
        return true;
    }

    @Override
    public int size() {
        return (int) datastore.getDB().getCollection("User").count();
    }

}
