package ua.artcode.dao;

import org.junit.After;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Razer on 15.02.16.
 */
@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/app-context.xml")
public class LessonDaoMongoImplTest {

    @Autowired
    @Qualifier("testStore")
    private Datastore datastore;

    @Qualifier("lessonDaoImplTest")
    @Autowired
    private LessonDao lessonDao;

    @Value("${mongo.test.db}")
    private String nameOfTestDb;

    @After
    public void deleteDb() {
        datastore.getMongo().dropDatabase(nameOfTestDb);
    }
}
