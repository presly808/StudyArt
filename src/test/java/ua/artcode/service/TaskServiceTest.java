package ua.artcode.service; /**
 * Created by Home on 02.11.2015.
 */
import org.junit.After;
import org.junit.Test;
import ua.artcode.dao.SimpleTaskDaoImpl;
import ua.artcode.db.CodingBatTaskContainer;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.CodingBatTask;
import ua.artcode.service.SimpleTaskServiceImpl;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;


public class TaskServiceTest {


    @Test(expected = NoSuchTaskException.class)
    public void testFindByIdException() throws NoSuchTaskException {
        CodingBatTaskContainer taskContainerMock = mock(CodingBatTaskContainer.class);
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

    @After
    public void after() {

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
