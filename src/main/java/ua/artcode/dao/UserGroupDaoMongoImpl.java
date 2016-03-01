package ua.artcode.dao;

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
    }

    @Override
    public UserGroup findByName(String name) throws NoSuchGroupException {
        UserGroup group = datastore.find(UserGroup.class, "name", name).get();
        if (group == null) {
            throw new NoSuchGroupException("There is no group with name:" + name + " !");
        }
        return group;
    }

    @Override
    public UserGroup findById(ObjectId id) throws NoSuchGroupException {
        UserGroup group = datastore.find(UserGroup.class, "id", id).get();
        if (group == null) {
            throw new NoSuchGroupException("There is no group with id:" + id + " !");
        }
        return group;
    }

    @Override
    public boolean delete(String name) throws NoSuchGroupException {
        UserGroup group = findByName(name);
        if (group != null) {
            datastore.delete(UserGroup.class, group.getId());
            return true;
        }
        return false;
    }

    @Override
    public List<UserGroup> getAllGroups() throws AppException {
        return datastore.find(UserGroup.class).asList();
    }

    @Override
    public UserGroup addGroup(UserGroup group) throws AppException {
        if (!isExist(group.getName())) {
            datastore.save(group);
            return group;
        }
        throw new AppException("Group with name: "+group.getName()+" already exist");
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
    public void updateGroup(UserGroup userGroup) throws NoSuchGroupException, AppException {
        delete(userGroup.getName());
        addGroup(userGroup);
    }

    @Override
    public void addUserToGroup(String name, User user) throws NoSuchGroupException, AppException {
        UserGroup userGroup = findByName(name);
        List<User> userList = userGroup.getStudents();
        userList.add(user);
        updateGroup(userGroup);
    }
}
