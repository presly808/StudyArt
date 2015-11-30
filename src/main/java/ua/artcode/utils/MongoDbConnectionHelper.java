package ua.artcode.utils;

import com.mongodb.MongoClient;
import org.apache.log4j.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.net.UnknownHostException;

/**
 * Created by Razer on 09.11.15.
 */
public class MongoDbConnectionHelper {
    private static final Logger LOG = Logger.getLogger(MongoDbConnectionHelper.class);
    private static MongoClient mongoClient;
    private static Datastore datastore;
    private static Morphia morphia;

    public static MongoClient initMongoClient() {
        if (mongoClient == null) {
            String host = AppPropertiesHolder.getProperty("host");
            int port = Integer.parseInt(AppPropertiesHolder.getProperty("port"));
            try {
                mongoClient = new MongoClient(host, port);
            } catch (UnknownHostException e) {
                LOG.error(e);
            }
        }
        return mongoClient;
    }

    public static <T> Datastore createDatastore(MongoClient mongoClient, Class<T> tClass) {
        if (datastore == null) {
            LOG.trace("Create data store");
            morphia = new Morphia();
            morphia.map(tClass);
            final String dataBaseName = AppPropertiesHolder.getProperty("databaseName");
            datastore = morphia.createDatastore(mongoClient, dataBaseName);
        }
        return datastore;
    }
}
