package ua.artcode.dao;

import com.mongodb.DuplicateKeyException;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.artcode.exception.*;
import ua.artcode.model.common.User;
import ua.artcode.model.common.UserGroup;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Razer on 15.02.16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/app-context.xml")
public class UserGroupDaoMongoImplTest {

    private static final Logger LOG = Logger.getLogger(UserGroupDaoMongoImplTest.class);

    private final int AMOUNT_OF_ELEMENTS = 50;

    @Autowired
    @Qualifier("testStore")
    private Datastore datastore;


    @Qualifier("groupDaoImplTest")
    @Autowired
    private UserGroupDao groupDao;

    @Autowired
    @Qualifier("userDaoMongoTestImpl")
    private UserDao userDao;

    @Value("${mongo.test.db}")
    private String nameOfTestDb;

    @Value("${mongo.data.db.path}")
    private String mongoDataPath;

    @Before
    public void initializeDb() throws InterruptedException {
        try {
            Process process = Runtime.getRuntime().exec("mongod --dbpath " + mongoDataPath);
            //LOG.warn((getData(process.getInputStream())));
            process.waitFor();
        } catch (IOException e) {
            LOG.error(e);
        }
        String value;
        for (int i = 0; i < AMOUNT_OF_ELEMENTS; i++) {
            value = Integer.toString(i);
            UserGroup userGroup = new UserGroup("name-" + value, "description-" + value);
            groupDao.addGroup(userGroup);
        }
    }

    @Test
    public void sizeTest() {
        assertEquals(groupDao.size(), AMOUNT_OF_ELEMENTS);
    }

    @Test
    public void deleteByTitleTest() throws AppException {
        int sizeBeforeRemove = groupDao.size();
        groupDao.delete("name-5");
        int sizeAfterDel = groupDao.size();
        assertEquals(sizeBeforeRemove, sizeAfterDel + 1);
    }

    @Test
    public void deleteByIdTest() throws AppException {
        int sizeBeforeRemove = groupDao.size();
        UserGroup userGroup = groupDao.find("name-5");
        groupDao.delete(userGroup.getId());
        int sizeAfterDel = groupDao.size();
        assertEquals(sizeBeforeRemove, sizeAfterDel + 1);
    }

    @Test(expected = NoSuchGroupException.class)
    public void negativeDeleteByIdTest() throws AppException {
        groupDao.delete(new ObjectId());
    }

    @Test(expected = NoSuchGroupException.class)
    public void negativeDeleteByTitleTest() throws NoSuchGroupException {
        groupDao.delete("");
    }

    @Test
    public void isExistTest() throws AppValidationException {
        assertTrue(groupDao.isExist("name-0"));
    }

    @Test
    public void negativeIsExistTest() {
        assertFalse(groupDao.isExist("n0"));
    }

    @Test
    public void getAllTest() {
        List<UserGroup> groups = groupDao.getAll();
        assertEquals(groups.size(), groupDao.size());
    }

    @Test
    public void addTest() throws AppException {
        UserGroup userGroup = new UserGroup("name", "description");
        groupDao.addGroup(userGroup);
        assertEquals(groupDao.size(), AMOUNT_OF_ELEMENTS + 1);
    }

    @Test(expected = DuplicateKeyException.class)
    public void addNegativeTest() throws DuplicateKeyException {
        UserGroup userGroup = new UserGroup("name-0", "description-0");
        groupDao.addGroup(userGroup);
    }

    @Test
    public void findByTitleTest() throws AppException {
        UserGroup userGroup = groupDao.find("name-10");
        assertEquals(userGroup.getName(), "name-10");
    }

    @Test(expected = NoSuchGroupException.class)
    public void negativeFindByTitleTest() throws NoSuchGroupException {
        groupDao.find(new ObjectId());
    }

    @Test
    public void addUserToGroup() throws DuplicateDataException, NoSuchGroupException, NoSuchUserException {
        UserGroup userGroup = groupDao.find("name-1");
        int sizeBeforeAdd = userGroup.size();
        userDao.add(new User("test","000000","test@gmail.com"));
        groupDao.addUserToGroup("name-1",userDao.findByEmail("test@gmail.com"));
        UserGroup group=groupDao.find("name-1");
        assertEquals(sizeBeforeAdd + 1,group.size());
    }

    @After
    public void deleteDb() {
        datastore.getMongo().dropDatabase(nameOfTestDb);
    }
}

