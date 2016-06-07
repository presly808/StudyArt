package ua.artcode.service;

import com.mongodb.DuplicateKeyException;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.artcode.dao.CourseDao;
import ua.artcode.dao.LessonDao;
import ua.artcode.dao.TaskDao;
import ua.artcode.dao.UserDao;
import ua.artcode.exception.AppException;
import ua.artcode.exception.DuplicateDataException;
import ua.artcode.exception.NoSuchUserException;
import ua.artcode.exception.UserAuthenticationFailException;
import ua.artcode.model.common.*;
import ua.artcode.model.taskComponent.TaskTestResult;
import ua.artcode.utils.Security;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    @Qualifier("userDaoMongoMongoImpl")
    private UserDao userDao;

    @Autowired
    @Qualifier("taskMongoImpl")
    private TaskDao taskDao;

    @Autowired
    @Qualifier("courseDaoImpl")
    private CourseDao courseDao;

    @Autowired
    @Qualifier("lessonDaoImpl")
    private LessonDao lessonDao;

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserDao userDao) {
        this.userDao=userDao;
    }

    @Override
    public User findUserByEmail(String email) throws NoSuchUserException {
        return userDao.findByEmail(email);
    }

    @Override
    public void update(String email, User user) throws NoSuchUserException,DuplicateDataException {
        userDao.update(email,user);
    }

    @Override
    public User findUser(String name) throws NoSuchUserException {
       return userDao.find(name);
    }

    @Override
    public boolean authenticate(String username, String password) throws AppException {
        User user = userDao.find(username);
        if (!user.getPassword().equals(Security.toMd5(password))) {
            throw new UserAuthenticationFailException("Wrong username or password");
        }
        return true;
    }

    @Override
    public void register(User user) throws DuplicateKeyException {
        userDao.add(user);
    }

    @Override
    public void register(String username, String password, String email) throws DuplicateKeyException {
        userDao.add(new User(username, password, email));
    }

    @Override
    public void register(String username, String password, String email, UserType userType) throws DuplicateKeyException {
        userDao.add(new User(username, password, email, userType));
    }

    @Override
    public User getUserInfo(String username) {
        try {
            return userDao.find(username);
        } catch (NoSuchUserException e) {
            return null;
        }
    }

    @Override
    public int size() {
        return userDao.size();
    }

    @Override
    public List<User> search(String keyWord, int offset, int length) {
        return userDao.search(keyWord, offset, length);
    }

    @Override
    public long searchUsersCount(String keyWord) {
        return userDao.searchCount(keyWord);
    }

    @Override
    public long searchLessonsCount(String keyWord) {
        return lessonDao.searchCount(keyWord);
    }

    @Override
    public long searchCoursesCount(String keyWord) {
        return courseDao.searchCount(keyWord);
    }

    @Override
    public long searchTasksCount(String keyWord){
        return taskDao.searchTaskCount(keyWord);
    }

    @Override
    public List<Task> searchTasks(String keyWord, int offset, int length) {
        return taskDao.searchByTitle(keyWord, offset, length);
    }

    @Override
    public List<Course> searchCourses(String keyWord, int offset, int length) {
        return courseDao.search(keyWord, offset, length);
    }

    @Override
    public List<Lesson> searchLessons(String keyWord, int offset, int length) {
        return lessonDao.search(keyWord, offset, length);
    }

    @Override
    public boolean delete(String email) throws NoSuchUserException {
       return userDao.delete(email);
    }


    @Override
    public List<User> getAllUsers() {
        return userDao.getAll();
    }

    @Override
    public Course addUserCourseStatInformation(User user, Course course) {

        /*if(!user.getSubscribedCourses().contains(course)){
            return course;
        }*/

        List<Lesson> lessonList = course.getLessonList();
//        long performedLessons = lessonList.stream().filter((lesson) -> user.getPerformedLesson().contains(lesson)).count();
        long performedLessons = lessonList.stream().filter((lesson) ->
            lesson.getTasks().stream()
                    .allMatch((task -> {
                        TaskTestResult taskTestResult1 = user.getSolvedTaskContainer().get(task.getId());
                        return taskTestResult1 != null && taskTestResult1.getPassedAll();
                    }))
        ).count();

        lessonList.stream().forEach((lesson) -> {
            List<Task> tasks = lesson.getTasks();
            lesson.setAmountTasksSize(tasks.size());
            lesson.setPerformedTasksSize(
                    (int) tasks.stream()
                            .filter(task -> {
                                TaskTestResult taskTestResult = user.getSolvedTaskContainer().get(task.getId());
                                return taskTestResult != null && taskTestResult.getPassedAll();
                            }).count());
        });

        course.setPerformedLesson((int) performedLessons);
        course.setAmountLessons(lessonList.size());

        return course;
    }

    // todo are you sure of this method location?
    @Override
    public void writeResult(User user, TaskTestResult newTaskTestResult, ObjectId taskId) {
        try {
            TaskTestResult oldTaskTestResult = user.getSolvedTask(taskId);

            // todo see at funny conditions
            if (oldTaskTestResult != null) {
                if (!oldTaskTestResult.getPassedAll()) {
                    user.addSolvedTask(taskId, newTaskTestResult);
                } else if (newTaskTestResult.getPassedAll()) {
                    user.addSolvedTask(taskId, newTaskTestResult);
                }
            } else {
                user.addSolvedTask(taskId, newTaskTestResult);
            }
            String email = user.getEmail();

            update(email, user);
        } catch (DuplicateDataException | NoSuchUserException e) {
            LOG.error(e.getMessage());
        }
    }
}
