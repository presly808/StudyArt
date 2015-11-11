package ua.artcode.service;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import ua.artcode.dao.SimpleTaskDao;
import ua.artcode.dao.SimpleTaskDaoImpl;
import ua.artcode.db.CodingBatTaskContainer;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.CodingBatTask;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Ignore
//TODO finish tests
@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    private static final Logger LOGGER = Logger.getLogger(TaskServiceTest.class);
    private static SimpleTaskDao mockTaskDao;

    private static CodingBatTaskContainer taskContainer;
    private static SimpleTaskDaoImpl simpleTaskDao;
    private static SimpleTaskServiceImpl simpleTaskService;

    @BeforeClass
    public static void initMocks() {
        mockTaskDao = mock(SimpleTaskDao.class);

        try {
            when(mockTaskDao.getAll()).thenReturn(Arrays.asList(new CodingBatTask("1", "sum", "desc1", "ex1", "template1")));
        } catch (AppException e) {
            LOGGER.error(e);
        }
    }

    @BeforeClass
    public static void initTasks() {

    }


    @Test(expected = NoSuchTaskException.class)
    public void findTaskByIdWithNoTask() throws NoSuchTaskException {
        simpleTaskService.getTask("noId");
    }

    @Test
    public void getAllTasks() throws AppException {
        //assertEquals(5, simpleTaskService.getAll().size());
    }

    @Test
    public void getTask() throws NoSuchTaskException {
        assertEquals("2", simpleTaskService.getTask("2").getId());
    }
    @Test
    public void addTask()throws AppException{
        CodingBatTask task=new CodingBatTask("TestTask","description","examples","template");
        simpleTaskService.addTask(task);
        assertEquals(0,simpleTaskService.getTask("5").compareTo(task));
    }

}
