package ua.artcode.dao;

import org.bson.types.ObjectId;
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
    public Course find(ObjectId id) throws NoSuchCourseException {
        Course course = datastore.find(Course.class, "id", id).get();
        if (course == null) {
            throw new NoSuchCourseException("There is no course with id:" + id+ " !");
        }
        return course;
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
    public Course add(Course course) throws AppException {
        if (!isExist(course.getTitle())) {
            datastore.save(course);
            return course;
        }
        throw new AppException("Course with title: "+course.getTitle()+" already exist");
    }

    @Override
    public List<Course> getAll() {
        return datastore.find(Course.class).asList();
    }

    @Override
    public boolean delete(String title) throws NoSuchCourseException {
        Course course = find(title);
        if (course != null) {
            datastore.delete(Course.class, course.getId());
            return true;
        }
        return false;
    }

    @Override
    public void update(Course course) throws NoSuchCourseException, AppException {
        delete(course.getTitle());
        add(course);
    }

    @Override
    public Course find(String title) throws NoSuchCourseException {
        Course course = datastore.find(Course.class, "title", title).get();
        if (course == null) {
            throw new NoSuchCourseException("There is no course with title:" + title + " !");
        }
        return course;
    }
}
