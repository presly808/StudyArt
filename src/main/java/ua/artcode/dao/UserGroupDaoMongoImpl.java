package ua.artcode.dao;

import com.mongodb.DuplicateKeyException;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchGroupException;
import ua.artcode.model.common.User;
import ua.artcode.model.common.UserGroup;

import java.util.List;


public class UserGroupDaoMongoImpl implements UserGroupDao {

    private Datastore datastore;

    public UserGroupDaoMongoImpl() {
    }

    public UserGroupDaoMongoImpl(Datastore datastore) {
        this.datastore = datastore;
        datastore.ensureIndexes();
    }

    @Override
    public void update(ObjectId id, UserGroup userGroup) throws NoSuchGroupException, AppException {
        delete(id);
        addGroup(userGroup);
    }

    @Override
    public UserGroup find(String name) throws NoSuchGroupException {
        UserGroup group = datastore.find(UserGroup.class, "name", name).get();
        if (group == null) {
            throw new NoSuchGroupException("There is no group with name:" + name + " !");
        }
        return group;
    }

    @Override
    public UserGroup find(ObjectId id) throws NoSuchGroupException {
        UserGroup group = datastore.find(UserGroup.class, "id", id).get();
        if (group == null) {
            throw new NoSuchGroupException("There is no group with id:" + id + " !");
        }
        return group;
    }

    @Override
    public boolean delete(String name) throws NoSuchGroupException {
        UserGroup userGroup= find(name);
        if (userGroup != null) {
            datastore.delete(UserGroup.class, userGroup.getId());
            return true;
        } else
            return false;
    }

    @Override
    public boolean delete(ObjectId id) throws NoSuchGroupException {
        UserGroup userGroup = find(id);
        if (userGroup != null) {
            datastore.delete(UserGroup.class, id);
            return true;
        } else
            return false;
    }

    @Override
    public List<UserGroup> getAll()  {
        return datastore.find(UserGroup.class).asList();
    }

    @Override
    public void addGroup(UserGroup group) throws DuplicateKeyException {
            datastore.save(group);
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
    public void addUserToGroup(String name, User user) throws NoSuchGroupException, AppException {
        UserGroup userGroup = find(name);
        List<User> userList = userGroup.getStudents();
        userList.add(user);
        update(userGroup.getId(),userGroup);
    }
}
