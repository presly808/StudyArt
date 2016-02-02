package ua.artcode.model;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.List;

/**
 * Created by Razer on 02.02.16.

 */
@Entity
public class Course {

    @Id
    private ObjectId id;

    private String name;

    private List<Lesson> lessonList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course(String name, List<Lesson> lessonList) {
        this.name = name;
        this.lessonList = lessonList;
    }

    public List<Lesson> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

}
