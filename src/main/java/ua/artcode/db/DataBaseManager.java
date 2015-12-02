package ua.artcode.db;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.utils.MongoDbConnectionHelper;

import java.io.IOException;

/**
 * Created by Razer on 09.11.15.
 */
public class DataBaseManager {
    private static DataBaseManager dataBaseManager = null;
    private MongoClient mongo;
    private Datastore datastore;

    private DataBaseManager() {
        initDataStore();
    }

    public static synchronized DataBaseManager getInstance() {
        if (dataBaseManager == null) {
            dataBaseManager = new DataBaseManager();
        }
        return dataBaseManager;
    }

    private void initDataStore() {
        MongoDbConnectionHelper mongoDbConnectionHelper = new MongoDbConnectionHelper();
        mongo = mongoDbConnectionHelper.initMongoClient();
        datastore = mongoDbConnectionHelper.createDatastore(mongo, CodingBatTask.class);
    }

    public void createDumpOfDataBase() {
        try {
            Runtime.getRuntime().exec("/Users/johnsmith/Mongodb/bin/mongodump", new String[]{"./mongodump"});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void restoreDataBaseFromDump() {
    }

    public void addTask(CodingBatTask codingBatTask) {
        datastore.save(codingBatTask);
    }

    public void deleteTask(CodingBatTask codingBatTask) {
    }

    public int size() {
        return (int) datastore.getDB().getCollection("CodingBatTask").getCount();
    }
}

