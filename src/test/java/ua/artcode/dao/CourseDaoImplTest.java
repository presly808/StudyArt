package ua.artcode.dao;

import com.mongodb.DuplicateKeyException;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.artcode.exception.AppException;
import ua.artcode.exception.AppValidationException;
import ua.artcode.exception.NoSuchCourseException;
import ua.artcode.model.common.Course;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Razer on 02.02.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/app-context.xml")
public class CourseDaoImplTest {

    private static final Logger LOG = Logger.getLogger(CourseDaoImplTest.class);

    private final int AMOUNT_OF_ELEMENTS = 10;

    @Autowired
    @Qualifier("testStore")
    private Datastore datastore;

    @Value("${mongo.test.db}")
    private String nameOfTestDb;

    @Autowired
    @Qualifier("courseDaoTestImpl")
    private CourseDao courseDao;

    @Value("${mongo.data.db.path}")
    private String mongoDataPath;


    @Before
    public void initializeDb() throws InterruptedException, AppException {
        try {
            Process process = Runtime.getRuntime().exec("mongod --dbpath " + mongoDataPath);
            //LOG.warn((getData(process.getInputStream())));
            process.waitFor();
        } catch (IOException e) {
            LOG.error(e);
        }
        String value;
//        List<Lesson> lessons = createLessons();
        for (int i = 0; i < AMOUNT_OF_ELEMENTS; i++) {
            value = Integer.toString(i);
            Course course = new Course("title-" + value, "description-" + value);
            courseDao.add(course);
        }
    }

    @Test
    public void sizeTest() {
        int sizeOfdb = courseDao.size();
        assertEquals(sizeOfdb, AMOUNT_OF_ELEMENTS);
    }

    @Test
    public void deleteByTitleTest() throws AppException {
        int sizeBeforeRemove = courseDao.size();
        courseDao.delete("title-5");
        int sizeAfterDel = courseDao.size();
        assertEquals(sizeBeforeRemove, sizeAfterDel + 1);
    }

    @Test
    public void deleteByIdTest() throws AppException {
        int sizeBeforeRemove = courseDao.size();
        Course course = courseDao.find("title-5");
        courseDao.delete(course.getId());
        assertEquals(sizeBeforeRemove, courseDao.size() + 1);
    }

    @Test
    public void updateTest() throws AppException {
          Course course=courseDao.find("title-1");
          course.setTitle("title-00011134324");
          Course courseToUpdate=courseDao.find("title-2");
          courseDao.update(courseToUpdate.getId(),course);
          assertEquals(course.getTitle(), courseDao.find("title-00011134324").getTitle());
    }

    @Test(expected = NoSuchCourseException.class)
    public void negativeDeleteByIdTest() throws NoSuchCourseException {
        courseDao.delete(new ObjectId());
    }

    @Test(expected = NoSuchCourseException.class)
    public void negativeDeleteTest() throws NoSuchCourseException {
        courseDao.delete("");
    }

    @Test
    public void isExistTest() throws AppValidationException {
        assertTrue(courseDao.isExist("title-0"));
    }

    @Test
    public void isExistNegativeTest() {
        assertFalse(courseDao.isExist("t0"));
    }

    @Test
    public void findByTitleTest() throws AppException {
        Course course = courseDao.find("title-10");
        assertEquals(course.getTitle(), "title-10");
    }

    @Test
    public void findByIdTest() throws AppException {
        Course course = courseDao.find("title-10");
        Course course1=courseDao.find(course.getId());
        assertEquals(course,course1);
    }

    @Test(expected = NoSuchCourseException.class)
    public void negativeFindByIdTest() throws NoSuchCourseException {
        courseDao.find(new ObjectId());
    }

    @Test(expected = NoSuchCourseException.class)
    public void findByIdExceptionTest() throws NoSuchCourseException {
        courseDao.find(" ");
    }

    @Test
    public void getAllTest() {
        List<Course> courses = courseDao.getAll();
        assertEquals(courses.size(), courseDao.size());
    }

    @Test
    public void addTest() throws AppException {
        Course course = new Course("title", "description");
        courseDao.add(course);
        assertEquals(courseDao.size(), AMOUNT_OF_ELEMENTS + 1);
    }

    @Ignore
    @Test(expected = DuplicateKeyException.class)
    public void negativeAddTest() throws DuplicateKeyException {
        Course course = new Course("title-0", "description-0");
        courseDao.add(course);
    }

    @After
    public void deleteDb() {
        datastore.getMongo().dropDatabase(nameOfTestDb);
    }

//    private List<Lesson> createLessons() {
//        List<Lesson> lessons = new ArrayList<>();
//        Lesson mockLesson = mock(Lesson.class);
//        for (int i = 0; i < 10; i++) {
//            lessons.add(mockLesson);
//        }
//        return lessons;
//    }
}
