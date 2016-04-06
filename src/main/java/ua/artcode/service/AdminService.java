package ua.artcode.service;

import com.mongodb.DuplicateKeyException;
import org.bson.types.ObjectId;
import ua.artcode.exception.AppException;
import ua.artcode.exception.DuplicateDataException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.common.Task;

import java.util.List;

/**
 * Created by Razer on 10.01.16.
 */
public interface AdminService {

    void addTask(Task task) throws DuplicateKeyException;

    Task findTaskByTitle(String title) throws NoSuchTaskException;

    Task findTaskById(ObjectId id) throws NoSuchTaskException;

    int size();

    List<Task> getAllTasks() throws AppException;

    boolean deleteByTitle(String title) throws NoSuchTaskException;

    boolean deleteById(ObjectId id) throws NoSuchTaskException;

    Task update(ObjectId id, Task task) throws NoSuchTaskException, DuplicateDataException;

    List<Task> getGroupTasks(String group);

    List<String> getGroups();
}
