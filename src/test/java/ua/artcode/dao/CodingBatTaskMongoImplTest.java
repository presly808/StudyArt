package ua.artcode.dao;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.springframework.context.ApplicationContext;
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

/**
 * Created by Razer on 14.12.15.
 */
public class CodingBatTaskMongoImplTest {
    private static final Logger LOG = Logger.getLogger(CodingBatTaskMongoImplTest.class);
    private static CodingBatTaskDao codingBatTaskDao;
    private static ApplicationContext context;
    private static Datastore datastore;
    private static final int AMOUNT_OF_ELEMENTS = 1000;


    @BeforeClass
    public static void initializeDB() throws InterruptedException, AppValidationException {
        context = SpringContext.getContext();
        //String mongoDataPath = AppPropertiesHolder.getProperty("mongo.data.db.path");
        try {
            Process process = Runtime.getRuntime().exec("mongod --dbpath /Users/johnsmith/Mongodb/data/db");
            //LOG.warn((getData(process.getInputStream())));
            process.waitFor();
        } catch (IOException e) {
            LOG.error(e);
        }
        datastore = (Datastore) context.getBean("testStore");
        codingBatTaskDao = new CodingBatTaskDaoMongoImpl(datastore);
        String value;
        for (int i = 0; i < AMOUNT_OF_ELEMENTS; i++) {
            value = Integer.toString(i);
            CodingBatTask task = new CodingBatTask("p1000".concat(value), "Title-".concat(value), "Simple description-".concat(value),
                    "simple example() -> true",
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
    public void findByIdTest() throws AppValidationException, NoSuchTaskException {
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
    public void updateTest(){
        //codingBatTaskDao.update();
    }

    @Test
    public void removeTest() throws AppValidationException, NoSuchTaskException {
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
