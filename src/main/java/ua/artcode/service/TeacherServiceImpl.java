package ua.artcode.service;

import com.mongodb.DuplicateKeyException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.artcode.dao.CourseDao;
import ua.artcode.dao.LessonDao;
import ua.artcode.dao.UserGroupDao;
import ua.artcode.exception.*;
import ua.artcode.model.common.Course;
import ua.artcode.model.common.Lesson;
import ua.artcode.model.common.Task;
import ua.artcode.model.common.User;
import ua.artcode.model.common.UserGroup;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Qualifier("courseDaoImpl")
    @Autowired()
    private CourseDao courseDao;

    @Qualifier("lessonDaoImpl")
    @Autowired
    private LessonDao lessonDao;

    @Override
    public void updateCourse(ObjectId id, Course course) throws NoSuchCourseException,DuplicateDataException {
        courseDao.update(id, course);
    }

    @Qualifier("groupDaoImpl")
    @Autowired
    private UserGroupDao userGroupDao;


    //Lesson methods
    @Override
    public void addLesson(Lesson lesson) throws DuplicateKeyException {
        lessonDao.add(lesson);
    }

    @Override
    public List<Lesson> getAllLessons() {
        return lessonDao.getAll();
    }

    @Override
    public void addTaskToLesson(String title, Task codingBatTask) throws NoSuchLessonException, DuplicateDataException {
        lessonDao.addTask(title,codingBatTask);
    }

    @Override
    public int sizeOfLesson() {
        return lessonDao.size();
    }

    @Override
    public void updateLesson(ObjectId id,Lesson lesson) throws NoSuchLessonException, DuplicateDataException{
        lessonDao.update(id,lesson);
    }

    @Override
    public Lesson findLessonByTitle(String title) throws NoSuchLessonException {
        return lessonDao.find(title);
    }

    @Override
    public Lesson findLessonById(ObjectId id) throws NoSuchLessonException {
        return lessonDao.find(id);
    }

    @Override
    public boolean deleteLessonByTitle(String title) throws NoSuchLessonException {
        return lessonDao.delete(title);
    }

    @Override
    public boolean deleteLessonById(ObjectId id) throws NoSuchLessonException {
        return lessonDao.delete(id);
    }


    //Course methods
    @Override
    public void addCourse(Course course) throws DuplicateKeyException {
        courseDao.add(course);
    }

    @Override
    public boolean deleteCourse(String title) throws NoSuchCourseException {
        return courseDao.delete(title);
    }

    @Override
    public boolean deleteCourse(ObjectId id) throws NoSuchCourseException {
        return courseDao.delete(id);
    }

    @Override
    public List<Course> getAllCourses() {
       return courseDao.getAll();
    }

    @Override
    public Course findCourseByTitle(String title) throws NoSuchCourseException {
        return courseDao.find(title);
    }

    @Override
    public Course findCourseById(ObjectId id) throws NoSuchCourseException {
        return courseDao.find(id);
    }

    @Override
    public int sizeOfCourse() {
        return courseDao.size();
    }


    //Group methods
    @Override
    public void addGroup(UserGroup group) throws DuplicateKeyException {
        userGroupDao.addGroup(group);
    }

    @Override
    public boolean deleteGroup(String name) throws NoSuchGroupException {
        return userGroupDao.delete(name);
    }

    @Override
    public boolean isExistGroup(String name) {
        return userGroupDao.isExist(name);
    }

    @Override
    public int sizeOfGroup() {
        return userGroupDao.size();
    }

    @Override
    public UserGroup findUserGroupByName(String name) throws NoSuchGroupException {
        return userGroupDao.find(name);
    }

    @Override
    public UserGroup findUserGroupById(ObjectId id) throws NoSuchGroupException {
        return userGroupDao.find(id);
    }

    @Override
    public void addUserToGroup(String name, User user) throws DuplicateDataException, NoSuchGroupException {
        userGroupDao.addUserToGroup(name,user);
    }

    @Override
    public void updateGroup(ObjectId id,UserGroup userGroup) throws DuplicateDataException, NoSuchGroupException {
        userGroupDao.update(id,userGroup);
    }

    @Override
    public List<UserGroup> getAllGroups() throws AppException {
        return userGroupDao.getAll();
    }

}
