package ua.artcode.utils;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.net.UnknownHostException;

/**
 * Created by Razer on 09.11.15.
 */
public class MongoDbConnectionHelper {
    private static MongoClient mongoClient;
    private static Datastore datastore;
    private static Morphia morphia;

    public static MongoClient getMongoClient() throws UnknownHostException {
        if (mongoClient == null) {
            String host = AppPropertiesHolder.getProperty("host");
            int port = Integer.parseInt(AppPropertiesHolder.getProperty("port"));
            mongoClient = new MongoClient(host, port);
        }
        return mongoClient;
    }

    public static Datastore getDatastore(MongoClient mongoClient) {
        String dataBaseName = AppPropertiesHolder.getProperty("databaseName");
        datastore = morphia.createDatastore(mongoClient, dataBaseName);
        return datastore;
    }
}
