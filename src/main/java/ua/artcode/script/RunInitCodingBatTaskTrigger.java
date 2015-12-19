package ua.artcode.script;

import org.mongodb.morphia.Datastore;
import org.springframework.context.ApplicationContext;
import ua.artcode.exception.UserAccountExistException;
import ua.artcode.utils.SpringContext;

public class RunInitCodingBatTaskTrigger {

    public static void main(String[] args) throws UserAccountExistException {
        //InitCodingBatTaskTrigger.loadTasksIfNeeded();
        //InitCodingBatTaskTrigger.loadTasksToDataBase();
        ApplicationContext context = SpringContext.getContext();
//        Morphia morphia = context.getBean(Morphia.class);
//        morphia.map(CodingBatTask.class);
        Datastore datastore = (Datastore) context.getBean("datastore");
//        SimpleTaskDao simpleTaskDao = new SimpleTaskDaoMongoImpl(datastore);
        //InitCodingBatTaskTrigger.loadTasksIfNeeded();
        InitCodingBatTaskTrigger.loadTasksToDataBase();
        //InitCodingBatTaskTrigger.createDumpOfDataBase();
        //InitCodingBatTaskTrigger.restoreDataBaseFromDump();
//        UserDao userDao = new UserDaoMongoImpl(datastore);
//        userDao.addUser(new User("Razer","111","chernyshov.dev@gmail.com", UserType.ADMIN));
//        userDao.addUser(new User("Maxim","222","obonemax@gmail.com", UserType.ADMIN));
        InitCodingBatTaskTrigger.createDumpOfDataBase();
    }
}
