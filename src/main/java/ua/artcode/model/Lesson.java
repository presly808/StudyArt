package ua.artcode.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Id;
import ua.artcode.model.codingbat.CodingBatTask;

import java.util.List;

/**
 * Created by Razer on 02.02.16.
 */
@Embedded
public class Lesson {
    @Id
    private ObjectId id;

    private String Title;

    private String Description;

    private List<CodingBatTask> Tasks;

    public Lesson() {
    }

    public Lesson(String title, String description, List<CodingBatTask> tasks) {
        Title = title;
        Description = description;
        Tasks = tasks;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public List<CodingBatTask> getTasks() {
        return Tasks;
    }

    public void setTasks(List<CodingBatTask> tasks) {
        Tasks = tasks;
    }

}

