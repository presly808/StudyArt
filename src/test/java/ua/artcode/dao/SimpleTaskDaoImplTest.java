package ua.artcode.dao;


import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.artcode.db.CodingBatTaskContainer;
import ua.artcode.model.CodingBatTask;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;


/**
 * Created by Razer on 10.11.15.
 */

public class SimpleTaskDaoImplTest {
    private static SimpleTaskDao simpleTaskDao;

    @BeforeClass
    public static void initDao() {
        CodingBatTaskContainer codingBatTaskContainer = new CodingBatTaskContainer();
        simpleTaskDao = new SimpleTaskDaoImpl(codingBatTaskContainer);
    }

    @Before
    public void initTask() {
        CodingBatTask mockTask = mock(CodingBatTask.class);
        CodingBatTask mockTask1 = mock(CodingBatTask.class);
        simpleTaskDao.addTask(mockTask);
        simpleTaskDao.addTask(mockTask1);
    }

    //TODO after delete size-1
    @Test
    public void deleteTask() {
        assertTrue(simpleTaskDao.delete("1"));
    }


}
