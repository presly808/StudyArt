package ua.artcode.service;

import com.mongodb.DuplicateKeyException;
import ua.artcode.exception.AppException;
import ua.artcode.exception.DuplicateDataException;
import ua.artcode.exception.NoSuchUserException;
import ua.artcode.model.common.User;
import ua.artcode.model.common.UserType;

import java.util.List;

public interface UserService {

    boolean authenticate(String username, String password) throws AppException;

    void register(String username, String password, String email) throws DuplicateKeyException;

    void register(String username, String password, String email, UserType userType) throws DuplicateKeyException;

    void register(User user) throws DuplicateKeyException;

    User getUserInfo(String username);

    int size();

    List<User> search(String keyWord);

    List<User> getAllUsers();

    User findUser(String name) throws NoSuchUserException;

    User findUserByEmail(String email) throws NoSuchUserException;

    void update(String email, User user) throws NoSuchUserException,DuplicateDataException;

    boolean delete(String email) throws NoSuchUserException;

}
