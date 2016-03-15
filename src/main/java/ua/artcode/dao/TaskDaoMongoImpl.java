package ua.artcode.dao;

import com.mongodb.DBCollection;
import com.mongodb.DuplicateKeyException;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
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
        datastore.ensureIndexes();
    }

    @Override
    public Task find(String title) throws NoSuchTaskException {
        Task task = datastore.find(Task.class, "title", title).get();
        if (task == null) {
            throw new NoSuchTaskException("No task with title: " + title);
        }
        return task;
    }

    @Override
    public Task find(ObjectId id) throws NoSuchTaskException {
        Task task = datastore.find(Task.class, "id", id).get();
        if (task == null) {
            throw new NoSuchTaskException("No task with id: " + id);
        }
        return task;
    }

    @Override
    public boolean delete(String title) throws NoSuchTaskException {
        Query<Task> query = datastore.createQuery(Task.class);
        query.field("title").equal(title);
        Task task = datastore.findAndDelete(query);
        if (task == null) {
            throw new NoSuchTaskException("There is no task with title: "+title);
        }
        return true;
    }

    @Override
    public boolean delete(ObjectId id) throws NoSuchTaskException {
        Query<Task> query = datastore.createQuery(Task.class);
        query.field("id").equal(id);
        Task task = datastore.findAndDelete(query);
        if (task == null) {
            throw new NoSuchTaskException();
        }
        return true;
    }

    @Override
    public int size() {
        return (int) datastore.getDB().getCollection("Task").count();
    }

    @Override
    public Task update(ObjectId id, Task task) throws AppException {
        delete(id);
        task.setId(id);
        add(task);
        return task;
    }

    @Override
    public List<Task> getAll() {
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
        return datastore.find(Task.class).field("groupName").equal(group).asList();
    }

    @Override
    public void add(Task task) throws DuplicateKeyException {
        datastore.save(task);
    }

}
