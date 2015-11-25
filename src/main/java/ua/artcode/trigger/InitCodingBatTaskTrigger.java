package ua.artcode.trigger;

import ua.artcode.dao.SimpleTaskDao;
import ua.artcode.dao.SimpleTaskDaoMongoImpl;
import ua.artcode.db.DataBaseManager;
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.utils.AppDataJsonSerializer;
import ua.artcode.utils.AppPropertiesHolder;
import ua.artcode.utils.CodingBatTaskGrabber;
import ua.artcode.utils.FileUtils;

import java.util.Collection;

public class InitCodingBatTaskTrigger {


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
    public static void loadTasksToDataBase() {
        //if()
        SimpleTaskDao simpleTaskDao = new SimpleTaskDaoMongoImpl(new DataBaseManager());
        AppDataJsonSerializer appDataJsonSerializer = new AppDataJsonSerializer();
        String dbJsonPath = AppPropertiesHolder.getProperty("db.json.task.path");
        Collection<CodingBatTask> collection = appDataJsonSerializer.load(dbJsonPath);
        int i = 0;
        for (CodingBatTask task : collection) {
            simpleTaskDao.create(task);
            //System.out.println(i++);
        }
        // System.out.println(simpleTaskDao.size());
    }

}
