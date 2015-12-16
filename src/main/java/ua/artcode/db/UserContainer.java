package ua.artcode.db;

import ua.artcode.model.common.User;

import java.io.Serializable;
import java.util.*;


public class UserContainer implements Serializable {

    private static int numberOfUser = 0;// todo when load data counter did not change
    private Map<String, User> userMap;

    public UserContainer() {
        userMap = new TreeMap<>();
    }

    public User addUser(User user){
        if (userMap.containsKey(user.getUsername())) {
            return  null;
        }

        userMap.put(user.getUsername(), user);

        numberOfUser++;

        return user;
    }

    public Set<String> getUserNames(){

        return userMap.keySet();
    }

    public User getByUserName(String username) {

        return userMap.get(username);
    }

    public boolean remove(User user) {

        return userMap.remove(user.getUsername(), user);
    }

}

