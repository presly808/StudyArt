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
public class Course implements Comparable<Course>{

    @Id
    private ObjectId id;

    @Title
    @Indexed(unique = true)
    private String title;

    @Description
    private String description;

    @Reference
    private List<Lesson> lessonList=new ArrayList<>();

    public Course() {
    }

    public Course(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Course(String title, List<Lesson> lessonList) {
        this.title = title;
        this.lessonList = lessonList;
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

    public List<Lesson> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Course{");
        sb.append("description='").append(description).append('\'');
        sb.append(", lessonList=").append(lessonList);
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

        Course that = (Course) o;

        return !(id != null ? !id.equals(that.id) : that.id != null);
    }

    @Override
    public int compareTo(Course o) {
        return this.id.compareTo(o.id);
    }
}
