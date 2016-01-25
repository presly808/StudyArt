package ua.artcode.dao;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.artcode.exception.AppException;
import ua.artcode.exception.AppValidationException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.model.codingbat.MethodSignature;
import ua.artcode.model.codingbat.TaskTestData;
import ua.artcode.utils.SpringContext;
import ua.artcode.utils.io.AppPropertiesHolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class CodingBatTaskMongoImplTest {

    private static final Logger LOG = Logger.getLogger(CodingBatTaskMongoImplTest.class);
    @Autowired
    private static CodingBatTaskDao codingBatTaskDao;

    @Autowired
    private static Datastore datastore;


    private static final int AMOUNT_OF_ELEMENTS = 100;


    @BeforeClass
    public static void initializeDB() throws InterruptedException, AppValidationException {
        //String mongoDataPath = AppPropertiesHolder.getProperty("mongo.data.db.path");
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
            CodingBatTask task = new CodingBatTask("p1000".concat(value), "Title-".concat(value), "Simple description-".concat(value),
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
            codingBatTaskDao.addTask(task);
        }
    }

    @Test
    public void findByIdTest() throws AppException {
        CodingBatTask task = codingBatTaskDao.findById("p10009");
        assertEquals(task.getCodingBatId(), "p10009");
    }

    @Test(expected = NoSuchTaskException.class)
    public void findByIdExceptionTest() throws NoSuchTaskException {
        codingBatTaskDao.findById(" ");
    }

    @Test
    public void getAllTest() {
        List<CodingBatTask> codingBatTasks = null;
        try {
            codingBatTasks = codingBatTaskDao.getAll();
        } catch (AppException e) {
            LOG.error(e);
        }
        int sizeOfList = codingBatTasks.size();
        int sizeOfDb = codingBatTaskDao.size();
        assertEquals(sizeOfDb, sizeOfList);
    }

    @Test
    public void sizeTest() {
        int sizeOfdb = codingBatTaskDao.size();
        assertEquals(sizeOfdb, AMOUNT_OF_ELEMENTS);
    }

    @Test
    public void updateTest() throws AppException {
        CodingBatTask newTask = codingBatTaskDao.findById("p100025");
        CodingBatTask taskToUpdate = codingBatTaskDao.findById("p100017");
        codingBatTaskDao.update("p100017", newTask);
        assertEquals(taskToUpdate.getCodingBatId(), codingBatTaskDao.findById("p100017").getCodingBatId());
        taskToUpdate.setCodingBatId("p1000".concat(String.valueOf(AMOUNT_OF_ELEMENTS + 1)));
        codingBatTaskDao.addTask(taskToUpdate);
    }

    @Test
    public void removeTest() throws AppException {
        CodingBatTask task = codingBatTaskDao.findById("p10005");
        task.setCodingBatId("p666666");
        codingBatTaskDao.addTask(task);
        int sizeBeforeRemove = codingBatTaskDao.size();
        codingBatTaskDao.delete("p666666");
        int sizeAfterDel = codingBatTaskDao.size();
        assertEquals(sizeBeforeRemove, sizeAfterDel + 1);
    }

    @Test
    public void invalidRemoveTest() throws AppValidationException {
        assertFalse(codingBatTaskDao.delete(""));
    }

    @Test
    public void isExistTest() throws AppValidationException {
        assertTrue(codingBatTaskDao.isExist("p10000"));
    }

    @Test
    public void isExistNegativeTest() {
        assertFalse(codingBatTaskDao.isExist("p0"));
    }

    @AfterClass
    public static void deleteDb() {
        String nameOfTestDb = AppPropertiesHolder.getProperty("mongo.test.db");
        datastore.getMongo().dropDatabase(nameOfTestDb);
    }

}
