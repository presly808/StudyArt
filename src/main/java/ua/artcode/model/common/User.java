package ua.artcode.model.common;


import java.io.Serializable;

public class User implements Serializable {

    private String username;
    // before saving, hash first by md5 algorithms
    // 1234 -> MD5 -> ab23ff2198fcd(stored in db)
    private String password;
    private String email;
    private UserType userType;

    private int score; // TODO should be changed on something else


    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userType = UserType.USER;
    }

    public User(String username, String password, String email, UserType userType) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userType = userType;
    }


    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }


}
