package ua.artcode.dao;

import org.bson.types.ObjectId;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchUserException;
import ua.artcode.model.common.User;

import java.util.List;


public interface UserDao {

    User addUser(User user) throws AppException;

    User findByUserEmail(String email) throws NoSuchUserException;

    User findByUserId(ObjectId id) throws NoSuchUserException;

    boolean delete(String email) throws NoSuchUserException;

    User update(String email, User user) throws AppException;

    List<User> getAllUser();

    boolean isExist(String email);

    int size();

}
