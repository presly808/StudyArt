package ua.artcode.model.common;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;
import ua.artcode.validation.Description;
import ua.artcode.validation.Title;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Lesson implements Comparable<Lesson> {

    @Id
    private ObjectId id;

    @Title
    @Indexed(unique = true)
    private String title;

    @Description
    private String description;

    @Reference(lazy = true)
    private User owner;

    @Reference
    private List<Task> tasks = new ArrayList<>();

    @Transient
    private int amountTasksSize;

    @Transient
    private int performedTasksSize;

    public Lesson() {
    }

    public Lesson(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Lesson(String title, String description, List<Task> tasks) {
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int size() {
        return tasks.size();
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public int getAmountTasksSize() {
        return amountTasksSize;
    }

    public void setAmountTasksSize(int amountTasksSize) {
        this.amountTasksSize = amountTasksSize;
    }

    public int getPerformedTasksSize() {
        return performedTasksSize;
    }

    public void setPerformedTasksSize(int performedTasksSize) {
        this.performedTasksSize = performedTasksSize;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Lesson{");
        sb.append("title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", tasks=").append(tasks);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lesson that = (Lesson) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);
    }

    @Override
    public int compareTo(Lesson o) {
        return this.id.compareTo(o.id);
    }
}

