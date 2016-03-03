package ua.artcode.dao;

import org.bson.types.ObjectId;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.Task;

import java.util.List;

public interface TaskDao {

    Task findByTitle(String title) throws NoSuchTaskException;

    Task findById(ObjectId id) throws NoSuchTaskException;

    boolean deleteById(ObjectId id);

    boolean deleteByTitle(String title);

    Task update(ObjectId id, Task updateTask) throws AppException;

    List<Task> getAll() throws AppException;

    Task addTask(Task task) throws AppException;

    boolean isExist(String title);

    int size();

    List<Task> getGroupTasks(String group);

    List<String> getGroups();

}
