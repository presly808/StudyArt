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
import ua.artcode.exception.AppValidationException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.Task;
import ua.artcode.model.codingbat.MethodSignature;
import ua.artcode.model.codingbat.TaskTestData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/app-context.xml")
public class TaskMongoImplTest {

    private static final Logger LOG = Logger.getLogger(TaskMongoImplTest.class);


    @Autowired
    @Qualifier("сodingBatTaskMongoTestImpl")
    private TaskDao taskDao;

    @Autowired
    @Qualifier("testStore")
    private Datastore datastore;

    @Value("${mongo.test.db}")
    private String nameOfTestDb;


    private final int AMOUNT_OF_ELEMENTS = 100;


    @Before
    public void initializeDB() throws InterruptedException, AppException {
        try {
            Process process = Runtime.getRuntime().exec("mongod --dbpath /Users/johnsmith/Mongodb/data/db");
            //LOG.warn((getData(process.getInputStream())));
            process.waitFor();
        } catch (IOException e) {
            LOG.error(e);
        }
        String value;
        for (int i = 0; i < AMOUNT_OF_ELEMENTS; i++) {
            value = Integer.toString(i);
            Task task = new Task("p1000".concat(value), "Simple description-".concat(value),
                    "methodName(true, false) → false",
                    "public boolean $ome_Method(int arg".concat(value) + ", String arg".concat(value) + ", boolean arg".concat(value) + ") {}", "Group-".concat(value));

            MethodSignature methodSignature = new MethodSignature();
            methodSignature.setReturnType("boolean");
            task.setMethodSignature(methodSignature);


            List<String> inData = new ArrayList<String>();
            inData.add("00".concat(value));
            inData.add("some string");
            inData.add("false");

            String expectedValue = "true";

            TaskTestData taskTestData = new TaskTestData(expectedValue, inData);

            task.getTaskTestDataContainer().addTaskTestData(taskTestData);
            task.getTaskTestDataContainer().addTaskTestData(taskTestData);
            task.getTaskTestDataContainer().addTaskTestData(taskTestData);
            taskDao.add(task);
        }
    }

    @Test
    public void findByTitleTest() throws AppException {
        Task task = taskDao.find("p10009");
        assertEquals(task.getTitle(), "p10009");
    }

    @Test(expected = NoSuchTaskException.class)
    public void findByIdExceptionTest() throws NoSuchTaskException {
        taskDao.find(" ");
    }

    @Test
    public void getAllTest() {
        List<Task> tasks=taskDao.getAll();
        int sizeOfList = tasks.size();
        int sizeOfDb = taskDao.size();
        assertEquals(sizeOfDb, sizeOfList);
    }

    @Test
    public void getGroupsTest() {
        List<String> gropList = taskDao.getGroups();
        assertEquals(gropList.size(),AMOUNT_OF_ELEMENTS);
    }

    @Test
    public void getGroupTasksTest() throws NoSuchTaskException {
        Task task = taskDao.find("p100010");
        List<Task> codingBatTaskList = taskDao.getGroupTasks(task.getGroupName());
        assertEquals(codingBatTaskList.size(),1 );
    }

    @Test
    public void sizeTest() {
        int sizeOfdb = taskDao.size();
        assertEquals(sizeOfdb, AMOUNT_OF_ELEMENTS);
    }

    @Test
    public void updateTest() throws AppException {
        Task newTask = taskDao.find("p100025");
        Task taskToUpdate = taskDao.find("p100017");
        //TODO
        //taskDao.update("p100017", newTask);
        assertEquals(taskToUpdate.getTitle(), taskDao.find("p100017").getTitle());
        taskToUpdate.setTitle("p1000".concat(String.valueOf(AMOUNT_OF_ELEMENTS + 1)));
        taskDao.add(taskToUpdate);
    }

    @Test
    public void removeTest() throws AppException {
        Task task = taskDao.find("p10005");
        task.setTitle("p666666");
        taskDao.add(task);
        int sizeBeforeRemove = taskDao.size();
        taskDao.delete("p666666");
        int sizeAfterDel = taskDao.size();
        assertEquals(sizeBeforeRemove, sizeAfterDel + 1);
    }

    @Test
    public void invalidRemoveTest() throws AppValidationException, NoSuchTaskException {
        assertFalse(taskDao.delete(""));
    }

    @Test
    public void isExistTest() throws AppValidationException {
        assertTrue(taskDao.isExist("p10000"));
    }

    @Test
    public void isExistNegativeTest() {
        assertFalse(taskDao.isExist("p0"));
    }

    @After
    public void deleteDb() {
        datastore.getMongo().dropDatabase(nameOfTestDb);
    }

}
