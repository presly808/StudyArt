package ua.artcode.model.common;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Reference;
import ua.artcode.validation.Description;
import ua.artcode.validation.Title;

import java.util.ArrayList;
import java.util.List;

@Entity
public class UserGroup {

    @Id
    private ObjectId id;
    @Title
    @Indexed(unique = true)
    private String name;
    @Description
    private String description;

    @Reference
    private List<User> students=new ArrayList<>();

    public UserGroup() {
    }

    public UserGroup(String name, List<User> students) {
        this.name = name;
        this.students = students;
    }

    public UserGroup(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }

    public List<User> getStudents() {
        return students;
    }

}
