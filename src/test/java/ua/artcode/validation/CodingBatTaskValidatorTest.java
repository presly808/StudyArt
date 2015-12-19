package ua.artcode.validation;

import org.junit.Before;
import org.junit.BeforeClass;
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.model.codingbat.MethodSignature;
import ua.artcode.model.codingbat.TaskTestData;


/**
 * Created by Maxim on 19.12.2015.
 */
public class CodingBatTaskValidatorTest {

    private static CodingBatTaskValidator validator;
    private static CodingBatTask task;


    @BeforeClass
    public static void initValidator() {
        validator = new CodingBatTaskValidator();
    }

    @Before
    public void initCorrectTask() {
        task = new CodingBatTask("p181767", "Title", "Simple description",
                "simple example() -> true",
                "public boolean someMethod(int arg1, String arg2) {}", "Group-1");
        MethodSignature methodSignature = new MethodSignature();
        methodSignature.setReturnType("boolean");
        task.setMethodSignature(methodSignature);


    }
}
