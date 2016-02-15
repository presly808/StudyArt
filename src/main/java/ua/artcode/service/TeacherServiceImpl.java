package ua.artcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.artcode.dao.CourseDao;
import ua.artcode.dao.GroupDao;
import ua.artcode.dao.LessonDao;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchCourseException;
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

    @Autowired
    private LessonDao lessonDao;

    @Autowired
    private GroupDao groupDao;


    @Override
    public void addLesson(Lesson lesson) {
        lessonDao.addLesson(lesson);
    }

    @Override
    public List<Lesson> getAllLessons() {
        return lessonDao.getAll();
    }

    @Override
    public void addTaskToLesson(String title, CodingBatTask codingBatTask) throws NoSuchLessonException {
        lessonDao.addTask(title,codingBatTask);
    }

    @Override
    public int sizeOfLesson() {
        return lessonDao.size();
    }

    @Override
    public void updateLesson(Lesson lesson) throws NoSuchLessonException {
        lessonDao.updateLesson(lesson);
    }

    @Override
    public Lesson findByTitleLesson(String title) throws NoSuchLessonException {
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
    public List<Course> getAllCourses() {
       return courseDao.getAll();
    }

    @Override
    public Course findByTitleCourse(String title) throws NoSuchCourseException {
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
       return groupDao.addGroup(group);
    }

    @Override
    public boolean deleteGroup(String name) {
        return groupDao.delete(name);
    }

    @Override
    public boolean isExistGroup(String name) {
        return groupDao.isExist(name);
    }

    @Override
    public int sizeOfGroup() {
        return groupDao.size();
    }

    @Override
    public List<UserGroup> getAll() throws AppException {
        return groupDao.getAll();
    }
}
