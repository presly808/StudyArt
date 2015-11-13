package ua.artcode.dao;

import ua.artcode.db.UserAccountContainer;
import ua.artcode.exception.NoSuchUserException;
import ua.artcode.exception.UserAccountExistException;
import ua.artcode.exception.UserAuthenticationFailException;
import ua.artcode.model.common.UserAccount;

import java.util.Set;
import java.util.stream.Collectors;

public class SimpleUserDaoImpl implements SimpleUserDao {

    private UserAccountContainer userContainer;

    public SimpleUserDaoImpl(UserAccountContainer userContainer) {

        this.userContainer = userContainer;
    }

    public UserAccount create(UserAccount user) throws  UserAccountExistException {

        UserAccount newuser = userContainer.addUser(user);
        if (newuser == null) {
            throw new UserAccountExistException("Account already exists for " + user.getUsername());
        }
        return user;
    }

    public UserAccount findByUserName(String username) throws NoSuchUserException {

        UserAccount user = userContainer.getByUserName(username);
        if (user == null) {
            throw new NoSuchUserException("Have no Account for " + username);
        }
        return user;
    }

    public boolean delete(String username) throws NoSuchUserException {

        UserAccount user = findByUserName(username);
        return userContainer.remove(user);
    }

    @Override
    public Set<UserAccount> getAllUser() {

        return userContainer.getAllUser().stream().collect(Collectors.toSet());
    }

    public Set<String> getAllUserNames()  {
        return userContainer.getUserNames();
    }

    public UserAccount authenticate(String username, String password) throws NoSuchUserException, UserAuthenticationFailException{

        UserAccount user = findByUserName(username);
        if (user.getPassword() != password) {
            throw new UserAuthenticationFailException("Incorrect password");
        }
        return user;
    }

}
