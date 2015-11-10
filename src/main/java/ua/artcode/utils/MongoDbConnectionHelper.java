package ua.artcode.utils;

import com.mongodb.MongoClient;

import java.net.UnknownHostException;

/**
 * Created by Razer on 09.11.15.
 */
public class MongoDbConnectionHelper {
    private static MongoClient mongoClient;

    public static MongoClient getMongoClient() throws UnknownHostException {
        if (mongoClient == null) {
            String host = AppPropertiesHolder.getProperty("host");
            int port = Integer.parseInt(AppPropertiesHolder.getProperty("port"));
            mongoClient = new MongoClient(host, port);
        }
        return mongoClient;
    }
}
