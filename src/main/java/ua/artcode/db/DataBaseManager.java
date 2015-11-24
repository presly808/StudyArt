package ua.artcode.db;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import ua.artcode.model.CodingBatTask;
import ua.artcode.utils.MongoDbConnectionHelper;

import java.net.UnknownHostException;

/**
 * Created by Razer on 09.11.15.
 */
public class DataBaseManager {
    public DataBaseManager() {
        initDataStore();
    }

    private static MongoClient mongo;
    private static Datastore datastore;

    public static void initDataStore() {
        try {
            mongo = MongoDbConnectionHelper.initMongoClient();
            datastore = MongoDbConnectionHelper.createDatastore(mongo, CodingBatTask.class);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static Datastore getDataStore() {
        return datastore;
    }

    public void addTask(CodingBatTask codingBatTask) {
        datastore.save(codingBatTask);
    }

    public void deleteTask(CodingBatTask codingBatTask) {
    }
}

