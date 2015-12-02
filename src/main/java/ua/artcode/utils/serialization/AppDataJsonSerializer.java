package ua.artcode.utils.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.utils.FileUtils;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Collection;


public class AppDataJsonSerializer {

    private static final Logger LOG = Logger.getLogger(AppDataJsonSerializer.class);

    private Gson gson;
    private FileWriter fileWriter;


    public AppDataJsonSerializer(Class... classes) {
        LOG.trace("init Gson");
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public <T> void save(Collection<T> collection, String path) {
        LOG.trace("save entities into file " + path);
        Type typeOfCollection = new TypeToken<Collection<CodingBatTask>>() {
        }.getType();
        //serialize collection to json
        String json = gson.toJson(collection, typeOfCollection);
        try {
            //create file
            fileWriter = new FileWriter(new File(path));
            //write data to file
            fileWriter.write(json);
            fileWriter.close();
        } catch (IOException e) {
            LOG.error(e);
        }
    }


    public <T> Collection<T> load(String path) {
        LOG.trace("load entities from file " + path);
        Collection<T> codingBatTasks = null;
        try {
            if (FileUtils.exists(path)) {
                //get type of collection
                Type collectionType = new TypeToken<Collection<CodingBatTask>>() {
                }.getType();
                //deserialize json from file to
                codingBatTasks = gson.fromJson(new FileReader(path), collectionType);
            }
        } catch (FileNotFoundException e) {
            LOG.error(e);
        }

        return codingBatTasks;
    }
}

