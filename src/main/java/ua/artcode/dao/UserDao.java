package ua.artcode.dao;

import com.mongodb.DuplicateKeyException;
import org.bson.types.ObjectId;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchUserException;
import ua.artcode.model.common.User;

import java.util.List;


public interface UserDao {

    void add(User user) throws DuplicateKeyException;


    User find(String name) throws NoSuchUserException;

    User findByEmail(String email) throws NoSuchUserException;

    User find(ObjectId id) throws NoSuchUserException;

    boolean delete(String email) throws NoSuchUserException;

    void update(String email, User user) throws AppException,DuplicateKeyException;

    List<User> getAll();

    boolean isExist(String email);

    int size();

}
