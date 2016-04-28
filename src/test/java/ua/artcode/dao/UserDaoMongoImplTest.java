package ua.artcode.dao;

import com.mongodb.DuplicateKeyException;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
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
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchUserException;
import ua.artcode.model.common.User;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;
import static ua.artcode.script.InitCodingBatTaskTrigger.getData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/app-context.xml")
public class UserDaoMongoImplTest {

    private static final Logger LOG = Logger.getLogger(UserDaoMongoImplTest.class);

    @Autowired
    @Qualifier("userDaoMongoTestImpl")
    private UserDao userDao;

    @Autowired
    @Qualifier("testStore")
    private Datastore datastore;

    @Value("${mongo.data.db.path}")
    private String mongoDataPath;

    @Value("${mongo.test.db}")
    private String nameOfTestDb;

    private final int AMOUNT_OF_USERS = 10;

    @Before
    public void initializeDB() throws InterruptedException,DuplicateKeyException {
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


    @Test
    public void sizeTest() {
        int dbSize = userDao.size();
        assertEquals(AMOUNT_OF_USERS, dbSize);
    }

    @Test
    public void updateTest() throws AppException {
        User user=userDao.find("User_1");
        user.setName("testName");
        User userToUpdate=userDao.find("User_2");
        userDao.update(userToUpdate.getEmail(),user);
        assertEquals(user.getName(), userDao.find("testName").getName());
    }

    @Test
    public void getAllUsersTest() {
        List<User> users = userDao.getAll();
        int sizeOfList = users.size();
        int sizeOfDb = userDao.size();
        assertEquals(sizeOfDb, sizeOfList);
    }

    @Test
    public void findByEmailTest() {
        User foundedUser = null;
        try {
            foundedUser = userDao.findByEmail("something_30@gmail.com");
        } catch (NoSuchUserException e) {
            LOG.warn("There are no user with email: something_33@gmail.com");

        }
        assertEquals("something_30@gmail.com", foundedUser.getEmail());
    }

    @Test(expected = NoSuchUserException.class)
    public void negativefindByEmailTest() throws NoSuchUserException {
        userDao.findByEmail("nonexistent_email@gmail.com");
    }

    @Test(expected = NoSuchUserException.class)
    public void removeUserTest() throws AppException {
        userDao.add(new User("User666", "password666", "test_1@gmail.com"));
        userDao.delete("test_1@gmail.com");
        userDao.find("test_1@gmail.com");
    }

    @Test(expected = NoSuchUserException.class)
    public void removeNonexistentUserTest() throws AppException {
        userDao.delete("test_1@gmail.com");
    }

    @Test
    public void addTest() throws DuplicateKeyException {
        User user = new User("User_2b", "password_2b", "test_2@gmail.com");
        userDao.add(user);
        assertEquals(AMOUNT_OF_USERS + 1, userDao.size());
    }

    @Ignore
    @Test(expected = DuplicateKeyException.class)
    public void addNegativeTest() throws DuplicateKeyException {
        User user=new User("User_0", "password_0", "something_0@gmail.com");
        userDao.add(user);
    }

    @Test
    public void updateUserTest() {
        User newUser;
        try {
            newUser = userDao.find("something_24@gmail.com");
            User userToUpdate = userDao.find("something_3@gmail.com");
            userDao.update("something_3@gmail.com", newUser);
            assertEquals(userToUpdate.getEmail(), userDao.find("something_3@gmail.com").getEmail());
            userToUpdate.setEmail("something_" + String.valueOf(AMOUNT_OF_USERS + 1) + "@gmail.com");
            userDao.add(userToUpdate);
        } catch (AppException e) {
            LOG.warn(e.getExceptionMessageList());
        }
    }

    @Test
    public void findByIdTest() throws AppException {
        User user= userDao.find("User_1");
        User user1=userDao.find(user.getId());
        assertEquals(user, user1);
    }

    @Test(expected =NoSuchUserException.class )
    public void negativeFindByIdTest() throws NoSuchUserException {
        userDao.find(new ObjectId());
    }

    @Test
    public void isExistTest() {
        assertTrue(userDao.isExist("something_12@gmail.com"));
    }

    @Test
    public void isExistNegativeTest() {
        assertFalse(userDao.isExist("nonexistent_email@gmail.com"));
    }

    @After
    public void deleteDb() {
        datastore.getMongo().dropDatabase(nameOfTestDb);
    }
}
