package ua.artcode.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import ua.artcode.model.common.User;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Group {

    @Id
    private ObjectId id;

    private String name;

    private List<User> students=new ArrayList<>();

    public Group() {
    }

    public Group(String name, List<User> students) {
        this.name = name;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }
}
