package ua.artcode.dao;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.artcode.model.Course;
import ua.artcode.model.Lesson;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Razer on 02.02.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/app-context.xml")
public class CourseDaoImplTest {

    private static final Logger LOG = Logger.getLogger(CodingBatTaskMongoImplTest.class);

    private final int AMOUNT_OF_ELEMENTS = 100;

    @Autowired
    @Qualifier("courseDaoTestImpl")
    private CourseDao courseDao;

    @Before
    public void initializeDb() throws InterruptedException {
        try {
            Process process = Runtime.getRuntime().exec("mongod --dbpath /Users/johnsmith/Mongodb/data/db");
            //LOG.warn((getData(process.getInputStream())));
            process.waitFor();
        } catch (IOException e) {
            LOG.error(e);
        }
        String value;
        for (int i = 0; i < AMOUNT_OF_ELEMENTS; i++) {
            value = Integer.toString(i);
            Course course = new Course("name"+value,new ArrayList<Lesson>());
            courseDao.addCourse(course);
        }
    }

    @Test
    public void sizeTest() {
        int sizeOfdb = courseDao.size();
        assertEquals(sizeOfdb, AMOUNT_OF_ELEMENTS);
    }

    @After
    public void deleteDb(){

    }
}
