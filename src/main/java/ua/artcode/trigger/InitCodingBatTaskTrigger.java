package ua.artcode.trigger;

import ua.artcode.model.CodingBatTask;
import ua.artcode.utils.AppDataJsonSerializer;
import ua.artcode.utils.AppPropertiesHolder;
import ua.artcode.utils.CodingBatTaskGrabber;
import ua.artcode.utils.FileUtils;

import java.util.Collection;

public class InitCodingBatTaskTrigger {


    /**
     * @return if task have been already loaded return false, otherwise return true
     * */
    public static synchronized boolean loadTasksIfNeeded(){
        String dbJsonPath = AppPropertiesHolder.getProperty("db.json.task.path");

        if(FileUtils.exists(dbJsonPath)){
            //AppDataJsonSerializer appDataJsonSerializer=new AppDataJsonSerializer();
            //appDataJsonSerializer.load(CodingBatTask.class,dbJsonPath);
            return false;
        } else {
            CodingBatTaskGrabber codingBatTaskGrabber = new CodingBatTaskGrabber();

            Collection<CodingBatTask> collections = codingBatTaskGrabber.getTasksFromCodingBat();

            AppDataJsonSerializer appDataJsonSerializer = new AppDataJsonSerializer(CodingBatTask.class);

            appDataJsonSerializer.save(collections, dbJsonPath);

            return true;
        }

    }

}
