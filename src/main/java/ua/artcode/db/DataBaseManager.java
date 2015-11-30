package ua.artcode.db;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.utils.MongoDbConnectionHelper;

/**
 * Created by Razer on 09.11.15.
 */
public class DataBaseManager {
    public DataBaseManager() {
        initDataStore();
    }

    private  MongoClient mongo;
    private  Datastore datastore;

    public  void initDataStore() {
        mongo = MongoDbConnectionHelper.initMongoClient();
        datastore = MongoDbConnectionHelper.createDatastore(mongo, CodingBatTask.class);
    }

    public  Datastore getDataStore() {
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

