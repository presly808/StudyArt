package ua.artcode.script;

import com.mongodb.DuplicateKeyException;
import org.apache.log4j.Logger;
import org.mongodb.morphia.Datastore;
import org.springframework.context.ApplicationContext;
import ua.artcode.dao.TaskDao;
import ua.artcode.dao.TaskDaoMongoImpl;
import ua.artcode.exception.AppValidationException;
import ua.artcode.model.taskComponent.Task;
import ua.artcode.utils.SpringContext;
import ua.artcode.utils.codingbat.CodingBatTaskGrabber;
import ua.artcode.utils.io.AppPropertiesHolder;
import ua.artcode.utils.io.FileUtils;
import ua.artcode.utils.serialization.AppDataJsonSerializer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Scanner;


public class InitCodingBatTaskTrigger {


    private static final Logger LOG = Logger.getLogger(InitCodingBatTaskTrigger.class);

    /**
     * @return if task have been already loaded return false, otherwise return true
     */
    public static synchronized boolean loadTasksIfNeeded() {

        String dbJsonPath =AppPropertiesHolder.getProperty("db.json.task.path");

        if (FileUtils.exists(dbJsonPath)) {
            AppDataJsonSerializer appDataJsonSerializer = new AppDataJsonSerializer();
            appDataJsonSerializer.load(dbJsonPath);
            return false;
        } else {
            CodingBatTaskGrabber codingBatTaskGrabber = new CodingBatTaskGrabber();

            Collection<Task> collections = codingBatTaskGrabber.getTasksFromCodingBat();

            AppDataJsonSerializer appDataJsonSerializer = new AppDataJsonSerializer(Task.class);

            appDataJsonSerializer.save(collections, dbJsonPath);
            LOG.info("Tasks collection save in json");

            return true;
        }

    }

    /**
     * @download tasks to database if it need
     */
    public static void loadTasksToDataBase() throws DuplicateKeyException, AppValidationException {

        ApplicationContext context = SpringContext.getContext();

        Datastore datastore = (Datastore) context.getBean("datastore");

        String dbJsonPath = AppPropertiesHolder.getProperty("db.json.task.path");

        TaskDao taskDao = new TaskDaoMongoImpl(datastore);

        AppDataJsonSerializer appDataJsonSerializer = new AppDataJsonSerializer();

        Collection<Task> collection = appDataJsonSerializer.load(dbJsonPath);
        for (Task task : collection) {
            try {
                taskDao.add(task);
            } catch (DuplicateKeyException e) {
                LOG.warn(e.getMessage());
            }
        }
        LOG.info("Tasks collection from json put in DB.");
    }

    /**
     * @create dump of database if it need
     */
    public static void createDumpOfDataBase() throws IOException, InterruptedException {
            String dbName = AppPropertiesHolder.getProperty("mongo.db");
            Process process = Runtime.getRuntime().exec("mongodump --db "  + dbName);
            LOG.info("Created dump from DB.");
            process.waitFor();
    }

    /**
     * @restore dump of database if it need
     */
    public static void restoreDataBaseFromDump() throws IOException, InterruptedException {
            Process process = Runtime.getRuntime().exec("mongorestore dump");
            LOG.info("Restore DB from dump.");
            process.waitFor();
            LOG.debug(getData(process.getErrorStream()));
    }

    public static String getData(InputStream is) {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(is);
        while (sc.hasNextLine()) {
            sb.append(sc.nextLine()).append("\n");
        }
        return sb.toString();
    }
}
