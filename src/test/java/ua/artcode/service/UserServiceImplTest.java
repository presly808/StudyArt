package ua.artcode.service;


import com.mongodb.DuplicateKeyException;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import ua.artcode.dao.UserDao;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchUserException;
import ua.artcode.model.common.User;

import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static ua.artcode.script.InitCodingBatTaskTrigger.getData;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/app-context.xml")
public class UserServiceImplTest {

    private static final Logger LOG = Logger.getLogger(UserServiceImplTest.class);

    @Qualifier("userServiceImpl")
    @Autowired
    private  UserServiceImpl userService;

    @Autowired
    @Qualifier("testStore")
    private Datastore datastore;

    @Value("${mongo.data.db.path}")
    private String mongoDataPath;

    @Value("${mongo.test.db}")
    private String nameOfTestDb;

    @Autowired
    @Qualifier("userDaoMongoTestImpl")
    private  UserDao userDao;

    private final int AMOUNT_OF_USERS = 10;


    @Before
    public void initializeDB() throws InterruptedException, DuplicateKeyException {
        ReflectionTestUtils.setField(userService, "userDao", userDao);
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
        Assert.assertEquals(AMOUNT_OF_USERS, userService.size());
    }

    @Test
    public void getAllUsersTest() {
        List<User> users = userService.getAllUsers();
        int sizeOfList = users.size();
        int sizeOfDb = userDao.size();
        assertEquals(sizeOfDb, sizeOfList);
    }

    @Test
    public void updateTest() throws AppException {
        User user=userService.findUser("User_1");
        user.setName("testName");
        User userToUpdate=userDao.find("User_2");
        userService.update(userToUpdate.getEmail(),user);
        Assert.assertEquals(user.getName(), userDao.find("testName").getName());
    }

    @Test
    public void findByEmailTest() {
        User foundedUser = null;
        try {
            foundedUser = userService.findUserByEmail("something_1@gmail.com");
        } catch (NoSuchUserException e) {
            LOG.warn("There are no user with email: something_33@gmail.com");

        }
        Assert.assertEquals("something_1@gmail.com", foundedUser.getEmail());
    }

    @Test(expected = NoSuchUserException.class)
    public void negativefindByEmailTest() throws NoSuchUserException {
        userService.findUserByEmail("nonexistent_email@gmail.com");
    }

    @Test(expected = NoSuchUserException.class)
    public void removeUserTest() throws AppException {
        userService.register(new User("User666", "password666", "test_1@gmail.com"));
        userService.delete("test_1@gmail.com");
        userService.findUserByEmail("test_1@gmail.com");
    }

    @Test(expected = NoSuchUserException.class)
    public void removeNonexistentUserTest() throws AppException {
        userService.delete("test_1@gmail.com");
    }

    @Test
    public void registerTest() throws DuplicateKeyException {
        User user = new User("User_2b", "password_2b", "test_2@gmail.com");
        userService.register(user);
        Assert.assertEquals(AMOUNT_OF_USERS + 1, userService.size());
    }

    @Test
    public void updateUserTest() {
        User newUser;
        try {
            newUser = userService.findUserByEmail("something_24@gmail.com");
            User userToUpdate = userService.findUserByEmail("something_3@gmail.com");
            userService.update("something_3@gmail.com", newUser);
            Assert.assertEquals(userToUpdate.getEmail(), userService.findUserByEmail("something_3@gmail.com").getEmail());
            userToUpdate.setEmail("something_" + String.valueOf(AMOUNT_OF_USERS + 1) + "@gmail.com");
            userDao.add(userToUpdate);
        } catch (AppException e) {
            LOG.warn(e.getExceptionMessageList());
        }
    }

    @After
    public void deleteDb() {
        datastore.getMongo().dropDatabase(nameOfTestDb);
    }
}
