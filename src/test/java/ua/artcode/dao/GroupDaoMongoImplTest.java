package ua.artcode.dao;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

/**
 * Created by Razer on 15.02.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/app-context.xml")
public class GroupDaoMongoImplTest {

    private static final Logger LOG = Logger.getLogger(GroupDaoMongoImplTest.class);

    @Autowired
    @Qualifier("testStore")
    private Datastore datastore;


    @Qualifier("groupDaoImplTest")
    @Autowired
    private GroupDao groupDao;

    @Value("${mongo.test.db}")
    private String nameOfTestDb;

    @Before
    public void initializeDb() throws InterruptedException {
        try {
            Process process = Runtime.getRuntime().exec("mongod --dbpath /Users/johnsmith/Mongodb/data/db");
            //LOG.warn((getData(process.getInputStream())));
            process.waitFor();
        } catch (IOException e) {
            LOG.error(e);
        }
//        String value;
//        List<Lesson> lessons = createLessons();
//        for (int i = 0; i < AMOUNT_OF_ELEMENTS; i++) {
//            value = Integer.toString(i);
//            Course course = new Course("name" + value, lessons);
//            courseDao.addCourse(course);
        }

    @After
    public void deleteDb() {
        datastore.getMongo().dropDatabase(nameOfTestDb);
    }
}

