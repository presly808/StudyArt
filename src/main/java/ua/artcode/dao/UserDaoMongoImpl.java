package ua.artcode.dao;

import com.mongodb.DuplicateKeyException;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchUserException;
import ua.artcode.model.common.User;
import ua.artcode.utils.Security;

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
        datastore.ensureIndexes();
    }

    @Override
    public void add(User user) throws DuplicateKeyException {
        user.setPassword(Security.toMd5(user.getPassword()));
        datastore.save(user);
        //TODO
        LOG.info("User with email: " + user.getEmail() + " was added to data base.");
    }

    @Override
    public User find(String name) throws NoSuchUserException {
        User user = datastore.find(User.class, "name", name).get();
        if (user == null) {
            throw new NoSuchUserException("There is no user with name " + name);
        }
        return user;
    }

    @Override
    public User findByEmail(String email) throws NoSuchUserException {
        User user = datastore.find(User.class, "email", email).get();
        if (user == null) {
            throw new NoSuchUserException("There is no user with email " + email);
        }
        return user;
    }

    @Override
    public User find(ObjectId id) throws NoSuchUserException {
        User user = datastore.find(User.class, "id", id).get();
        if (user == null) {
            throw new NoSuchUserException("There is no user with the id: " + id);
        }
        return user;
    }

    @Override
    public boolean delete(String email) throws NoSuchUserException {
        Query<User> query = datastore.createQuery(User.class);
        query.field("email").equal(email);
        User user = datastore.findAndDelete(query);
        if (user == null) {
            throw new NoSuchUserException("There is no user with email: " + email);
        }
        LOG.info("User with email -  " + email + " was deleted from data base.");
        return true;
    }

    @Override
    public void update(String email, User user) throws AppException,DuplicateKeyException {
        delete(email);
        datastore.save(user);
    }

    @Override
    public List<User> getAll() {
        return datastore.find(User.class, "userType", "ROLE_STUDENT").asList();
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
