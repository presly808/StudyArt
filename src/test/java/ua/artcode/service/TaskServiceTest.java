package ua.artcode.service;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import ua.artcode.dao.SimpleTaskDao;
import ua.artcode.dao.SimpleTaskDaoImpl;
import ua.artcode.db.CodingBatTaskContainer;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.CodingBatTask;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//TODO finish tests
@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    private static final Logger LOGGER = Logger.getLogger(TaskServiceTest.class);
    private static SimpleTaskDao mockTaskDao;

    private CodingBatTaskContainer taskContainer;
    private SimpleTaskDaoImpl simpleTaskDao;
    private SimpleTaskServiceImpl simpleTaskService;

    @BeforeClass
    public static void initMocks() {
        mockTaskDao = mock(SimpleTaskDao.class);

        try {
            when(mockTaskDao.getAll()).thenReturn(Arrays.asList(new CodingBatTask("1", "sum", "desc1", "ex1", "template1")));
        } catch (AppException e) {
            LOGGER.error(e);
        }
    }

    @Before
    public void initTasks() {
        taskContainer = new CodingBatTaskContainer();
        simpleTaskDao = new SimpleTaskDaoImpl(taskContainer);
        simpleTaskService = new SimpleTaskServiceImpl(simpleTaskDao);

        CodingBatTask mockTask1 = mock(CodingBatTask.class);
        CodingBatTask mockTask2 = mock(CodingBatTask.class);
        CodingBatTask mockTask3 = mock(CodingBatTask.class);
        CodingBatTask mockTask4 = mock(CodingBatTask.class);
        CodingBatTask mockTask5 = mock(CodingBatTask.class);

        when(mockTask1.getId()).thenReturn("0");
        when(mockTask2.getId()).thenReturn("1");
        when(mockTask3.getId()).thenReturn("2");
        when(mockTask4.getId()).thenReturn("3");
        when(mockTask5.getId()).thenReturn("4");

        taskContainer.addTask(mockTask1);
        taskContainer.addTask(mockTask2);
        taskContainer.addTask(mockTask3);
        taskContainer.addTask(mockTask4);
        taskContainer.addTask(mockTask5);
    }


    @Test(expected = NoSuchTaskException.class)
    public void findTaskByIdWithNoTask() throws NoSuchTaskException {
        simpleTaskService.getTask("noId");
    }

    @Test
    public void getAllTasks() throws AppException {
        assertEquals(5, simpleTaskService.getAll().size());
    }

    @Test
    public void getTask() throws NoSuchTaskException {
        assertEquals("2", simpleTaskService.getTask("12").getId());
    }

}
