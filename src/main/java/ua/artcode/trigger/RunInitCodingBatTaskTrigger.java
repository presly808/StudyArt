package ua.artcode.trigger;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.context.ApplicationContext;
import ua.artcode.dao.SimpleTaskDaoMongoImpl;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.utils.SpringContext;

public class RunInitCodingBatTaskTrigger {

    public static void main(String[] args) {
        //InitCodingBatTaskTrigger.loadTasksIfNeeded();
        //InitCodingBatTaskTrigger.loadTasksToDataBase();
//        ApplicationContext context = SpringContext.getContext();
//        Morphia morphia = context.getBean(Morphia.class);
//        morphia.map(CodingBatTask.class);
//        Datastore datastore = context.getBean(Datastore.class);
//        SimpleTaskDao simpleTaskDao = new SimpleTaskDaoMongoImpl(datastore);
        //InitCodingBatTaskTrigger.loadTasksIfNeeded();
        //InitCodingBatTaskTrigger.loadTasksToDataBase();
        //InitCodingBatTaskTrigger.createDumpOfDataBase();
        //InitCodingBatTaskTrigger.restoreDataBaseFromDump();


    }
}
