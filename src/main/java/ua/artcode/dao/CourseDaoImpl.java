package ua.artcode.dao;

import org.mongodb.morphia.Datastore;
import ua.artcode.exception.NoSuchCourseException;
import ua.artcode.model.Course;

import java.util.List;

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

    @Override
    public List<Course> getAll() {
       return datastore.find(Course.class).asList();
    }

    @Override
    public boolean delete(String title) throws NoSuchCourseException {
        Course course = findByTitle(title);
        if (course != null) {
            datastore.delete(Course.class, course.getId());
            return true;
        } else
            return false;
    }

    @Override
    public void updateCourse(Course course) throws NoSuchCourseException {
        delete(course.getTitle());
        addCourse(course);
    }

    @Override
    public Course findByTitle(String title) throws NoSuchCourseException {
        Course course = datastore.find(Course.class, "title", title).get();
        if (course == null) {
            throw new NoSuchCourseException("There is no course with title:" + title + " !");
        }
        return course;
    }
}
