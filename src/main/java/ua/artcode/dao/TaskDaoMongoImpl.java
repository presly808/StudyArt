package ua.artcode.dao;

import com.mongodb.DBCollection;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.Task;

import java.util.List;


/**
 * Created by Razer on 09.11.15.
 */


public class TaskDaoMongoImpl implements TaskDao {

    private Datastore datastore;

    public TaskDaoMongoImpl() {
    }

    public TaskDaoMongoImpl(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public Task findByTitle(String title) throws NoSuchTaskException {
        Task task = datastore.find(Task.class,"title", title).get();
        if (task == null) {
            throw new NoSuchTaskException("No task with title: " + title);
        }
        return task;
    }

    @Override
    public Task findById(ObjectId id) throws NoSuchTaskException {
        Task task = datastore.find(Task.class, "id", id).get();
        if (task == null) {
            throw new NoSuchTaskException("No task with id: " + id);
        }
        return task;
    }

    @Override
    public boolean deleteByTitle(String title) throws NoSuchTaskException {
        Task task=findByTitle(title);
        if (task != null) {
            datastore.delete(Task.class, task.getId());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(ObjectId id) {
        Task task = datastore.find(Task.class).field("id").equal(id).get();
        if (task != null) {
            datastore.delete(Task.class, id);
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return (int) datastore.getDB().getCollection("Task").count();
    }

//    @Override
//    public Task update(String title, Task taskToAdd) throws AppException {
//        Task task = findByTitle(title);
//        //taskToAdd.setTitle(task.getTitle());
//        delete(title);
//        addTask(taskToAdd);
//        return taskToAdd;
//    }

    public Task update(ObjectId id, Task taskToAdd) throws AppException {
        //Task task = findById(id);
        //taskToAdd.setTitle(task.getTitle());
        deleteById(id);
        addTask(taskToAdd);
        return taskToAdd;
    }


    @Override
    public List<Task> getAll() throws AppException {
        return datastore.find(Task.class).asList();
    }

    @Override
    public boolean isExist(String title) {
        Task existTask = datastore.find(Task.class).field("title").equal(title).get();
        if (existTask == null) {
            return false;
        }
        return true;
    }

    @Override
    public List<String> getGroups() {
        DBCollection dBCollection = datastore.getCollection(Task.class);
        List<String> groups = dBCollection.distinct("groupName");
        return groups;
    }

    @Override
    public List<Task> getGroupTasks(String group) {
        List<Task> groupTasks = datastore.find(Task.class).field("groupName").equal(group).asList();
        return groupTasks;
    }

    @Override
    public Task addTask(Task task) throws AppException {
        if (!isExist(task.getTitle())) {
            datastore.save(task);
            return task;
        }
        throw new AppException("Task with title: " + task.getTitle() + " already exist");
    }

}
