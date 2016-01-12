package ua.artcode.script;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.context.ApplicationContext;
import ua.artcode.dao.CodingBatTaskDao;
import ua.artcode.dao.CodingBatTaskDaoMongoImpl;
import ua.artcode.exception.AppValidationException;
import ua.artcode.exception.CompilationException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.exception.UserAccountExistException;
import ua.artcode.process.TaskRunFacade;
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.utils.SpringContext;

public class RunInitCodingBatTaskTrigger {

    public static void main(String[] args) throws UserAccountExistException, AppValidationException, NoSuchTaskException, CompilationException {
        //TaskRunFacade taskRunFacade = new TaskRunFacade();

        //taskRunFacade.runTask();
//        InitCodingBatTaskTrigger.loadTasksIfNeeded();
//        InitCodingBatTaskTrigger.loadTasksToDataBase();
//        ApplicationContext context = SpringContext.getContext();
//        Morphia morphia = context.getBean(Morphia.class);
//        morphia.map(CodingBatTask.class);
//        Datastore datastore = (Datastore) context.getBean("datastore");
//        CodingBatTaskDao codingBatTaskDao = new CodingBatTaskDaoMongoImpl(datastore);
//        CodingBatTask task = codingBatTaskDao.findById("p187868");
//        TaskRunFacade taskRunFacade = context.getBean(TaskRunFacade.class);
//        taskRunFacade.runTask(task, task.getTemplate().substring(0, task.getTemplate().length() - 1) + "return(!weekday||vacation);\n}");
//        InitCodingBatTaskTrigger.loadTasksIfNeeded();
        //InitCodingBatTaskTrigger.loadTasksToDataBase();
        // InitCodingBatTaskTrigger.createDumpOfDataBase();
        //InitCodingBatTaskTrigger.restoreDataBaseFromDump();
//        UserDao userDao = new UserDaoMongoImpl(datastore);
//        userDao.addUser(new User("Razer","111","chernyshov.dev@gmail.com", UserType.ADMIN));
//        userDao.addUser(new User("Maxim","222","obonemax@gmail.com", UserType.ADMIN));
//        InitCodingBatTaskTrigger.createDumpOfDataBase();
//        CodingBatTask codingBatTask = new CodingBatTask();
//        codingBatTask.setCodingBatId("00000");
//        codingBatTaskDao.addTask(codingBatTask);
//        CodingBatTask c2 = new CodingBatTask();
//        c2.setCodingBatId("5555");
//        System.out.println(codingBatTaskDao.isExist(codingBatTask));

    }
}
