package ua.artcode.service;

import org.apache.log4j.Logger;
import org.junit.After;
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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
//TODO finish tests
@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    private static final Logger LOGGER = Logger.getLogger(TaskServiceTest.class);
    private static SimpleTaskDao mockTaskDao;
    private CodingBatTaskContainer taskContainerMock;

    @BeforeClass
    public static void initMocks(){
        mockTaskDao = mock(SimpleTaskDao.class);

        try {
            when(mockTaskDao.getAll()).thenReturn(Arrays.asList(new CodingBatTask("1","sum","desc1", "ex1", "template1")));
        } catch (AppException e) {
            LOGGER.error(e);
        }
    }


    @Test(expected = NoSuchTaskException.class)
    public void findTaskByIdWithNoTask() throws NoSuchTaskException {
        taskContainerMock = mock(CodingBatTaskContainer.class);

        SimpleTaskDaoImpl dao = new SimpleTaskDaoImpl(taskContainerMock);

        dao.findById("notId");
    }

    @Test
    public void getAllTasks() throws AppException {
        CodingBatTaskContainer taskContainer = new CodingBatTaskContainer();
        SimpleTaskDaoImpl dao = new SimpleTaskDaoImpl(taskContainer);
        SimpleTaskServiceImpl simpleTaskService = new SimpleTaskServiceImpl(dao);
        CodingBatTask mockTask1 = mock(CodingBatTask.class);
        CodingBatTask mockTask2 = mock(CodingBatTask.class);
        CodingBatTask mockTask3 = mock(CodingBatTask.class);
        CodingBatTask mockTask4 = mock(CodingBatTask.class);
        CodingBatTask mockTask5 = mock(CodingBatTask.class);

        taskContainer.addTask(mockTask1);
        taskContainer.addTask(mockTask2);
        taskContainer.addTask(mockTask3);
        taskContainer.addTask(mockTask4);
        taskContainer.addTask(mockTask5);

        assertEquals(5, simpleTaskService.getAll().size());
    }


    @Test
    public void getTask() throws NoSuchTaskException {
        CodingBatTaskContainer taskContainer = new CodingBatTaskContainer();
        SimpleTaskDaoImpl dao = new SimpleTaskDaoImpl(taskContainer);
        SimpleTaskServiceImpl simpleTaskService = new SimpleTaskServiceImpl(dao);
        CodingBatTask mockTask1 = mock(CodingBatTask.class);
        CodingBatTask mockTask2 = mock(CodingBatTask.class);
        CodingBatTask mockTask3 = mock(CodingBatTask.class);
        CodingBatTask mockTask4 = mock(CodingBatTask.class);
        CodingBatTask mockTask5 = mock(CodingBatTask.class);

        when(mockTask1.getId()).thenReturn("1875");
        when(mockTask2.getId()).thenReturn("1876");
        when(mockTask3.getId()).thenReturn("1877");
        when(mockTask4.getId()).thenReturn("1878");
        when(mockTask5.getId()).thenReturn("1879");

        taskContainer.addTask(mockTask1);
        taskContainer.addTask(mockTask2);
        taskContainer.addTask(mockTask3);
        taskContainer.addTask(mockTask4);
        taskContainer.addTask(mockTask5);
//        System.out.println(simpleTaskService.getTask("2"));

        assertEquals(simpleTaskService.getTask("7").getId(), "1877");
    }

}
