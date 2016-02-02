package ua.artcode.dao;

import ua.artcode.model.Course;

/**
 * Created by Razer on 02.02.16.
 */
public interface CourseDao {

    Course addCourse(Course course);

    int size();

}
