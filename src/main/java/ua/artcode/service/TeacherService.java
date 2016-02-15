package ua.artcode.service;

import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchCourseException;
import ua.artcode.exception.NoSuchLessonException;
import ua.artcode.model.Course;
import ua.artcode.model.common.UserGroup;
import ua.artcode.model.Lesson;
import ua.artcode.model.codingbat.CodingBatTask;

import java.util.List;

/**
 * Created by Razer on 02.02.16.
 */
public interface TeacherService {

    void addLesson(Lesson lesson);

    List<Lesson> getAllLessons();

    Lesson findByTitleLesson(String title) throws NoSuchLessonException;

    void addTaskToLesson(String title, CodingBatTask codingBatTask) throws NoSuchLessonException;

    boolean deleteLesson(String title) throws NoSuchLessonException;

    void updateLesson(Lesson lesson) throws NoSuchLessonException;

    int sizeOfLesson();


    Course addCourse(Course course);

    List<Course> getAllCourses();

    void updateCourse(Course course) throws NoSuchCourseException;

    Course findByTitleCourse(String title) throws NoSuchCourseException;

    int sizeOfCourse();


    UserGroup addGroup(UserGroup group);

    boolean deleteGroup(String name);

    List<UserGroup> getAll() throws AppException;

    boolean isExistGroup(String name);

    int sizeOfGroup();

}
