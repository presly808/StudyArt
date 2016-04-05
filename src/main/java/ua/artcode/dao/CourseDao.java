package ua.artcode.dao;

import com.mongodb.DuplicateKeyException;
import org.bson.types.ObjectId;
import ua.artcode.exception.DuplicateDataException;
import ua.artcode.exception.NoSuchCourseException;
import ua.artcode.model.common.Course;

import java.util.List;


public interface CourseDao {

    void add(Course course) throws DuplicateKeyException;

    void update(ObjectId id,Course course) throws NoSuchCourseException, DuplicateDataException;

    boolean delete(ObjectId id) throws NoSuchCourseException;

    boolean delete(String title) throws NoSuchCourseException;

    Course find(String title) throws NoSuchCourseException;

    Course find(ObjectId id) throws NoSuchCourseException;

    int size();

    boolean isExist(String title);

    List<Course> getAll();

    void update(Course course) throws NoSuchCourseException, DuplicateDataException;

}
