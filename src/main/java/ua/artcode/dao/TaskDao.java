package ua.artcode.dao;

import com.mongodb.DuplicateKeyException;
import org.bson.types.ObjectId;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.Task;

import java.util.List;

public interface TaskDao {

    Task find(String title) throws NoSuchTaskException;

    Task find(ObjectId id) throws NoSuchTaskException;

    boolean delete(ObjectId id) throws NoSuchTaskException;

    boolean delete(String title) throws NoSuchTaskException;

    Task update(ObjectId id, Task updateTask) throws AppException;

    List<Task> getAll();

    void add(Task task) throws DuplicateKeyException;

    boolean isExist(String title);

    int size();

    List<Task> getGroupTasks(String group);

    List<String> getGroups();

}
