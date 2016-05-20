package ua.artcode.dao;

import com.mongodb.DuplicateKeyException;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import ua.artcode.exception.DuplicateDataException;
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
        LOG.debug("UserDaoMongoImpl instance has been created.");
    }

    public UserDaoMongoImpl(Datastore datastore) {
        this.datastore = datastore;
        datastore.ensureIndexes(User.class);
        LOG.debug("UserDaoMongoImpl instance has been created.");
    }

    @Override
    public void add(User user) throws DuplicateKeyException {
        user.setPassword(Security.toMd5(user.getPassword()));
        datastore.save(user);
        LOG.info("User with email: " + user.getEmail() + " has been added to DB.");
    }

    @Override
    public void update(String email, User user) throws NoSuchUserException, DuplicateDataException {
        User oldUser = findByEmail(email);
        try {
            delete(email);
            datastore.save(user);
        } catch (DuplicateKeyException e) {
            datastore.save(oldUser);
            throw new DuplicateDataException("User with email: "+user.getEmail()+" is already exist");
        }

        LOG.info("The user has been updated.");
    }

    @Override
    public User find(ObjectId id) throws NoSuchUserException {
        User user = datastore.find(User.class, "id", id).get();
        if (user == null) {
            LOG.warn("The user has been not founded. id: " + id);
            throw new NoSuchUserException("The user has been not founded.");
        }
        return user;
    }

    @Override
    public User find(String name) throws NoSuchUserException {
        User user = datastore.find(User.class, "name", name).get();
        if (user == null) {
            LOG.warn("The user has been not founded. Name: " + name);
            throw new NoSuchUserException("There is no user with name " + name);
        }
        return user;
    }

    @Override
    public User findByEmail(String email) throws NoSuchUserException {
        User user = datastore.find(User.class, "email", email).get();
        if (user == null) {
            LOG.warn("The user has been not founded. Email: " + email);
            throw new NoSuchUserException("There is no user with email " + email);
        }
        return user;
    }

    @Override
    public boolean delete(String email) throws NoSuchUserException {
        Query<User> query = datastore.createQuery(User.class);
        query.field("email").equal(email);
        User user = datastore.findAndDelete(query);
        if (user == null) {
            LOG.debug("The user has not been deleted. Email: " + email);
            throw new NoSuchUserException("There is no user with email: " + email);
        }
        return true;
    }

    @Override
    public List<User> getAll() {
        return datastore.find(User.class, "userType", "ROLE_STUDENT").asList();
    }

    @Override
    public boolean isExist(String email) {
        User existUser = datastore.find(User.class).field("email").equal(email).get();
        return existUser != null;
    }

    @Override
    public int size() {
        return (int) datastore.getDB().getCollection("User").count();
    }

    @Override
    public List<User> search(String key) {
        Query<User> query = datastore.createQuery(User.class);

        query.or(query.criteria("email").containsIgnoreCase(key),
                query.criteria("name").containsIgnoreCase(key)
        );

        return query.asList();
    }

    @Override
    public long searchCount(String key) {

        Query<User> query = datastore.createQuery(User.class);

        query.or(query.criteria("email").containsIgnoreCase(key),
                query.criteria("name").containsIgnoreCase(key)
        );

        return query.countAll();
    }

}
