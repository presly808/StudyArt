package ua.artcode.model;


import java.io.Serializable;

public class UserAccount implements Serializable {
    private String username;
    private String password;
    private String email;
    private AccountType accountType;


    private UserHistory history;

    public UserAccount(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.accountType = AccountType.USER;
    }

    public UserAccount(String username, String password, String email, AccountType accountType) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.accountType = accountType;
    }

    public UserHistory getHistory() {
        return history;
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

    public void setHistory(UserHistory history) {
        this.history = history;
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


    public boolean checkPassword(String pass) {
        if (pass.equals(password)) {
            return true;
        }
        return false;
    }

}
