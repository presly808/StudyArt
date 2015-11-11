package ua.artcode.db;

import ua.artcode.model.common.UserAccount;

import java.io.Serializable;
import java.util.*;


public class UserAccountContainer implements Serializable {

    private static int numberOfUser = 0;// todo when load data counter did not change
    private Map<String, UserAccount> userMap;

    public UserAccountContainer() {
        userMap = new TreeMap<>();
    }

    public UserAccount addUser(UserAccount user){
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

    public UserAccount getByUserName(String username) {

        return userMap.get(username);
    }

    public boolean remove(UserAccount user) {

        return userMap.remove(user.getUsername(), user);
    }

}

