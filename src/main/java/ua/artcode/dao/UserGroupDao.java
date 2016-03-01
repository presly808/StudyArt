package ua.artcode.dao;


import org.bson.types.ObjectId;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchGroupException;
import ua.artcode.model.common.User;
import ua.artcode.model.common.UserGroup;

import java.util.List;

public interface UserGroupDao {

    UserGroup findByName(String name) throws NoSuchGroupException;

    UserGroup findById(ObjectId id) throws NoSuchGroupException;

    boolean delete(String name) throws NoSuchGroupException;

    List<UserGroup> getAllGroups() throws AppException;

    UserGroup addGroup(UserGroup group) throws AppException;

    void addUserToGroup(String name, User user) throws NoSuchGroupException, AppException;

    void updateGroup(UserGroup userGroup) throws NoSuchGroupException, AppException;

    boolean isExist(String name);

    int size();

}
