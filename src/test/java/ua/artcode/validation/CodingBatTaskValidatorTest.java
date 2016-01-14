package ua.artcode.validation;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import ua.artcode.exception.AppValidationException;
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.model.codingbat.MethodSignature;
import ua.artcode.model.codingbat.TaskTestData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;


/**
 * Created by Maxim on 19.12.2015.
 */

public class CodingBatTaskValidatorTest {

    private static final Logger LOG = Logger.getLogger(CodingBatTaskValidator.class);
    private static CodingBatTaskValidator validator;
    private CodingBatTask task;


    @BeforeClass
    public static void initValidator() {
        validator = new CodingBatTaskValidator();
    }

    @Before
    public void initCorrectTask() {
        task = new CodingBatTask("p181767", "Title", "Simple description",
                "simple example() -> true",
                "public boolean $ome_Method(int arg1, String arg2, boolean arg3) {}", "Group-1");
        MethodSignature methodSignature = new MethodSignature();
        methodSignature.setReturnType("boolean");
        task.setMethodSignature(methodSignature);


        List<String> inData = new ArrayList<String>();
        inData.add("456");
        inData.add("some string");
        inData.add("false");

        String expectedValue = "true";

        TaskTestData taskTestData = new TaskTestData(expectedValue, inData);

        task.getTaskTestDataContainer().addTaskTestData(taskTestData);
        task.getTaskTestDataContainer().addTaskTestData(taskTestData);
        task.getTaskTestDataContainer().addTaskTestData(taskTestData);
    }

    @Test
    public void testCorrectTask() {
        boolean result = false;
        try {
            result = validator.validate(task);

        } catch (AppValidationException e) {
            LOG.warn(e.getExceptionMessageList());
        }
        assertTrue(result);
    }

    @Test(expected = AppValidationException.class)
    public void testInvalidCodingBatId_1() throws AppValidationException {
        task.setCodingBatId("879210");
        validator.validate(task);
    }

    @Test(expected = AppValidationException.class)
    public void testInvalidCodingBatId_2() throws AppValidationException {
        task.setCodingBatId("pp879210");
        validator.validate(task);
    }

    @Test
    public void testTemplateWithoutArgs() {
        task.setTemplate("public static void some_method232() {}");
        boolean result = false;
        try {
            result = validator.validate(task);

        } catch (AppValidationException e) {
            LOG.warn(e.getExceptionMessageList());
        }
        assertTrue(result);
    }

    @Test(expected = AppValidationException.class)
    public void testInvalidTemplate_1() throws AppValidationException {
        task.setTemplate("public static void some_method232 {}");
        validator.validate(task);
    }

    @Test(expected = AppValidationException.class)
    public void testInvalidTemplate_2() throws AppValidationException {
        task.setTemplate("public static void some_method232()");
        validator.validate(task);
    }

    @Test(expected = AppValidationException.class)
    public void testInvalidTemplate_3() throws AppValidationException {
        task.setTemplate("method() {}");
        validator.validate(task);
    }

    @Test
    public void testReturnTypeArray() {
        task.getMethodSignature().setReturnType("String[]");
        boolean result = false;
        try {
            result = validator.validate(task);

        } catch (AppValidationException e) {
            LOG.warn(e.getExceptionMessageList());
        }
        assertTrue(result);
    }

    @Test(expected = AppValidationException.class)
    public void testInvalidReturnType() throws AppValidationException {
        task.getMethodSignature().setReturnType("bolean");
        validator.validate(task);
    }
}
