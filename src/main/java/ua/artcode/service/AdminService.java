package ua.artcode.service;

import org.bson.types.ObjectId;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.Task;

import java.util.List;

/**
 * Created by Razer on 10.01.16.
 */
public interface AdminService {

    Task addTask(Task codingBatTask) throws AppException;

    Task findTaskByTitle(String title) throws NoSuchTaskException;

    Task findTaskById(ObjectId id) throws NoSuchTaskException;

    int size();

    List<Task> getAll() throws AppException;

    boolean delete(String title);

    Task update(String id, Task updateTask);

    List<Task> getGroupTasks(String group);

    List<String> getGroups();
}
