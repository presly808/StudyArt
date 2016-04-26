package ua.artcode.model.common;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import ua.artcode.model.taskComponent.TaskTestResult;
import ua.artcode.validation.Email;
import ua.artcode.validation.Password;
import ua.artcode.validation.UserName;
import ua.artcode.validation.UserType;

import java.util.HashMap;
import java.util.Map;

@Entity
public class User implements Comparable<User> {

    @Id
    private ObjectId id;

    @UserName
    @Indexed(unique = true)
    private String name;

    @Password
    private String password;

    @Email
    @Indexed(unique = true)
    private String email;

    @UserType
    private ua.artcode.model.common.UserType userType;

    @Embedded
    private Map<ObjectId, TaskTestResult> solvedTaskContainer = new HashMap<>();


    public User() {
        id=new ObjectId();
    }

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.userType = ua.artcode.model.common.UserType.ROLE_STUDENT;
    }

    public User(String name, String password, String email, ua.artcode.model.common.UserType userType) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.userType = userType;
    }



    public Map<ObjectId, TaskTestResult> getSolvedTaskContainer() {
        return solvedTaskContainer;
    }

    public void setSolvedTaskContainer(Map<ObjectId, TaskTestResult> solvedTaskContainer) {
        this.solvedTaskContainer = solvedTaskContainer;
    }

    public void addSolvedTask(ObjectId id,TaskTestResult taskTestResult){
        solvedTaskContainer.put(id,taskTestResult);
    }

    public TaskTestResult getSolvedTask(ObjectId id)  {
        return  solvedTaskContainer.get(id);
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

    public ua.artcode.model.common.UserType getUserType() {
        return userType;
    }

    public void setUserType(ua.artcode.model.common.UserType userType) {
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

}
