package ua.artcode.dao;

import com.mongodb.DuplicateKeyException;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import ua.artcode.exception.DuplicateDataException;
import ua.artcode.exception.NoSuchCourseException;
import ua.artcode.model.common.Course;

import java.util.List;


public class CourseDaoMongoImpl implements CourseDao {

    private Datastore datastore;
    private static final Logger LOG = Logger.getLogger(CourseDaoMongoImpl.class);


    public CourseDaoMongoImpl() {
        LOG.debug("CourseDaoMongoImpl instance has been created.");
    }

    public CourseDaoMongoImpl(Datastore datastore) {
        this.datastore = datastore;
        datastore.ensureIndexes(Course.class);
        LOG.debug("CourseDaoMongoImpl instance has been created.");
    }


    @Override
    public void add(Course course) throws DuplicateKeyException {
        datastore.save(course);
        LOG.info("The new course has been added to DB.");
    }

    @Override
    public void update(ObjectId id, Course course) throws NoSuchCourseException, DuplicateDataException {
        Course oldCourse = find(id);
        try {
            delete(id);
            course.setId(id);
            add(course);
        } catch (DuplicateKeyException e) {
            add(oldCourse);
            throw new DuplicateDataException("Course with title: " + course.getTitle() + " already exist!");
        }
        LOG.info("The course has been updated.");
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
            LOG.warn("The course has been not founded. By id: " + id);
            throw new NoSuchCourseException("The course has been not founded.");
        }
        return course;
    }

    @Override
    public Course find(String title) throws NoSuchCourseException {
        Course course = datastore.find(Course.class, "title", title).get();
        if (course == null) {
            LOG.debug("The course has been not founded. By title: " + title);
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
            LOG.warn("The course has not been deleted.");
            throw new NoSuchCourseException("The course is not found.");
        }
        LOG.info("The course has been deleted.");
        return true;
    }

    @Override
    public boolean delete(String title) throws NoSuchCourseException {
        Query<Course> query = datastore.createQuery(Course.class);
        query.field("title").equal(title);
        Course course = datastore.findAndDelete(query);
        if (course == null) {
            LOG.debug("The course has not been deleted. Title: " + title);
            throw new NoSuchCourseException("There is no course with title: " + title);
        }
        LOG.info("The course has been deleted. Title: " + title);
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
