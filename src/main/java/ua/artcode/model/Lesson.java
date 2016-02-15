package ua.artcode.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Id;
import ua.artcode.model.codingbat.CodingBatTask;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Razer on 02.02.16.
 */
@Embedded
public class Lesson {
    @Id
    private ObjectId id;

    private String title;

    private String description;

    private List<CodingBatTask> tasks=new ArrayList<>();

    public Lesson() {
    }

    public Lesson(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Lesson(String title, String description, List<CodingBatTask> tasks) {
        this.title = title;
        this.description = description;
        this.tasks = tasks;
   }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CodingBatTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<CodingBatTask> tasks) {
        this.tasks = tasks;
    }


}

