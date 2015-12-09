package ua.artcode.trigger;

import org.springframework.context.ApplicationContext;
import ua.artcode.dao.SimpleTaskDao;
import ua.artcode.dao.SimpleTaskDaoMongoImpl;
import ua.artcode.db.DataBaseManager;
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.utils.AppPropertiesHolder;
import ua.artcode.utils.CodingBatTaskGrabber;
import ua.artcode.utils.FileUtils;
import ua.artcode.utils.SpringContext;
import ua.artcode.utils.serialization.AppDataJsonSerializer;

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
        ApplicationContext context= SpringContext.getContext();
        DataBaseManager dataBaseManager= (DataBaseManager) context.getBean("dbManager");
        SimpleTaskDao simpleTaskDao = new SimpleTaskDaoMongoImpl(dataBaseManager);
        AppDataJsonSerializer appDataJsonSerializer = new AppDataJsonSerializer();
        String dbJsonPath = (String) context.getBean("dbJsonTaskPath");
        Collection<CodingBatTask> collection = appDataJsonSerializer.load(dbJsonPath);
        for (CodingBatTask task : collection) {
            simpleTaskDao.create(task);
        }
    }

}
