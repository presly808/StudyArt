package ua.artcode.core;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.artcode.exception.AppValidationException;
import ua.artcode.model.common.Task;
import ua.artcode.model.taskComponent.TaskTestResult;
import ua.artcode.process.TaskRunFacade;
import ua.artcode.utils.codingbat.CodingBatTaskUtils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
//Add test-context
@ContextConfiguration("/app-context.xml")
public class TaskRunFacadeTest {

    @Autowired
    private TaskRunFacade taskRunFacade;

    private static Task intTask;

    private static Task stringTask;

    @BeforeClass
    public static void createTask() throws AppValidationException {

        intTask= new Task("sum", "description", "example", "public int sum(int a,int b){}", "test", "public int sum(int a,int b){ return a+b; }");
        String testData = "2-1,1\r\n3-2,1\r\n4-2,2\r\n8-4,4\r\n10-5,5";
        intTask.setMethodSignature(CodingBatTaskUtils.getMethodSignature(intTask.getTemplate()));
        intTask.setTaskTestDataContainer(CodingBatTaskUtils.getTestDataContainer(testData));

        stringTask= new Task("concat", "description", "example", "public String concat(String word,int b){}", "test", "public int sum(String word,int b){ return word+b; }");
        String stringTestData = "ivan1-ivan,1\r\n"+
                "java8-java,8\r\n" +
                "spring4-spring,4\r\n" +
                "tomcat7-tomcat,7\r\n" +
                "world000-world,000";
        stringTask.setMethodSignature(CodingBatTaskUtils.getMethodSignature(stringTask.getTemplate()));
        stringTask.setTaskTestDataContainer(CodingBatTaskUtils.getTestDataContainer(stringTestData));

    }

    @Test
    public void runTaskIntPassedAllTest() {
        TaskTestResult taskTestResult = taskRunFacade.runTask(intTask, "public int sum(int a, int b){return a+b;}");
        assertTrue(taskTestResult.getPassedAll());
    }

    @Test
    public void runTaskIntPassedAllTestNegative() {
        TaskTestResult taskTestResult = taskRunFacade.runTask(stringTask, "public String sum(int a, int b){return a-b;}");
        assertFalse(taskTestResult.getPassedAll());
    }

    @Test
    public void runTaskStringPassedAllTest() {
        TaskTestResult taskTestResult = taskRunFacade.runTask(stringTask, "public String concat(String word, int b){return word+b;}");
        assertTrue(taskTestResult.getPassedAll());
    }

    @Test
    public void runTaskStringPassedAllTestNegative() {
        TaskTestResult taskTestResult = taskRunFacade.runTask(stringTask, "public String concat(String word, int b){return word;}");
        assertFalse(taskTestResult.getPassedAll());
    }

}
