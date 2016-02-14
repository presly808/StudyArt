package ua.artcode.dao;

import ua.artcode.exception.NoSuchCourseException;
import ua.artcode.model.Course;

import java.util.List;

/**
 * Created by Razer on 02.02.16.
 */
public interface CourseDao {

    Course addCourse(Course course);

    boolean delete(String title) throws NoSuchCourseException;

    Course findByTitle(String title) throws NoSuchCourseException;

    int size();

    List<Course> getAll();

    void updateCourse(Course course) throws  NoSuchCourseException;

}
