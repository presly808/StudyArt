package ua.artcode.dao;

import org.bson.types.ObjectId;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchCourseException;
import ua.artcode.model.Course;

import java.util.List;

/**
 * Created by Razer on 02.02.16.
 */
public interface CourseDao {

    Course addCourse(Course course) throws AppException;

    boolean delete(String title) throws NoSuchCourseException;

    Course findByTitle(String title) throws NoSuchCourseException;

    Course findById(ObjectId id) throws NoSuchCourseException;

    int size();

    boolean isExist(String title);

    List<Course> getAll();

    void updateCourse(Course course) throws NoSuchCourseException, AppException;

}
