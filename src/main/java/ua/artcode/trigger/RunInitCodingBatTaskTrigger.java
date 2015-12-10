package ua.artcode.trigger;

import org.springframework.context.ApplicationContext;
import ua.artcode.dao.SimpleTaskDao;
import ua.artcode.dao.SimpleTaskDaoMongoImpl;
import ua.artcode.db.DataBaseManager;
import ua.artcode.utils.SpringContext;

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
