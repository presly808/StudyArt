package ua.artcode.dao;

import org.apache.log4j.Logger;
import org.mongodb.morphia.Datastore;
import ua.artcode.exception.AppException;
import ua.artcode.exception.AppValidationException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.validation.CodingBatTaskValidator;

import java.util.List;


/**
 * Created by Razer on 09.11.15.
 */


public class CodingBatTaskDaoMongoImpl implements CodingBatTaskDao {

    //TODO is need Logger here?
    private static final Logger LOG = Logger.getLogger(CodingBatTaskDaoMongoImpl.class);

    //@Autowired
    private Datastore datastore;

    public CodingBatTaskDaoMongoImpl() {
    }

    public CodingBatTaskDaoMongoImpl(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public CodingBatTask findById(String id) throws NoSuchTaskException {
        CodingBatTask codingBatTask = datastore.find(CodingBatTask.class, "codingBatId", id).get();
        if (codingBatTask == null) {
            throw new NoSuchTaskException("No task with id: " + id);
        }
        return codingBatTask;
    }

    @Override
    public boolean delete(String id) {
        CodingBatTask codingBatTask = datastore.find(CodingBatTask.class).field("codingBatId").equal(id).get();
        if (codingBatTask != null) {
            datastore.delete(CodingBatTask.class, codingBatTask.getId());
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return (int) datastore.getDB().getCollection("CodingBatTask").count();
    }

    @Override
    public CodingBatTask update(String id, CodingBatTask taskToAdd) throws AppException {
        CodingBatTask codingBatTask = findById(id);
        taskToAdd.setCodingBatId(codingBatTask.getCodingBatId());
        delete(id);
        addTask(taskToAdd);
        return taskToAdd;
    }

    @Override
    public List<CodingBatTask> getAll() throws AppException {
        List<CodingBatTask> tasks = datastore.find(CodingBatTask.class).asList();
        return tasks;
    }

    @Override
    public boolean isExist(String id) {
        CodingBatTask existTask = datastore.find(CodingBatTask.class).field("codingBatId").equal(id).get();
        if (existTask == null) {
            return false;
        }
        return true;
    }

    @Override
    public CodingBatTask addTask(CodingBatTask codingBatTask) throws AppValidationException {
        CodingBatTaskValidator validator = new CodingBatTaskValidator();
        validator.validate(codingBatTask);
        datastore.save(codingBatTask);
        return codingBatTask;
    }

}
