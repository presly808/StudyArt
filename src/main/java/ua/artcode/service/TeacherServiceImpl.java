package ua.artcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.artcode.dao.CourseDao;
import ua.artcode.dao.UserGroupDao;
import ua.artcode.dao.LessonDao;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchCourseException;
import ua.artcode.exception.NoSuchGroupException;
import ua.artcode.exception.NoSuchLessonException;
import ua.artcode.model.Course;
import ua.artcode.model.common.UserGroup;
import ua.artcode.model.Lesson;
import ua.artcode.model.codingbat.CodingBatTask;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Qualifier("courseDaoImpl")
    @Autowired()
    private CourseDao courseDao;

    @Qualifier("lessonDaoImpl")
    @Autowired
    private LessonDao lessonDao;

    @Qualifier("groupDaoImpl")
    @Autowired
    private UserGroupDao userGroupDao;


    @Override
    public void addLesson(Lesson lesson) throws AppException {
        lessonDao.addLesson(lesson);
    }

    @Override
    public List<Lesson> getAllLessons() {
        return lessonDao.getAll();
    }

    @Override
    public void addTaskToLesson(String title, CodingBatTask codingBatTask) throws NoSuchLessonException, AppException {
        lessonDao.addTask(title,codingBatTask);
    }

    @Override
    public int sizeOfLesson() {
        return lessonDao.size();
    }

    @Override
    public void updateLesson(Lesson lesson) throws NoSuchLessonException, AppException {
        lessonDao.updateLesson(lesson);
    }

    @Override
    public Lesson findLessonByTitle(String title) throws NoSuchLessonException {
        return lessonDao.findByTitle(title);
    }

    @Override
    public boolean deleteLesson(String title) throws NoSuchLessonException {
        return lessonDao.delete(title);
    }


    @Override
    public Course addCourse(Course course) {
       return courseDao.addCourse(course);
    }

    @Override
    public boolean deleteCourse(String title) throws NoSuchCourseException {
        return courseDao.delete(title);
    }

    @Override
    public List<Course> getAllCourses() {
       return courseDao.getAll();
    }

    @Override
    public Course findCourseByTitle(String title) throws NoSuchCourseException {
        return courseDao.findByTitle(title);
    }

    @Override
    public int sizeOfCourse() {
        return courseDao.size();
    }

    @Override
    public void updateCourse(Course course) throws NoSuchCourseException {
        courseDao.updateCourse(course);
    }


    @Override
    public UserGroup addGroup(UserGroup group){
       return userGroupDao.addGroup(group);
    }

    @Override
    public boolean deleteGroup(String name) {
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
        return userGroupDao.findByName(name);
    }

    @Override
    public List<UserGroup> getAllUsers() throws AppException {
        return userGroupDao.getAll();
    }
}
