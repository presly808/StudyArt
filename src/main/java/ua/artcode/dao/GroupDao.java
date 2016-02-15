package ua.artcode.dao;


import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchGroupException;
import ua.artcode.model.common.UserGroup;

import java.util.List;

public interface GroupDao {

    UserGroup findById(String name) throws NoSuchGroupException;

    boolean delete(String name);

    List<UserGroup> getAll() throws AppException;

    UserGroup addGroup(UserGroup group);

    boolean isExist(String name);

    int size();

}
