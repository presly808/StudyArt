package ua.artcode.dao;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.springframework.context.ApplicationContext;
import ua.artcode.exception.AppValidationException;
import ua.artcode.exception.NoSuchUserException;
import ua.artcode.exception.UserAccountExistException;
import ua.artcode.model.common.User;
import ua.artcode.utils.SpringContext;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static ua.artcode.script.InitCodingBatTaskTrigger.getData;

/**
 * Created by Maxim on 19.12.2015.
 */
public class UserMongoImplTest {
    private static final Logger LOG = Logger.getLogger(UserMongoImplTest.class);
    private static UserDao userDao;
    private static ApplicationContext context;
    private static Datastore datastore;
    private static final int AMOUNT_OF_USERS = 100;

    @BeforeClass
    public static void initializeDB() throws InterruptedException, AppValidationException {
        try {
            //TODO show commandline result of start server
            Process process = Runtime.getRuntime().exec("mongod --dbpath C:\\mongodb\\data\\");
            Process process1 = Runtime.getRuntime().exec("mongod --storageEngine=mmapv1");
            LOG.debug((getData(process.getErrorStream())));
            process.waitFor();
        } catch (IOException e) {
            LOG.error(e);
        }
        context = SpringContext.getContext();
        datastore = (Datastore) context.getBean("testStore");
        userDao = new UserDaoMongoImpl(datastore);

        for (int i = 0; i < AMOUNT_OF_USERS; i++) {
            try {
                userDao.addUser(new User("User_" + i, "password_" + i, "something_" + i + "@gmail.com"));
            } catch (UserAccountExistException e) {
                e.printStackTrace();
            }

        }
    }

    @Ignore
    @Test
    public void findByUseEmailTest() throws AppValidationException, UserAccountExistException {
        User user = null;
        User userToFind = new User("Login", "1223password", "test@gmail.com");

        userDao.addUser(userToFind);

        try {
            user = userDao.findByUserEmail("test@gmail.com");
            assertEquals(userToFind, user);
            userDao.delete("test@gmail.com");
        } catch (NoSuchUserException e) {
            LOG.warn("There is no user with email: " + user.getEmail());
        }


    }
}
