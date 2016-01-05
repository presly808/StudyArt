package ua.artcode.model.common;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import ua.artcode.exception.AppException;
import ua.artcode.model.codingbat.TaskTestResult;

import java.util.HashMap;
import java.util.Map;

@Entity
public class User implements Comparable<User> {

    @Id
    private ObjectId id;
    private String userName;
    private String password;
    private String email;
    private UserType userType;

    private Map<String, TaskTestResult> solvedTaskContainer = new HashMap<>();

    public User() {
    }

    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.userType = UserType.USER;
    }

    public User(String userName, String password, String email, UserType userType) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.userType = userType;
    }


    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public int compareTo(User o) {
        return this.id.compareTo(o.id);
    }


    public void addSolvedTask(String codingBatId, TaskTestResult solvedTask) {
        solvedTaskContainer.put(codingBatId, solvedTask);
    }

    public TaskTestResult getSolvedTask(String codingBatId) throws AppException {
        TaskTestResult solvedTask = solvedTaskContainer.get(codingBatId);
        if (solvedTask == null) {
            throw new AppException("Solved task not found with id: " + codingBatId);
        }
        return solvedTask;
    }
}
