package ua.artcode.dao;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.springframework.context.ApplicationContext;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchUserException;
import ua.artcode.exception.UserAccountExistException;
import ua.artcode.model.common.User;
import ua.artcode.utils.SpringContext;
import ua.artcode.utils.io.AppPropertiesHolder;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
    public static void initializeDB() throws InterruptedException, AppException {
        try {
            //TODO show commandline result of start server
            String mongoDataPath = AppPropertiesHolder.getProperty("mongo.data.db.path");
            Process process = Runtime.getRuntime().exec("mongod --dbpath " + mongoDataPath);
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

    @Test
    public void sizeTest() {
        int dbSize = userDao.size();
        assertEquals(AMOUNT_OF_USERS, dbSize);
    }

    @Test
    public void getAllUsersTest() {
        List<User> users = userDao.getAllUser();
        int sizeOfList = users.size();
        int sizeOfDb = userDao.size();
        assertEquals(sizeOfDb, sizeOfList);
    }

    @Test
    public void findUserByExistentEmailTest() {
        User foundedUser = null;
        try {
            foundedUser = userDao.findByUserEmail("something_33@gmail.com");
        } catch (NoSuchUserException e) {
            LOG.warn("There are no user with email: something_33@gmail.com");

        }
        assertEquals("something_33@gmail.com", foundedUser.getEmail());
    }

    @Test(expected = NoSuchUserException.class)
    public void findUserByNonexistentEmailTest() throws NoSuchUserException {
        userDao.findByUserEmail("nonexistent_email@gmail.com");
    }


    @Test(expected = NoSuchUserException.class)
    public void removeUserTest() throws AppException {
        User userForRemove = new User("User666", "password666", "test_1@gmail.com");
        userDao.addUser(userForRemove);
        userDao.delete("test_1@gmail.com");
        userDao.findByUserEmail("test_1@gmail.com");
    }

    @Test(expected = NoSuchUserException.class)
    public void removeNonexistentUserTest() throws AppException {
        userDao.delete("test_1@gmail.com");
    }

    @Test
    public void addUserTest()  {
        User user = new User("User_2b", "password_2b", "test_2@gmail.com");
        try {
            userDao.addUser(user);
            assertEquals(AMOUNT_OF_USERS + 1, userDao.size());
            userDao.delete("test_2@gmail.com");
        } catch (AppException e) {
            LOG.warn(e.getExceptionMessageList());
        }
    }

    @Test
    public void updateUserTest() {
        User newUser = null;
        try {
            newUser = userDao.findByUserEmail("something_24@gmail.com");
            User userToUpdate = userDao.findByUserEmail("something_3@gmail.com");
            userDao.update("something_3@gmail.com", newUser);
            assertEquals(userToUpdate.getEmail(), userDao.findByUserEmail("something_3@gmail.com").getEmail());
            userToUpdate.setEmail("something_" + String.valueOf(AMOUNT_OF_USERS + 1) + "@gmail.com");
            userDao.addUser(userToUpdate);
        } catch (AppException e) {
            LOG.warn(e.getExceptionMessageList());
        }
    }

    @Test
    public void isExistTest()  {
        assertTrue(userDao.isExist("something_12@gmail.com"));
    }

    @Test
    public void isExistNegativeTest() {
        assertFalse(userDao.isExist("nonexistent_email@gmail.com"));
    }

    @AfterClass
    public static void deleteDb() {
        String nameOfTestDb = AppPropertiesHolder.getProperty("mongo.test.db");
        datastore.getMongo().dropDatabase(nameOfTestDb);
    }
}
