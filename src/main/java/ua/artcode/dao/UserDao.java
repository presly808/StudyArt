package ua.artcode.dao;

import org.bson.types.ObjectId;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchUserException;
import ua.artcode.exception.UserAccountExistException;
import ua.artcode.model.common.User;

import java.util.List;


public interface UserDao {

    User add(User user) throws UserAccountExistException;

    User find(String email) throws NoSuchUserException;

    User find(ObjectId id) throws NoSuchUserException;

    boolean delete(String email) throws NoSuchUserException;

    User update(String email, User user) throws AppException;

    List<User> getAll();

    boolean isExist(String email);

    int size();

}
