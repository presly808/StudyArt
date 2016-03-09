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

    List<Task> getAllTasks() throws AppException;

    boolean deleteByTitle(String title) throws NoSuchTaskException;

    boolean deleteById(ObjectId id);

    Task update(ObjectId id, Task updateTask) throws AppException;

    List<Task> getGroupTasks(String group);

    List<String> getGroups();
}
