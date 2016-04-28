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
import ua.artcode.exception.*;
import ua.artcode.model.common.Lesson;
import ua.artcode.model.common.Task;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Razer on 15.02.16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/app-context.xml")
public class LessonDaoMongoImplTest {

    private static final Logger LOG = Logger.getLogger(LessonDaoMongoImplTest.class);

    private final int AMOUNT_OF_ELEMENTS = 10;

    @Autowired
    @Qualifier("testStore")
    private Datastore datastore;

    @Qualifier("lessonDaoImplTest")
    @Autowired
    private LessonDao lessonDao;

    @Autowired
    @Qualifier("taskMongoTestImpl")
    private TaskDao taskDao;

    @Value("${mongo.test.db}")
    private String nameOfTestDb;

    @Before
    public void initializeDB() throws InterruptedException, AppException {
        try {
            Process process = Runtime.getRuntime().exec("mongod --dbpath /Users/johnsmith/Mongodb/data/db");
//            LOG.warn((getData(process.getInputStream())));
            process.waitFor();
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        String value;
//        List<Task> tasks = createTasks();
        for (int i = 0; i < AMOUNT_OF_ELEMENTS; i++) {
            value = Integer.toString(i);
            Lesson lesson = new Lesson("title-".concat(value), "description-".concat(value));
            lessonDao.add(lesson);
        }

    }

    @Test
    public void findByTitleTest() throws AppException {
        Lesson lesson = lessonDao.find("title-10");
        assertEquals(lesson.getTitle(), "title-10");
    }

    @Test
    public void isExistTest() throws AppValidationException {
        assertTrue(lessonDao.isExist("title-0"));
    }

    @Test
    public void isExistNegativeTest() {
        assertFalse(lessonDao.isExist("t0"));
    }

    @Test(expected = NoSuchLessonException.class)
    public void findByIdExceptionTest() throws NoSuchLessonException {
        lessonDao.find(new ObjectId());
    }

    @Test(expected = NoSuchLessonException.class)
    public void findByTitleExceptionTest() throws NoSuchLessonException {
        lessonDao.find("");
    }

    @Test
    public void sizeTest() {
        int sizeOfdb = lessonDao.size();
        assertEquals(sizeOfdb, AMOUNT_OF_ELEMENTS);
    }

    @Test
    public void deleteTest() throws AppException {
        int sizeBeforeRemove = lessonDao.size();
        lessonDao.delete("title-5");
        int sizeAfterDel = lessonDao.size();
        assertEquals(sizeBeforeRemove, sizeAfterDel + 1);
    }

    @Test(expected = NoSuchLessonException.class)
    public void negativeDeleteTest() throws NoSuchLessonException {
        lessonDao.delete(new ObjectId());
    }

    @Test(expected = NoSuchLessonException.class)
    public void negativeRemoveTest() throws NoSuchLessonException {
        lessonDao.delete("");
    }

    @Test
    public void getAllTest() {
        List<Lesson> lessons = lessonDao.getAll();
        assertEquals(lessons.size(), lessonDao.size());
    }

    @Test
    public void addTaskToLesson() throws DuplicateDataException, NoSuchLessonException, NoSuchTaskException {
        Lesson lesson = lessonDao.find("title-1");
        int sizeBeforeAdd = lesson.size();
        taskDao.add(new Task("title-000", "Simple description-000",
                "methodName(true, false) → false",
                "public boolean $ome_Method(int arg-000", "String arg-000" + ", boolean arg"));
        lessonDao.addTask("title-1", taskDao.find("title-000"));
        Lesson lessonAfterUpdate = lessonDao.find("title-1");
        assertEquals(sizeBeforeAdd + 1, lessonAfterUpdate.size());
    }

    @Test
    public void addTest() throws AppException {
        Lesson lesson = new Lesson("title", "description");
        lessonDao.add(lesson);
        assertEquals(lessonDao.size(), AMOUNT_OF_ELEMENTS + 1);
    }

    @Ignore
    @Test(expected = DuplicateKeyException.class)
    public void negativeAddTest() throws DuplicateKeyException {
        Lesson lesson = new Lesson("title-0", "description-0");
        lessonDao.add(lesson);
    }

    @After
    public void deleteDb() {
        datastore.getMongo().dropDatabase(nameOfTestDb);
    }

//    private List<Task> createTasks() {
//        List<Task> tasks = new ArrayList<>();
//        Task mockLesson = mock(Task.class);
//        for (int i = 0; i < 10; i++) {
//            tasks.add(mockLesson);
//        }
//        return tasks;
//    }

}
