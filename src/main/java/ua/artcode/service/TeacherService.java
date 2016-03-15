package ua.artcode.service;

import com.mongodb.DuplicateKeyException;
import org.bson.types.ObjectId;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchCourseException;
import ua.artcode.exception.NoSuchGroupException;
import ua.artcode.exception.NoSuchLessonException;
import ua.artcode.model.common.Course;
import ua.artcode.model.common.User;
import ua.artcode.model.common.UserGroup;
import ua.artcode.model.common.Lesson;
import ua.artcode.model.codingbat.Task;

import java.util.List;

/**
 * Created by Razer on 02.02.16.
 */
public interface TeacherService {

    //Lesson methods
    void addLesson(Lesson lesson) throws DuplicateKeyException;

    List<Lesson> getAllLessons();

    Lesson findLessonByTitle(String title) throws NoSuchLessonException;

    Lesson findLessonById(ObjectId id) throws NoSuchLessonException;

    void addTaskToLesson(String title, Task codingBatTask) throws NoSuchLessonException, AppException;

    boolean deleteLessonByTitle(String title) throws NoSuchLessonException;

    boolean deleteLessonById(ObjectId id) throws NoSuchLessonException;

    void updateLesson(ObjectId id,Lesson lesson) throws NoSuchLessonException, AppException;

    int sizeOfLesson();

    //Course methods
    void addCourse(Course course) throws DuplicateKeyException;

    boolean deleteCourse(String title) throws NoSuchCourseException;

    List<Course> getAllCourses();

    void updateCourse(Course course) throws NoSuchCourseException, AppException;

    Course findCourseByTitle(String title) throws NoSuchCourseException;

    Course findCourseById(ObjectId id) throws NoSuchCourseException;

    int sizeOfCourse();

    //Group methods
    void addGroup(UserGroup group) throws DuplicateKeyException;

    boolean deleteGroup(String name) throws NoSuchGroupException;

    void addUserToGroup(String name, User user) throws AppException, NoSuchGroupException;

    void updateGroup(ObjectId id,UserGroup userGroup) throws AppException, NoSuchGroupException;

    List<UserGroup> getAllGroups() throws AppException;

    UserGroup findUserGroupByName(String name) throws NoSuchGroupException;

    UserGroup findUserGroupById(ObjectId id) throws NoSuchGroupException;

    boolean isExistGroup(String name);

    int sizeOfGroup();

}
