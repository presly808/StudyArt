package ua.artcode.model.common;


import java.io.Serializable;

public class User implements Serializable {

    private String username;
    private String password;
    private String email;
    private AccountType accountType;

    private int score; // TODO should be changed on something else


    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.accountType = AccountType.USER;
    }

    public User(String username, String password, String email, AccountType accountType) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.accountType = accountType;
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

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }


}
