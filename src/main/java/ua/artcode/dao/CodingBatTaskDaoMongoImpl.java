package ua.artcode.dao;

import com.mongodb.DBCollection;
import org.mongodb.morphia.Datastore;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.validation.CodingBatTaskValidator;

import java.util.List;


/**
 * Created by Razer on 09.11.15.
 */


public class CodingBatTaskDaoMongoImpl implements CodingBatTaskDao {

    private Datastore datastore;

    public CodingBatTaskDaoMongoImpl() {
    }

    public CodingBatTaskDaoMongoImpl(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public CodingBatTask findByTitle(String title) throws NoSuchTaskException {
        CodingBatTask codingBatTask = datastore.find(CodingBatTask.class, "title", title).get();
        if (codingBatTask == null) {
            throw new NoSuchTaskException("No task with title: " + title);
        }
        return codingBatTask;
    }

    @Override
    public boolean delete(String title) {
        CodingBatTask codingBatTask = datastore.find(CodingBatTask.class).field("title").equal(title).get();
        if (codingBatTask != null) {
            datastore.delete(CodingBatTask.class, codingBatTask.getTitle());
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return (int) datastore.getDB().getCollection("CodingBatTask").count();
    }

    @Override
    public CodingBatTask update(String title, CodingBatTask taskToAdd) throws AppException {
        CodingBatTask codingBatTask = findByTitle(title);
        taskToAdd.setTitle(codingBatTask.getTitle());
        delete(title);
        addTask(taskToAdd);
        return taskToAdd;
    }

    @Override
    public List<CodingBatTask> getAll() throws AppException {
        return datastore.find(CodingBatTask.class).asList();
    }

    @Override
    public boolean isExist(String title) {
        CodingBatTask existTask = datastore.find(CodingBatTask.class).field("title").equal(title).get();
        if (existTask == null) {
            return false;
        }
        return true;
    }

    @Override
    public List<String> getGroups() {
        DBCollection dBCollection = datastore.getCollection(CodingBatTask.class);
        List<String> groups = dBCollection.distinct("groupName");
        return groups;
    }

    @Override
    public List<CodingBatTask> getGroupTasks(String group) {
        List<CodingBatTask> groupTasks = datastore.find(CodingBatTask.class).field("groupName").equal(group).asList();
        return groupTasks;
    }

    @Override
    public CodingBatTask addTask(CodingBatTask codingBatTask) throws AppException {
        if (!isExist(codingBatTask.getTitle())) {
            CodingBatTaskValidator validator = new CodingBatTaskValidator();
            validator.validate(codingBatTask);
            datastore.save(codingBatTask);
            return codingBatTask;
        }
        throw new AppException("Task with this title already exist");
    }

}
