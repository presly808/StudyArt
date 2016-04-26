package ua.artcode.service;


import com.mongodb.DuplicateKeyException;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.artcode.dao.UserDao;
import ua.artcode.model.common.User;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static ua.artcode.script.InitCodingBatTaskTrigger.getData;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/app-context.xml")
public class UserServiceTest {

    private static final Logger LOG = Logger.getLogger(UserServiceTest.class);


    @Autowired
    @Qualifier("serviceImplTest")
    private UserServiceImpl userService;

    @Autowired
    @Qualifier("testStore")
    private Datastore datastore;

    @Value("${mongo.data.db.path}")
    private String mongoDataPath;

    @Value("${mongo.test.db}")
    private String nameOfTestDb;

    @Autowired
    @Qualifier("userDaoMongoTestImpl")
    private UserDao userDao;

    private final int AMOUNT_OF_USERS = 50;

    @Before
    public void initializeDB() throws InterruptedException, DuplicateKeyException {
        try {
            Process process = Runtime.getRuntime().exec("mongod --dbpath " + mongoDataPath);
            LOG.info(getData(process.getInputStream()));
            LOG.debug((getData(process.getErrorStream())));
            process.waitFor();
        } catch (IOException e) {
            LOG.error(e);
        }
        for (int i = 0; i < AMOUNT_OF_USERS; i++) {
            userDao.add(new User("User_" + i, "password_" + i, "something_" + i + "@gmail.com"));
        }
    }


    //
    @Test
    public void getAllUsersTest() {
        java.util.List<User> users = userService.getAllUsers();
        int sizeOfList = users.size();
        int sizeOfDb = userDao.size();
        assertEquals(sizeOfDb, sizeOfList);
    }
//
//    @Test
//    public void findUserByExistentEmailTest() {
//        User foundedUser = null;
//        try {
//            foundedUser = userDao.findByEmail("something_30@gmail.com");
//        } catch (NoSuchUserException e) {
//            LOG.warn("There are no user with email: something_33@gmail.com");
//
//        }
//        assertEquals("something_30@gmail.com", foundedUser.getEmail());
//    }
//
//    @Test(expected = NoSuchUserException.class)
//    public void findUserByNonExistentEmailTest() throws NoSuchUserException {
//        userDao.find("nonexistent_email@gmail.com");
//    }
//
//    @Test(expected = NoSuchUserException.class)
//    public void removeUserTest() throws AppException {
//        userDao.add(new User("User666", "password666", "test_1@gmail.com"));
//        userDao.delete("test_1@gmail.com");
//        userDao.find("test_1@gmail.com");
//    }
//
//    @Test(expected = NoSuchUserException.class)
//    public void removeNonexistentUserTest() throws AppException {
//        userDao.delete("test_1@gmail.com");
//    }
//
//    @Test
//    public void addTest() throws DuplicateKeyException {
//        User user = new User("User_2b", "password_2b", "test_2@gmail.com");
//        userDao.add(user);
//        assertEquals(AMOUNT_OF_USERS + 1, userDao.size());
//    }
//
//    @Test(expected = DuplicateKeyException.class)
//    public void addNegativeTest() throws DuplicateKeyException {
//        User user = new User("User_0", "password_0", "something_0@gmail.com");
//        userDao.add(user);
//    }
//
//    @Test
//    public void updateUserTest() {
//        User newUser;
//        try {
//            newUser = userDao.find("something_24@gmail.com");
//            User userToUpdate = userDao.find("something_3@gmail.com");
//            userDao.update("something_3@gmail.com", newUser);
//            assertEquals(userToUpdate.getEmail(), userDao.find("something_3@gmail.com").getEmail());
//            userToUpdate.setEmail("something_" + String.valueOf(AMOUNT_OF_USERS + 1) + "@gmail.com");
//            userDao.add(userToUpdate);
//        } catch (AppException e) {
//            LOG.warn(e.getExceptionMessageList());
//        }
//    }
//
//    @Test
//    public void isExistTest() {
//        assertTrue(userDao.isExist("something_12@gmail.com"));
//    }
//
//    @Test
//    public void isExistNegativeTest() {
//        assertFalse(userDao.isExist("nonexistent_email@gmail.com"));
//    }

    @After
    public void deleteDb() {
        datastore.getMongo().dropDatabase(nameOfTestDb);
    }
}
