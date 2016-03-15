package ua.artcode.dao;


import com.mongodb.DuplicateKeyException;
import org.bson.types.ObjectId;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchGroupException;
import ua.artcode.model.common.User;
import ua.artcode.model.common.UserGroup;

import java.util.List;

public interface UserGroupDao {

    UserGroup find(String name) throws NoSuchGroupException;

    UserGroup find(ObjectId id) throws NoSuchGroupException;

    boolean delete(String name) throws NoSuchGroupException;

    boolean delete(ObjectId id) throws NoSuchGroupException;

    List<UserGroup> getAll();

    void addGroup(UserGroup group)  throws DuplicateKeyException;

    void addUserToGroup(String name, User user) throws NoSuchGroupException, AppException;

    void update(ObjectId id,UserGroup userGroup) throws NoSuchGroupException, AppException;

    boolean isExist(String name);

    int size();

}
