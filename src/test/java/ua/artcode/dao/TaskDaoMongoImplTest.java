package ua.artcode.dao;

import com.mongodb.DuplicateKeyException;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
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
import ua.artcode.exception.AppValidationException;
import ua.artcode.exception.DuplicateDataException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.common.Task;
import ua.artcode.model.taskComponent.MethodSignature;
import ua.artcode.model.taskComponent.TaskTestData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/app-context.xml")
public class TaskDaoMongoImplTest {

    private static final Logger LOG = Logger.getLogger(TaskDaoMongoImplTest.class);

    @Autowired
    @Qualifier("taskMongoTestImpl")
    private TaskDao taskDao;

    @Autowired
    @Qualifier("testStore")
    private Datastore datastore;

    @Value("${mongo.test.db}")
    private String nameOfTestDb;

    @Value("${mongo.data.db.path}")
    private String mongoDataPath;



    private final int AMOUNT_OF_ELEMENTS = 50;


    @Before
    public void initializeDB() throws InterruptedException, AppException {
        try {
            Process process = Runtime.getRuntime().exec("mongod --dbpath " + mongoDataPath);
//            LOG.warn((getData(process.getInputStream())));
            process.waitFor();
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        String value;
        for (int i = 0; i < AMOUNT_OF_ELEMENTS; i++) {
            value = Integer.toString(i);
            Task task = new Task("title-".concat(value), "Simple description-".concat(value),
                    "methodName(true, false) → false",
                    "public boolean $ome_Method(int arg".concat(value) + ", String arg".concat(value) + ", boolean arg".concat(value) + ") {}", "Group-".concat(value));

            MethodSignature methodSignature = new MethodSignature();
            methodSignature.setReturnType("boolean");
            task.setMethodSignature(methodSignature);


            List<String> inData = new ArrayList<String>();
            inData.add("00".concat(value));
            inData.add("some string");
            inData.add("false");

            List expectedValue = new ArrayList<>();
            expectedValue.add("true");

            TaskTestData taskTestData = new TaskTestData(expectedValue, inData);

            task.getTaskTestDataContainer().addTaskTestData(taskTestData);
            task.getTaskTestDataContainer().addTaskTestData(taskTestData);
            task.getTaskTestDataContainer().addTaskTestData(taskTestData);
            taskDao.add(task);
        }
    }

    @Test
    public void findByTitleTest() throws AppException {
        Task task = taskDao.find("title-10");
        assertEquals(task.getTitle(), "title-10");
    }

    @Test(expected = NoSuchTaskException.class)
    public void findByIdExceptionTest() throws NoSuchTaskException {
        taskDao.find(new ObjectId());
    }

    @Test(expected = NoSuchTaskException.class)
    public void negativeFindByIdTest() throws NoSuchTaskException {
        taskDao.find(" ");
    }

    @Test
    public void getAllTest() {
        List<Task> tasks = taskDao.getAll();
        assertEquals(tasks.size(), taskDao.size());
    }

    @Test
    public void getGroupsTest() {
        List<String> gropList = taskDao.getGroups();
        assertEquals(gropList.size(), AMOUNT_OF_ELEMENTS);
    }

    @Test
    public void getGroupTasksTest() throws NoSuchTaskException {
        Task task = taskDao.find("title-10");
        List<Task> codingBatTaskList = taskDao.getGroupTasks(task.getGroupName());
        assertEquals(codingBatTaskList.size(), 1);
    }

    @Test
    public void sizeTest() {
        int sizeOfdb = taskDao.size();
        assertEquals(sizeOfdb, AMOUNT_OF_ELEMENTS);
    }

    @Test
    public void updateTest() throws AppException {
        Task newTask = taskDao.find("title-25");
        newTask.setTitle("title-010111");
        Task taskToUpdate = taskDao.find("title-17");
        taskDao.update(taskToUpdate.getId(), newTask);
        assertEquals(newTask.getTitle(), taskDao.find("title-010111").getTitle());
    }
    //TODO?
    @Test(expected = DuplicateDataException.class)
    public void negativeUpdateTest() throws NoSuchTaskException, DuplicateDataException {
        Task newTask = taskDao.find("title-22");
        Task taskToDelete = taskDao.find("title-25");
        taskDao.update(taskToDelete.getId(),newTask);
    }

    @Test
    public void deleteTest() throws AppException {
        int sizeBeforeRemove = taskDao.size();
        taskDao.delete("title-5");
        int sizeAfterDel = taskDao.size();
        assertEquals(sizeBeforeRemove, sizeAfterDel + 1);
    }

    @Test(expected =NoSuchTaskException.class )
    public void deleteExceptionTest() throws NoSuchTaskException {
        taskDao.delete(new ObjectId());
    }

    @Test(expected = NoSuchTaskException.class)
    public void negativeDeleteTest() throws NoSuchTaskException {
        taskDao.delete("");
    }

    @Test
    public void isExistTest() throws AppValidationException {
        assertTrue(taskDao.isExist("title-0"));
    }

    @Test
    public void negativeIsExistTest() {
        assertFalse(taskDao.isExist("p0"));
    }

    @Test
    public void addTest() throws AppException {
        Task task= new Task();
        taskDao.add(task);
        assertEquals(taskDao.size(), AMOUNT_OF_ELEMENTS + 1);
    }

    @Test(expected = DuplicateKeyException.class)
    public void negativeAddTest() throws DuplicateKeyException {
        Task task= new Task();
        task.setTitle("title-1");
        taskDao.add(task);
    }

    @After
    public void deleteDb() {
        datastore.getMongo().dropDatabase(nameOfTestDb);
    }

}
