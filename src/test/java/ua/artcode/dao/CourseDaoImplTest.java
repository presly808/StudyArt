package ua.artcode.dao;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.artcode.exception.AppException;
import ua.artcode.model.Course;
import ua.artcode.model.Lesson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by Razer on 02.02.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/app-context.xml")
public class CourseDaoImplTest {

    private static final Logger LOG = Logger.getLogger(TaskMongoImplTest.class);

    private final int AMOUNT_OF_ELEMENTS = 100;

    @Autowired
    @Qualifier("testStore")
    private Datastore datastore;

    @Value("${mongo.test.db}")
    private String nameOfTestDb;

    @Autowired
    @Qualifier("courseDaoTestImpl")
    private CourseDao courseDao;

    @Before
    public void initializeDb() throws InterruptedException, AppException {
        try {
            Process process = Runtime.getRuntime().exec("mongod --dbpath /Users/johnsmith/Mongodb/data/db");
            //LOG.warn((getData(process.getInputStream())));
            process.waitFor();
        } catch (IOException e) {
            LOG.error(e);
        }
        String value;
        List<Lesson> lessons = createLessons();
        for (int i = 0; i < AMOUNT_OF_ELEMENTS; i++) {
            value = Integer.toString(i);
            Course course = new Course("name" + value, lessons);
            courseDao.add(course);
        }
    }

    @Test
    public void sizeTest() {
        int sizeOfdb = courseDao.size();
        assertEquals(sizeOfdb, AMOUNT_OF_ELEMENTS);
    }

    @Test
    public void addCourseTest() throws AppException {
        List<Lesson> lessons = createLessons();
        Course course = new Course("name", lessons);
        courseDao.add(course);
        assertEquals(courseDao.size(),AMOUNT_OF_ELEMENTS+1);
    }

    @After
    public void deleteDb() {
        datastore.getMongo().dropDatabase(nameOfTestDb);
    }

    private List<Lesson> createLessons() {
        List<Lesson> lessons = new ArrayList<>();
        Lesson mockLesson = mock(Lesson.class);
        for (int i = 0; i < 10; i++) {
            lessons.add(mockLesson);
        }
        return lessons;
    }
}
