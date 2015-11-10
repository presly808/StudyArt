package ua.artcode.db;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import ua.artcode.utils.MongoDbConnectionHelper;

import java.net.UnknownHostException;

/**
 * Created by Razer on 09.11.15.
 */
public class InitDataStore {
    private MongoClient mongo;
    private Datastore datastore;

    private void initDataStore() {
        try {
            mongo = MongoDbConnectionHelper.getMongoClient();
            datastore = MongoDbConnectionHelper.getDatastore(mongo);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}

