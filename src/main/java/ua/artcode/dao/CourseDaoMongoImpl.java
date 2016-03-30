package ua.artcode.dao;

import com.mongodb.DuplicateKeyException;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchCourseException;
import ua.artcode.model.common.Course;

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
        datastore.ensureIndexes();
    }


    @Override
    public void add(Course course) throws DuplicateKeyException {
        datastore.save(course);
    }

    @Override
    public void update(ObjectId id, Course course) throws NoSuchCourseException, AppException {
        delete(id);
        add(course);
    }

    @Override
    public void update(Course course) throws NoSuchCourseException, AppException {
        delete(course.getTitle());
        add(course);
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
    public Course find(ObjectId id) throws NoSuchCourseException {
        Course course = datastore.find(Course.class, "id", id).get();
        if (course == null) {
            throw new NoSuchCourseException("There is no course with id:" + id);
        }
        return course;
    }

    @Override
    public Course find(String title) throws NoSuchCourseException {
        Course course = datastore.find(Course.class, "title", title).get();
        if (course == null) {
            throw new NoSuchCourseException("There is no course with title:" + title);
        }
        return course;
    }

    @Override
    public boolean delete(ObjectId id) throws NoSuchCourseException {
        Query<Course> query = datastore.createQuery(Course.class);
        query.field("id").equal(id);
        Course course = datastore.findAndDelete(query);
        if (course == null) {
            throw new NoSuchCourseException("The course is not found.");
        }
        return true;
    }

    @Override
    public boolean delete(String title) throws NoSuchCourseException {
        Query<Course> query = datastore.createQuery(Course.class);
        query.field("title").equal(title);
        Course course = datastore.findAndDelete(query);
        if (course == null) {
            throw new NoSuchCourseException("There is no course with title: " + title);
        }
        return true;
    }

    @Override
    public int size() {
        return (int) datastore.getDB().getCollection("Course").count();
    }

    @Override
    public List<Course> getAll() {
        return datastore.find(Course.class).asList();
    }

}
