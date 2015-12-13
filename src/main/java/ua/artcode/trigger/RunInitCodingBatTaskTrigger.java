package ua.artcode.trigger;

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
        InitCodingBatTaskTrigger.loadTasksToDataBase();
        //InitCodingBatTaskTrigger.createDumpOfDataBase();
        //InitCodingBatTaskTrigger.restoreDataBaseFromDump();
    }
}
