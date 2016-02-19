package ua.artcode.model.common;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import ua.artcode.exception.AppException;
import ua.artcode.model.codingbat.TaskTestResult;

import javax.validation.constraints.Size;
import java.util.HashMap;
import java.util.Map;

@Entity
public class User implements Comparable<User> {

    @Id
    private ObjectId id;


    @NotEmpty @Size(min=4,max=20,message = "error size")
    private String name;


    @NotEmpty @Size(min=6, max=25)
    private String password;


    @NotEmpty @Email(message = "error email")
    private String email;

    private UserType userType;

    private Map<String, TaskTestResult> solvedTaskContainer = new HashMap<>();

    public User() {
    }

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.userType = UserType.ROLE_USER;
    }

    public User(String name, String password, String email, UserType userType) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
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
