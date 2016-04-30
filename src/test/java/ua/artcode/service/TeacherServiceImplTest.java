package ua.artcode.service;

import com.mongodb.DuplicateKeyException;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import ua.artcode.dao.CourseDao;
import ua.artcode.dao.LessonDao;
import ua.artcode.exception.AppException;
import ua.artcode.model.common.Course;
import ua.artcode.model.common.Lesson;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static ua.artcode.script.InitCodingBatTaskTrigger.getData;

/**
 * Created by Razer on 30.04.16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/app-context.xml")
public class TeacherServiceImplTest {

    private static final Logger LOG = Logger.getLogger(UserServiceImplTest.class);


    @Autowired
    private TeacherServiceImpl teacherService;

    @Autowired
    @Qualifier("testStore")
    private Datastore datastore;

    @Value("${mongo.data.db.path}")
    private String mongoDataPath;

    @Value("${mongo.test.db}")
    private String nameOfTestDb;


    @Qualifier("courseDaoTestImpl")
    @Autowired
    private CourseDao courseDao;


    @Qualifier("lessonDaoImplTest")
    @Autowired
    private LessonDao lessonDao;

    private final int AMOUNT_OF_ELEMENTS = 10;


    @Before
    public void initializeDB() throws InterruptedException, DuplicateKeyException {
        ReflectionTestUtils.setField(teacherService, "courseDao", courseDao);
        ReflectionTestUtils.setField(teacherService, "lessonDao", lessonDao);
        try {
            Process process = Runtime.getRuntime().exec("mongod --dbpath " + mongoDataPath);
            LOG.info(getData(process.getInputStream()));
            LOG.debug((getData(process.getErrorStream())));
            process.waitFor();
        } catch (IOException e) {
            LOG.error(e);
        }
        String value;
        for (int i = 0; i < AMOUNT_OF_ELEMENTS; i++) {
            value = Integer.toString(i);
            Lesson lesson = new Lesson("title-".concat(value), "description-".concat(value));
            lessonDao.add(lesson);
            Course course = new Course("title-" + value, "description-" + value);
            courseDao.add(course);
        }
    }

    @Test
    public void sizeTest() {
        Assert.assertEquals(AMOUNT_OF_ELEMENTS, teacherService.sizeOfLesson());
    }

    @Test
    public void deleteByTitleCourseTest() throws AppException {
        int sizeBeforeRemove = teacherService.sizeOfCourse();
        teacherService.deleteCourse("title-5");
        int sizeAfterDel = teacherService.sizeOfCourse();
        assertEquals(sizeBeforeRemove-1, sizeAfterDel);
    }

    @Test
    public void deleteByIdCourseTest() throws AppException {
        int sizeBeforeRemove = teacherService.sizeOfCourse();
        Course course = teacherService.findCourseByTitle("title-5");
        teacherService.deleteCourse(course.getId());
        assertEquals(sizeBeforeRemove, teacherService.sizeOfCourse() + 1);
    }


    @After
    public void deleteDb() {
        datastore.getMongo().dropDatabase(nameOfTestDb);
    }
}

