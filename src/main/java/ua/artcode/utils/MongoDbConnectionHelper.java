package ua.artcode.utils;

import com.mongodb.MongoClient;
import org.apache.log4j.Logger;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.context.ApplicationContext;

/**
 * Created by Razer on 09.11.15.
 */
public class MongoDbConnectionHelper {
    private final Logger LOG = Logger.getLogger(MongoDbConnectionHelper.class);
    private MongoClient mongoClient;
    private Datastore datastore;
    private Morphia morphia;

    public MongoClient initMongoClient() {
        ApplicationContext context = SpringContext.getContext();
        if (mongoClient == null) {
            //String host = AppPropertiesHolder.getProperty("MongoDBhost");
            //int port = Integer.parseInt(AppPropertiesHolder.getProperty("MongoDBport"));
            mongoClient = (MongoClient) context.getBean("mongoClient");
            //mongoClient = new MongoClient(host, port);
        }
        return mongoClient;
    }

    public <T> Datastore createDatastore(MongoClient mongoClient, Class<T> tClass) {
        if (datastore == null) {
            LOG.trace("Create data store");
            morphia = new Morphia();
            morphia.map(tClass);
            final String dataBaseName = AppPropertiesHolder.getProperty("mongo.db");
            datastore = morphia.createDatastore(mongoClient, dataBaseName);
        }
        return datastore;
    }
}
