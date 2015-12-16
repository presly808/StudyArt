package ua.artcode.script;

import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.utils.io.AppPropertiesHolder;
import ua.artcode.utils.codingbat.CodingBatTaskGrabber;
import ua.artcode.utils.io.FileUtils;
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
    /*public static void loadTasksToDataBase() {
        ApplicationContext context= SpringContext.getContext();

        DataBaseManager dataBaseManager=  context.getBean(DataBaseManager.class);

        CodingBatTaskDao codingBatTaskDao = new CodingBatTaskDaoMongoImpl(dataBaseManager);
        AppDataJsonSerializer appDataJsonSerializer = new AppDataJsonSerializer();

        String dbJsonPath = context.getEnvironment().getProperty("db.json.task.path");

        Collection<CodingBatTask> collection = appDataJsonSerializer.load(dbJsonPath);
        for (CodingBatTask task : collection) {
            codingBatTaskDao.create(task);
        }
    }*/

}
