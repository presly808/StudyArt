package ua.artcode.dao;

import com.mongodb.DBCollection;
import com.mongodb.DuplicateKeyException;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import ua.artcode.exception.DuplicateDataException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.common.Task;

import java.util.List;

public class TaskDaoMongoImpl implements TaskDao {

    private Datastore datastore;
    private static final Logger LOG = Logger.getLogger(TaskDaoMongoImpl.class);

    public TaskDaoMongoImpl() {
        LOG.debug("TaskDaoMongoImpl instance has been created.");
    }

    public TaskDaoMongoImpl(Datastore datastore) {
        this.datastore = datastore;
        datastore.ensureIndexes(Task.class);
        LOG.debug("TaskDaoMongoImpl instance has been created.");
    }


    @Override
    public void add(Task task) throws DuplicateKeyException {
        datastore.save(task);
        LOG.info("The new task has been added to DB. Title:" + task.getTitle());
    }
    //TODO
    @Override
    public Task update(ObjectId id, Task task) throws NoSuchTaskException, DuplicateDataException {
        Task oldTask = find(id);
        try {
            delete(id);
            task.setId(id);
            add(task);
        } catch (DuplicateKeyException e) {
            add(oldTask);
            throw new DuplicateDataException("The task with title: "+task.getTitle()+" is already exist!");
        }
        LOG.info("The task has been updated.");
        return task;
    }

    @Override
    public Task find(ObjectId id) throws NoSuchTaskException {
        Task task = datastore.find(Task.class, "id", id).get();
        if (task == null) {
            LOG.warn("The task has been not founded. id: " + id);
            throw new NoSuchTaskException("The task has been not founded.");
        }
        return task;
    }

    @Override
    public Task find(String title) throws NoSuchTaskException {
        Task task = datastore.find(Task.class, "title", title).get();
        if (task == null) {
            LOG.debug("The task has been not founded. Title: " + title);
            throw new NoSuchTaskException("There is no task with title: " + title);
        }
        return task;
    }

    @Override
    public boolean delete(ObjectId id) throws NoSuchTaskException {
        Query<Task> query = datastore.createQuery(Task.class);
        query.field("id").equal(id);
        Task task = datastore.findAndDelete(query);
        if (task == null) {
            LOG.warn("The task has not been deleted. id: " + id);
            throw new NoSuchTaskException();
        }
        LOG.info("The task has been deleted.");
        return true;
    }

    @Override
    public boolean delete(String title) throws NoSuchTaskException {
        Query<Task> query = datastore.createQuery(Task.class);
        query.field("title").equal(title);
        Task task = datastore.findAndDelete(query);
        if (task == null) {
            LOG.debug("The task has not been deleted. Title: " + title);
            throw new NoSuchTaskException("There is no task with title: " + title);
        }
        LOG.info("The task has been deleted. Title: " + title);
        return true;
    }

    @Override
    public int size() {
        return (int) datastore.getDB().getCollection("Task").count();
    }

    @Override
    public List<Task> getAll() {
        return datastore.find(Task.class).asList();
    }

    @Override
    public boolean isExist(String title) {
        Task existTask = datastore.find(Task.class).field("title").equal(title).get();
        return existTask != null;
    }

    @Override
    public List<String> getGroups() {
        DBCollection dBCollection = datastore.getCollection(Task.class);
        List<String> groups = dBCollection.distinct("groupName");
        return groups;
    }

    /**
     * Return tasks which have common group
     */
    @Override
    public List<Task> getGroupTasks(String group) {
        return datastore.find(Task.class).field("groupName").equal(group).asList();
    }

}
