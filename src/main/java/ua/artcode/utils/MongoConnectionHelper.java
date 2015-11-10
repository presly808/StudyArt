package ua.artcode.utils;

import com.mongodb.MongoClient;

import java.net.UnknownHostException;

/**
 * Created by Razer on 09.11.15.
 */
public class MongoConnectionHelper {
    private static MongoClient mongoClient;

    public static MongoClient getMongoClient() throws UnknownHostException {
        if (mongoClient == null) {
            String name = AppPropertiesHolder.getProperty("dataBaseName");
            int port = Integer.parseInt(AppPropertiesHolder.getProperty("port"));
            mongoClient = new MongoClient(name, port);
        }
        return mongoClient;
    }
}
