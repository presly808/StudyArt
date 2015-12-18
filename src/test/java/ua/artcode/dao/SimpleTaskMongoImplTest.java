package ua.artcode.dao;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.springframework.context.ApplicationContext;
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.utils.AppPropertiesHolder;
import ua.artcode.utils.SpringContext;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static ua.artcode.trigger.InitCodingBatTaskTrigger.getData;

/**
 * Created by Razer on 14.12.15.
 */
public class SimpleTaskMongoImplTest {
    private static final Logger LOG = Logger.getLogger(SimpleTaskMongoImplTest.class);
    private static SimpleTaskDao simpleTaskDao;
    private static ApplicationContext context;
    private static Datastore datastore;

    @BeforeClass
    public static void initializeDB() throws InterruptedException {
        try {
            //TODO show commandline result of start server
            Process process = Runtime.getRuntime().exec("mongod --dbpath /Users/johnsmith/Mongodb/data/db");
            LOG.debug((getData(process.getErrorStream())));
            process.waitFor();
        } catch (IOException e) {
            LOG.error(e);
        }
        context = SpringContext.getContext();
        datastore = (Datastore) context.getBean("testStore");
        simpleTaskDao = new SimpleTaskDaoMongoImpl(datastore);
        //CodingBatTask mockTask = mock(CodingBatTask.class);
        simpleTaskDao.addTask(new CodingBatTask("342324", "2", "2", "2", "2", "2"));
//        for (int i = 0; i < 5; i++) {
//            when(mockTask.getCodingBatId()).thenReturn(Integer.toString(i));
//            simpleTaskDao.addTask(mockTask);
//        }
    }


    @Test
    public void sizeTest() {
        int sizeOfdb = simpleTaskDao.size();
        assertEquals(sizeOfdb, 1);
    }

    @Test
    public void removeTest() {
        simpleTaskDao.addTask(new CodingBatTask("123785", "1", "1", "1", "1", "1"));
        int sizeBeforeRemove = simpleTaskDao.size();
        simpleTaskDao.delete("123785");
        int sizeAfterDel = simpleTaskDao.size();
        assertEquals(sizeBeforeRemove, sizeAfterDel + 1);
    }

    @Test
    public void invalidRemoveTest() {
        simpleTaskDao.addTask(new CodingBatTask("45678", "1", "1", "1", "1", "1"));
        int sizeBeforeRemove = simpleTaskDao.size();
        simpleTaskDao.delete("");
        int sizeAfterDel = simpleTaskDao.size();
        assertEquals(sizeBeforeRemove, sizeAfterDel);
        simpleTaskDao.delete("45678");
    }

    @AfterClass
    public static void deleteDb() {
        //camelcase
        String nameOfTestDb = AppPropertiesHolder.getProperty("mongo.test.db");
        //String nameOfTestDb=context.getEnvironment().getProperty("mongo.test.db");
        datastore.getMongo().dropDatabase(nameOfTestDb);
    }

}
