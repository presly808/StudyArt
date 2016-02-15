package ua.artcode.dao;

import org.mongodb.morphia.Datastore;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchGroupException;
import ua.artcode.model.common.UserGroup;

import java.util.List;


public class GroupDaoMongoImpl implements GroupDao {

    private Datastore datastore;

    public GroupDaoMongoImpl() {
    }

    public GroupDaoMongoImpl(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public UserGroup findById(String name) throws NoSuchGroupException {
       UserGroup group = datastore.find(UserGroup.class, "name", name).get();
        if (group == null) {
            throw new NoSuchGroupException("There is no group with name:" + name + " !");
        }
        return group;
    }

    @Override
    public boolean delete(String name) {
        return false;
    }

    @Override
    public List<UserGroup> getAll() throws AppException {
        return datastore.find(UserGroup.class).asList();
    }

    @Override
    public UserGroup addGroup(UserGroup group) {
        datastore.save(group);
        return group;
    }

    @Override
    public boolean isExist(String name) {
        UserGroup group = datastore.find(UserGroup.class).field("name").equal(name).get();
        if (group== null) {
            return false;
        }
        return true;
    }

    @Override
    public int size() {
        return (int) datastore.getDB().getCollection("Group").count();
    }
}
