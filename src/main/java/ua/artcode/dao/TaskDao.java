package ua.artcode.dao;

import com.mongodb.DuplicateKeyException;
import org.bson.types.ObjectId;
import ua.artcode.exception.DuplicateDataException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.taskComponent.Task;

import java.util.List;

public interface TaskDao {

    Task find(String title) throws NoSuchTaskException;

    Task find(ObjectId id) throws NoSuchTaskException;

    boolean delete(ObjectId id) throws NoSuchTaskException;

    boolean delete(String title) throws NoSuchTaskException;

    Task update(ObjectId id, Task updateTask) throws NoSuchTaskException, DuplicateDataException;

    List<Task> getAll();

    void add(Task task) throws DuplicateKeyException;

    boolean isExist(String title);

    int size();

    /**
     * Return tasks which have common group
     */
    List<Task> getGroupTasks(String group);

    List<String> getGroups();

}
