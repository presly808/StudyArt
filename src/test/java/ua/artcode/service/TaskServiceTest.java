package ua.artcode.service;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import ua.artcode.dao.SimpleTaskDao;
import ua.artcode.dao.SimpleTaskDaoImpl;
import ua.artcode.db.CodingBatTaskContainer;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.CodingBatTask;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//TODO finish tests
@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    private static final Logger LOGGER = Logger.getLogger(TaskServiceTest.class);
    private static SimpleTaskDao mockTaskDao;

    private static CodingBatTaskContainer taskContainer;
    private static SimpleTaskDaoImpl simpleTaskDao;
    private static SimpleTaskServiceImpl simpleTaskService;

    @After
    public void cleanCounter() {
        CodingBatTaskContainer.setCount(0);
    }

    @Before
    public void initTasks() {
        taskContainer=new CodingBatTaskContainer();
        simpleTaskDao = new SimpleTaskDaoImpl(taskContainer);
        simpleTaskService = new SimpleTaskServiceImpl(simpleTaskDao);
        //create and add tasks to container
        for (int i = 0; i < 5; i++) {
            CodingBatTask mockTask = mock(CodingBatTask.class);
            when(mockTask.getId()).thenReturn(Integer.toString(i));
            taskContainer.addTask(mockTask);
        }
    }


    @Test(expected = NoSuchTaskException.class)
    public void findTaskByIdWithNoTask() throws NoSuchTaskException {
        simpleTaskService.getTask("noId");
    }

    @Test
    public void getAllTasks() throws AppException {
        assertEquals(5, simpleTaskService.getAll().size());
    }

    //TODO static?or non static?
    @Test
    public void getTask() throws NoSuchTaskException {
        assertEquals("2", simpleTaskService.getTask("2").getId());
    }

    @Test
    public void addTask() throws AppException {
        CodingBatTask task = new CodingBatTask("5", "TestTask", "description", "examples", "template");
        simpleTaskService.addTask(task);
        assertEquals(0, simpleTaskService.getTask("5").compareTo(task));
    }

}
