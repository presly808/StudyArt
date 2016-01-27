package ua.artcode.script;

import org.apache.log4j.Logger;
import org.mongodb.morphia.Datastore;
import org.springframework.context.ApplicationContext;

import ua.artcode.dao.CodingBatTaskDao;
import ua.artcode.dao.CodingBatTaskDaoMongoImpl;
import ua.artcode.exception.AppValidationException;
import ua.artcode.exception.UserAccountExistException;
import ua.artcode.model.codingbat.CodingBatTask;

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


        String dbJsonPath = AppPropertiesHolder.getProperty("db.json.task.path");

        if (FileUtils.exists(dbJsonPath)) {
            AppDataJsonSerializer appDataJsonSerializer = new AppDataJsonSerializer();
            appDataJsonSerializer.load(dbJsonPath);
            return false;
        } else {
            CodingBatTaskGrabber codingBatTaskGrabber = new CodingBatTaskGrabber();

            Collection<CodingBatTask> collections = codingBatTaskGrabber.getTasksFromCodingBat();

            AppDataJsonSerializer appDataJsonSerializer = new AppDataJsonSerializer(CodingBatTask.class);

            appDataJsonSerializer.save(collections, dbJsonPath);

            return true;
        }

    }

    /**
     * @download tasks to database if it need
     */
    public static void loadTasksToDataBase() throws UserAccountExistException, AppValidationException {
        ApplicationContext context = SpringContext.getContext();

        Datastore datastore = (Datastore) context.getBean("datastore");

        CodingBatTaskDao codingBatTaskDao = new CodingBatTaskDaoMongoImpl(datastore);
        AppDataJsonSerializer appDataJsonSerializer = new AppDataJsonSerializer();
        //context.GetRecourse(datastore)
        //TODO do with spring property
        //String dbJsonPath = context.getEnvironment().getProperty("db.json.task.path");
        String dbJsonPath = AppPropertiesHolder.getProperty("db.json.task.path");

        Collection<CodingBatTask> collection = appDataJsonSerializer.load(dbJsonPath);
        for (CodingBatTask task : collection) {
            try {
                codingBatTaskDao.addTask(task);
            } catch (AppValidationException e) {
                LOG.warn(e.getExceptionMessageList());
                throw e;
            }
        }
    }

    /**
     * @create dump of database if it need
     */
    public static void createDumpOfDataBase() {
        try {
            LOG.trace("addUser dump from db");
            Process process = Runtime.getRuntime().exec("mongodump --db CodingBat");
            process.waitFor();
        } catch (IOException e) {
            LOG.error(e);
        } catch (InterruptedException e) {
            LOG.error(e);
        }
    }

    /**
     * @restore dump of database if it need
     */
    //TODO where dump path?
    public static void restoreDataBaseFromDump() {
        try {
            LOG.trace("Restore db from dump");
            Process process = Runtime.getRuntime().exec("mongorestore dump");
            process.waitFor();
            LOG.debug(getData(process.getErrorStream()));
        } catch (IOException e) {
            LOG.error(e);
        } catch (InterruptedException e) {
            LOG.error(e);
        }
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
