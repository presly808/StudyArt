package ua.artcode.dao;

import org.apache.log4j.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
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
    private static final Logger LOG = Logger.getLogger(CodingBatTaskDaoMongoImpl.class);
    private Datastore datastore;

    public CodingBatTaskDaoMongoImpl(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public CodingBatTask findById(String id) throws NoSuchTaskException {
        CodingBatTask codingBatTask =datastore.find(CodingBatTask.class, "codingBatId", id).get();
        if (codingBatTask == null) {
            throw new NoSuchTaskException("No task with id " + id);
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
    public CodingBatTask update(CodingBatTask task) {
        UpdateOperations<CodingBatTask> ops;
        Query<CodingBatTask> updateQuery = datastore.createQuery(CodingBatTask.class).field("id").equal(task.getId());
        ops = datastore.createUpdateOperations(CodingBatTask.class);
        //UpdateOperations<CodingBatTask> updateOper = datastore.createUpdateOperations(CodingBatTask.class);
        //datastore.findAndModify(updateQuery,ops);
        datastore.update(updateQuery, ops);
        return task;
    }

    @Override
    public List<CodingBatTask> getAll() throws AppException {
        List<CodingBatTask> tasks = datastore.find(CodingBatTask.class).asList();
        return tasks;
    }

    @Override
    public boolean isExist(String id) {
        //TODO read about exist
        CodingBatTask existTask = datastore.find(CodingBatTask.class).field("codingBatId").equal(id).get();
        if (existTask == null) {
            return false;
        }
        return true;
    }

    @Override
    public CodingBatTask addTask(CodingBatTask codingBatTask) {
        CodingBatTaskValidator validator = new CodingBatTaskValidator();
        try {
            validator.validate(codingBatTask);
        } catch (AppValidationException e) {
            LOG.warn(e.getExceptionMessageList());
        }
        datastore.save(codingBatTask);
        return codingBatTask;
    }

}
