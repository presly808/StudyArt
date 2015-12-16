package ua.artcode.dao;

import ua.artcode.db.UserContainer;
import ua.artcode.exception.NoSuchUserException;
import ua.artcode.exception.UserAccountExistException;
import ua.artcode.exception.UserAuthenticationFailException;
import ua.artcode.model.common.User;

import java.util.Set;

public class SimpleUserDaoImpl implements SimpleUserDao {

    private UserContainer userContainer;

    public SimpleUserDaoImpl(UserContainer userContainer) {

        this.userContainer = userContainer;
    }

    public User create(User user) throws  UserAccountExistException {

        User newuser = userContainer.addUser(user);
        if (newuser == null) {
            throw new UserAccountExistException("Account already exists for " + user.getUsername());
        }
        return user;
    }

    public User findByUserName(String username) throws NoSuchUserException {

        User user = userContainer.getByUserName(username);
        if (user == null) {
            throw new NoSuchUserException("Have no Account for " + username);
        }
        return user;
    }

    public boolean delete(String username) throws NoSuchUserException {

        User user = findByUserName(username);
        return userContainer.remove(user);
    }

    @Override
    public Set<User> getAllUser() {
        return null;
    }

    public Set<String> getAllUserNames()  {
        return userContainer.getUserNames();
    }

    public User authenticate(String username, String password) throws NoSuchUserException, UserAuthenticationFailException{

        User user = findByUserName(username);
        if (user.getPassword().equals(password)) {
            throw new UserAuthenticationFailException("Incorrect password");
        }
        return user;
    }

}
