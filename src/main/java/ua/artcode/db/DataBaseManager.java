package ua.artcode.db;

import com.mongodb.MongoClient;
import org.apache.log4j.Logger;
import org.mongodb.morphia.Datastore;
import org.springframework.context.ApplicationContext;
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.utils.MongoDbConnectionHelper;

import java.io.IOException;

/**
 * Created by Razer on 09.11.15.
 */
public class DataBaseManager {
    private final Logger LOG = Logger.getLogger(MongoDbConnectionHelper.class);
    private MongoClient mongo;
    private Datastore datastore;
    private ApplicationContext context;

    private DataBaseManager() {
    }

    public void initDataStore() {
        MongoDbConnectionHelper mongoDbConnectionHelper = new MongoDbConnectionHelper();
        mongo = mongoDbConnectionHelper.initMongoClient();
        datastore = mongoDbConnectionHelper.createDatastore(mongo, CodingBatTask.class);
    }

    public void createDumpOfDataBase() {
        try {
            LOG.trace("create dump from db");
            Runtime.getRuntime().exec("mongodump --db CodingBat");
        } catch (IOException e) {
            LOG.error(e);
        }
    }

    public void restoreDataBaseFromDump() {
        try {
            LOG.trace("Restore db from dump");
            Runtime.getRuntime().exec("mongorestore dump");
        } catch (IOException e) {
            LOG.error(e);
        }
    }

    public void findById(String id) {
        datastore.find(CodingBatTask.class, "CodingBatId", id);
    }

    public void addTask(CodingBatTask codingBatTask) {
        datastore.save(codingBatTask);
    }

    public boolean deleteTaskById(String id) {
        if (true) {
            datastore.delete(null);
            return true;
        }
        return false;
    }

    public int size() {
        //TODO create bean with name of collection
        return (int) datastore.getDB().getCollection("CodingBatTask").count();
    }

    public CodingBatTask getById() {
        return null;
    }
}

