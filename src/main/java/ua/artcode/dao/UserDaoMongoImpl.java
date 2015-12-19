package ua.artcode.dao;

import org.mongodb.morphia.Datastore;
import ua.artcode.exception.NoSuchUserException;
import ua.artcode.exception.UserAccountExistException;
import ua.artcode.model.common.User;

import java.util.List;

/**
 * Created by Maxim on 17.12.2015.
 */
public class UserDaoMongoImpl implements UserDao{
    private Datastore datastore;

    public UserDaoMongoImpl(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public User addUser(User user) throws UserAccountExistException {
        datastore.save(user);
        return user;
    }

    @Override
    public User findByUserName(String userName) throws NoSuchUserException {
        User user = datastore.find(User.class, "userName", userName).get();
        if (user == null) {
            throw new NoSuchUserException("There is no user with the name " + userName);
        }
        return user;
    }

    @Override
    public boolean delete(String userName) throws NoSuchUserException {
        User user = datastore.find(User.class).field("userName").equal(userName).get();
        if (user != null) {
            datastore.delete(User.class, user.getUserName());
        }
        return false;
    }

    @Override
    public List<User> getAllUser() {
        List<User> allUsers = datastore.find(User.class).asList();
        return allUsers;
    }

    @Override
    public int size() {
        return (int) datastore.getDB().getCollection("User").count();
    }


}
