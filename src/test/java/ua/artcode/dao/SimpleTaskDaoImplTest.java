package ua.artcode.dao;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.artcode.db.CodingBatTaskContainer;
import ua.artcode.exception.AppException;
import ua.artcode.model.codingbat.CodingBatTask;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;


/**
 * Created by Razer on 10.11.15.
 */

public class SimpleTaskDaoImplTest {
    private static SimpleTaskDao simpleTaskDao;


    @Before
    public void initTask() {
        CodingBatTaskContainer codingBatTaskContainer = new CodingBatTaskContainer();
        simpleTaskDao = new SimpleTaskDaoImpl(codingBatTaskContainer);
        for (int i = 0; i < 5; i++) {
            CodingBatTask mockTask = mock(CodingBatTask.class);
            simpleTaskDao.addTask(mockTask);
        }
    }

    @After
    public void cleanCount() {
        CodingBatTaskContainer.setCount(0);
    }

    @Test
    public void deleteTask() {
        assertTrue(simpleTaskDao.delete("1"));
    }

    @Test
    public void deleteTaskByDecSize() throws AppException {
        int sizeBeforeDel = getSize();
        simpleTaskDao.delete("2");
        assertEquals(sizeBeforeDel - 1, getSize());
    }

    public int getSize() throws AppException {
        return simpleTaskDao.getAll().size();
    }


}
