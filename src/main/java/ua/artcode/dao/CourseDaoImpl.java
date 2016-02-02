package ua.artcode.dao;

import org.mongodb.morphia.Datastore;
import ua.artcode.model.Course;

/**
 * Created by Razer on 02.02.16.
 */
public class CourseDaoImpl implements CourseDao {

    private Datastore datastore;

    public CourseDaoImpl() {
    }

    public CourseDaoImpl(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public int size() {
        return (int) datastore.getDB().getCollection("Course").count();
    }

    @Override
    public Course addCourse(Course course) {
        datastore.save(course);
        return course;
    }
}
