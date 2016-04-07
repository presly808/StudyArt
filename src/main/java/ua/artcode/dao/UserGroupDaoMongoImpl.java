package ua.artcode.dao;

import com.mongodb.DuplicateKeyException;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import ua.artcode.exception.DuplicateDataException;
import ua.artcode.exception.NoSuchGroupException;
import ua.artcode.model.common.User;
import ua.artcode.model.common.UserGroup;

import java.util.List;


public class UserGroupDaoMongoImpl implements UserGroupDao {

    private Datastore datastore;
    private static final Logger LOG = Logger.getLogger(UserGroupDaoMongoImpl.class);

    public UserGroupDaoMongoImpl() {
        LOG.debug("UserGroupDaoMongoImpl instance has been created.");
    }

    public UserGroupDaoMongoImpl(Datastore datastore) {
        this.datastore = datastore;
        datastore.ensureIndexes(UserGroup.class);
        LOG.debug("UserGroupDaoMongoImpl instance has been created.");
    }

    @Override
    public void addGroup(UserGroup group) throws DuplicateKeyException {
        datastore.save(group);
        LOG.info("The new Group of users has been added to DB.");
    }

    @Override
    public void update(ObjectId id, UserGroup userGroup) throws NoSuchGroupException, DuplicateDataException {
        UserGroup oldUserGroup = find(id);
        try {
            delete(id);
            addGroup(userGroup);
        } catch (DuplicateKeyException e) {
            addGroup(oldUserGroup);
            throw new DuplicateDataException("The group with name: "+userGroup.getName()+" is already exist!");
        }
        LOG.info("The Group of users has been updated.");
    }

    @Override
    public UserGroup find(ObjectId id) throws NoSuchGroupException {
        UserGroup group = datastore.find(UserGroup.class, "id", id).get();
        if (group == null) {
            LOG.debug("The Group of users has been not founded. id: " + id);
            throw new NoSuchGroupException("There is no group with id:" + id);
        }
        return group;
    }

    @Override
    public UserGroup find(String name) throws NoSuchGroupException {
        UserGroup group = datastore.find(UserGroup.class, "name", name).get();
        if (group == null) {
            LOG.debug("The Group of users has been not founded. Name: " + name);
            throw new NoSuchGroupException("There is no group with name:" + name);
        }
        return group;
    }

    @Override
    public boolean delete(ObjectId id) throws NoSuchGroupException {
        Query<UserGroup> query = datastore.createQuery(UserGroup.class);
        query.field("id").equal(id);
        UserGroup group = datastore.findAndDelete(query);
        if (group == null) {
            LOG.warn("The user group has not been deleted. id: " + id);
            throw new NoSuchGroupException("The group has not been founded.");
        }
        LOG.info("The group has been deleted.");
        return true;
    }

    @Override
    public boolean delete(String name) throws NoSuchGroupException {
        Query<UserGroup> query = datastore.createQuery(UserGroup.class);
        query.field("name").equal(name);
        UserGroup group = datastore.findAndDelete(query);
        if (group == null) {
            LOG.debug("The user group has not been deleted. Name: " + name);
            throw new NoSuchGroupException("There is no user group with name: " + name);
        }
        return true;
    }

    @Override
    public List<UserGroup> getAll() {
        return datastore.find(UserGroup.class).asList();
    }

    @Override
    public boolean isExist(String name) {
        UserGroup group = datastore.find(UserGroup.class).field("name").equal(name).get();
        if (group == null) {
            return false;
        }
        return true;
    }

    @Override
    public int size() {
        return (int) datastore.getDB().getCollection("Group").count();
    }

    @Override
    public void addUserToGroup(String name, User user) throws NoSuchGroupException, DuplicateDataException {
        UserGroup userGroup = find(name);
        List<User> userList = userGroup.getStudents();
        userList.add(user);
        update(userGroup.getId(), userGroup);
    }
}
