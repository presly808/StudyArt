package ua.artcode.script;

public class RunInitCodingBatTaskTrigger {

    public static void main(String[] args) {
        InitCodingBatTaskTrigger.loadTasksIfNeeded();
        //InitCodingBatTaskTrigger.loadTasksToDataBase();
       /* ApplicationContext context = SpringContext.getContext();
        DataBaseManager dataBaseManager = (DataBaseManager) context.getBean("dbManager");
        SimpleTaskDao simpleTaskDao = new SimpleTaskDaoMongoImpl(dataBaseManager);
        dataBaseManager.restoreDataBaseFromDump();*/
        //dataBaseManager.createDumpOfDataBase();
        //System.out.println(simpleTaskDao.size());
    }
}
