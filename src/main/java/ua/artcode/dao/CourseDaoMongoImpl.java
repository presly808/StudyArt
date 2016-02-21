package ua.artcode.dao;

import org.mongodb.morphia.Datastore;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchCourseException;
import ua.artcode.model.Course;

import java.util.List;

/**
 * Created by Razer on 02.02.16.
 */
public class CourseDaoMongoImpl implements CourseDao {

    private Datastore datastore;

    public CourseDaoMongoImpl() {
    }

    public CourseDaoMongoImpl(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public int size() {
        return (int) datastore.getDB().getCollection("Course").count();
    }

    @Override
    public boolean isExist(String title) {
        Course existCourse = datastore.find(Course.class).field("title").equal(title).get();
        if (existCourse == null) {
            return false;
        }
        return true;
    }

    @Override
    public Course addCourse(Course course) throws AppException {
        if (!isExist(course.getTitle())) {
            datastore.save(course);
            return course;
        }
        throw new AppException("Task with this title already exist");
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
        }
        return false;
    }

    @Override
    public void updateCourse(Course course) throws NoSuchCourseException, AppException {
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
