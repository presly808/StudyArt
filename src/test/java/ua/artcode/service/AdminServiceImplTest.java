package ua.artcode.service;


import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
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
import ua.artcode.dao.TaskDao;
import ua.artcode.exception.AppException;
import ua.artcode.exception.DuplicateDataException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.common.Task;
import ua.artcode.model.taskComponent.MethodSignature;
import ua.artcode.model.taskComponent.TaskTestData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/app-context.xml")
public class AdminServiceImplTest {

    private static final Logger LOG = Logger.getLogger(UserServiceImplTest.class);


    @Autowired
    private  AdminServiceImpl adminService;

    @Autowired
    @Qualifier("testStore")
    private Datastore datastore;

    @Value("${mongo.data.db.path}")
    private String mongoDataPath;

    @Value("${mongo.test.db}")
    private String nameOfTestDb;


    @Qualifier("taskMongoTestImpl")
    @Autowired
    private TaskDao taskDao;

    private final int AMOUNT_OF_TASKS = 10;


    @Before
    public void initializeDB() throws InterruptedException, AppException {
        ReflectionTestUtils.setField(adminService, "taskDao", taskDao);
        try {
            Process process = Runtime.getRuntime().exec("mongod --dbpath " + mongoDataPath);
//            LOG.warn((getData(process.getInputStream())));
            process.waitFor();
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
        String value;
        for (int i = 0; i < AMOUNT_OF_TASKS; i++) {
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
    public void sizeTest() {
        Assert.assertEquals(AMOUNT_OF_TASKS, adminService.size());
    }

    @Test
    public void findByTitleTest() throws AppException {
        Task task = adminService.findTaskByTitle("title-3");
        assertEquals(task.getTitle(), "title-3");
    }

    @Test
    public void findByIdTest() throws AppException {
        Task task=adminService.findTaskByTitle("title-3");
        Task task1 = adminService.findTaskById(task.getId());
        assertEquals(task,task1);
    }

    @Test(expected = NoSuchTaskException.class)
    public void findByIdExceptionTest() throws NoSuchTaskException {
        adminService.findTaskById(new ObjectId());
    }

    @Test(expected = NoSuchTaskException.class)
    public void negativeFindByTitleTest() throws NoSuchTaskException {
        adminService.findTaskByTitle("");
    }

    @Test
    public void deleteByIdTest() throws AppException {
        Task task=adminService.findTaskByTitle("title-5");
        int sizeBeforeRemove = adminService.size();
        adminService.deleteById(task.getId());
        assertEquals(sizeBeforeRemove-1, adminService.size());
    }

    @Test
    public void deleteByTitleTest() throws AppException {
        int sizeBeforeRemove = adminService.size();
        adminService.deleteByTitle("title-5");
        assertEquals(sizeBeforeRemove-1, adminService.size());
    }

    @Test
    public void addTest() throws AppException {
        Task task= new Task();
        adminService.addTask(task);
        assertEquals(adminService.size(), AMOUNT_OF_TASKS + 1);
    }

    @Test
    public void getAllTest() {
        List<Task> tasks = adminService.getAllTasks();
        assertEquals(tasks.size(), adminService.size());
    }

    @Test
    public void getGroupsTest() {
        List<String> gropList = adminService.getGroups();
        assertEquals(gropList.size(), AMOUNT_OF_TASKS);
    }

    @Test
    public void getGroupTasksTest() throws NoSuchTaskException {
        Task task = adminService.findTaskByTitle("title-1");
        List<Task> codingBatTaskList = adminService.getGroupTasks(task.getGroupName());
        assertEquals(codingBatTaskList.size(), 1);
    }

    @Test
    public void updateTest() throws NoSuchTaskException, DuplicateDataException {
        Task newTask = adminService.findTaskByTitle("title-5");
        newTask.setTitle("title-010111");
        Task taskToUpdate = adminService.findTaskByTitle("title-7");
        adminService.update(taskToUpdate.getId(), newTask);
        assertEquals(newTask.getTitle(), adminService.findTaskByTitle("title-010111").getTitle());
    }

    @After
    public void deleteDb() {
        datastore.getMongo().dropDatabase(nameOfTestDb);
    }
}
