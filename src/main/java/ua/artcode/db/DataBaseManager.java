package ua.artcode.db;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import ua.artcode.model.CodingBatTask;
import ua.artcode.utils.MongoDbConnectionHelper;

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
        mongo = MongoDbConnectionHelper.initMongoClient();
        datastore = MongoDbConnectionHelper.createDatastore(mongo, CodingBatTask.class);
    }

    public static Datastore getDataStore() {
        return datastore;
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

