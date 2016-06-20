package ua.artcode.core;

import org.junit.BeforeClass;
import org.junit.Ignore;
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

@ContextConfiguration("/test-context.xml")
public class TaskRunFacadeTest {

    @Autowired
    private TaskRunFacade taskRunFacade;

    private static Task byteTask;

    private static Task shortTask;

    private static Task intTask;

    private static Task arraySizeTask;

    private static Task arrayIntTask;

    private static Task longTask;

    private static Task charTask;

    private static Task stringTask;

    private static Task floatTask;

    private static Task doubleTask;


    @BeforeClass
    public static void createTask() throws AppValidationException {

        byteTask = new Task("sum", "description", "example", "public byte sum(byte a, byte b){}", "test", "public byte sum(byte a, byte b){ return a+b; }");
        String byteTestData = "2-1,1\r\n3-2,1\r\n4-2,2\r\n8-4,4\r\n10-5,5";
        byteTask.setMethodSignature(CodingBatTaskUtils.getMethodSignature(byteTask.getTemplate()));
        byteTask.setTaskTestDataContainer(CodingBatTaskUtils.getTestDataContainer(byteTestData));

        shortTask = new Task("sum", "description", "example", "public short sum(short a, short b){}", "test", "public short sum(short a, short b){ return a+b; }");
        String shortTestData = "2-1,1\r\n3-2,1\r\n4-2,2\r\n8-4,4\r\n10-5,5";
        shortTask.setMethodSignature(CodingBatTaskUtils.getMethodSignature(byteTask.getTemplate()));
        shortTask.setTaskTestDataContainer(CodingBatTaskUtils.getTestDataContainer(shortTestData));

        intTask = new Task("sum", "description", "example", "public int sum(int a, int b){}", "test", "public int sum(int a,int b){ return a+b; }");
        String testData = "2-1,1\r\n3-2,1\r\n4-2,2\r\n8-4,4\r\n10-5,5";
        intTask.setMethodSignature(CodingBatTaskUtils.getMethodSignature(intTask.getTemplate()));
        intTask.setTaskTestDataContainer(CodingBatTaskUtils.getTestDataContainer(testData));

        arrayIntTask = new Task("sum", "description", "example", "public int[] array(int a, int b){}", "test", "public int[] array(int a,int b){ return  int[] z={a,b}; }");
        String arrayIntTestData = "{1,1}-1,1\r\n{2,1}-2,1\r\n{2,2}-2,2\r\n{4,4}-4,4\r\n{5,5}-5,5";
        arrayIntTask.setMethodSignature(CodingBatTaskUtils.getMethodSignature(arrayIntTask.getTemplate()));
        arrayIntTask.setTaskTestDataContainer(CodingBatTaskUtils.getTestDataContainer(arrayIntTestData));

        arraySizeTask = new Task("sum", "description", "example", "public int sizeArray(int[] z){}", "test", "public int sizeArray(int[] z){ return  3;}");
        String arraySizeIntTestData = "3-{1,2,4}";//\r\n6-{2,1,4,3,2,3,}\r\n1-{1}\r\n3-{5,5,4}";
        arraySizeTask.setMethodSignature(CodingBatTaskUtils.getMethodSignature(arraySizeTask.getTemplate()));
        arraySizeTask.setTaskTestDataContainer(CodingBatTaskUtils.getTestDataContainer(arraySizeIntTestData));

        longTask = new Task("sum", "description", "example", "public long sum(long a,long b){}", "test", "public long sum(long a,long b){ return a+b; }");
        String longTestData = "2-1,1\r\n3-2,1\r\n4-2,2\r\n8-4,4\r\n10-5,5";
        longTask.setMethodSignature(CodingBatTaskUtils.getMethodSignature(longTask.getTemplate()));
        longTask.setTaskTestDataContainer(CodingBatTaskUtils.getTestDataContainer(longTestData));

        charTask = new Task("concat", "description", "example", "public String concat(char word,char b){}", "test", "public String sum(char word,char b){ return word+b; }");
        String charTestData = "ivan1-ivan,1\r\n" +
                "java8-java,8\r\n" +
                "spring4-spring,4\r\n" +
                "tomcat7-tomcat,7\r\n" +
                "world123-world,123\r\n";
        charTask.setMethodSignature(CodingBatTaskUtils.getMethodSignature(charTask.getTemplate()));
        charTask.setTaskTestDataContainer(CodingBatTaskUtils.getTestDataContainer(charTestData));

        stringTask = new Task("concat", "description", "example", "public String concat(String word,int b){}", "test", "public int sum(String word,int b){ return word+b; }");
        String stringTestData = "ivan1-ivan,1\r\n" +
                "java8-java,8\r\n" +
                "spring4-spring,4\r\n" +
                "tomcat7-tomcat,7\r\n" +
                "world123-world,123\r\n";
        stringTask.setMethodSignature(CodingBatTaskUtils.getMethodSignature(stringTask.getTemplate()));
        stringTask.setTaskTestDataContainer(CodingBatTaskUtils.getTestDataContainer(stringTestData));

        floatTask = new Task("sum", "description", "example", "public float sum(float a,float b){}", "test", "public float sum(float a,float b){ return a+b; }");
        String floatTestData = "2-1,1\r\n3-2,1\r\n4-2,2\r\n8-4,4\r\n10-5,5";
        floatTask.setMethodSignature(CodingBatTaskUtils.getMethodSignature(floatTask.getTemplate()));
        floatTask.setTaskTestDataContainer(CodingBatTaskUtils.getTestDataContainer(floatTestData));

        doubleTask = new Task("sum", "description", "example", "public double sum(double a,double b){}", "test", "public double sum(double a,double b){ return a+b; }");
        String doubleTestData = "2-1,1\r\n3-2,1\r\n4-2,2\r\n8-4,4\r\n10-5,5";
        doubleTask.setMethodSignature(CodingBatTaskUtils.getMethodSignature(doubleTask.getTemplate()));
        doubleTask.setTaskTestDataContainer(CodingBatTaskUtils.getTestDataContainer(doubleTestData));

    }
    @Ignore
    @Test
    public void runTaskBytePassedAllTest() {
        TaskTestResult taskTestResult = taskRunFacade.runTask(byteTask, "public byte sum(byte a,byte b){return a+b;}");
        assertTrue(taskTestResult.getPassedAll());
    }

    @Test
    public void runTaskBytePassedAllTestNegative() {
        TaskTestResult taskTestResult = taskRunFacade.runTask(byteTask, "public byte sum(byte a,byte b){return a-b;}");
        assertFalse(taskTestResult.getPassedAll());
    }

    @Test
    public void runTaskShortPassedAllTest() {
        TaskTestResult taskTestResult = taskRunFacade.runTask(shortTask, "public short sum(short a, short b){return a+b;}");
        assertTrue(taskTestResult.getPassedAll());
    }

    @Test
    public void runTaskShortPassedAllTestNegative() {
        TaskTestResult taskTestResult = taskRunFacade.runTask(shortTask, "public short sum(short a, short b){return a-b;}");
        assertFalse(taskTestResult.getPassedAll());
    }

    @Test
    public void runTaskIntPassedAllTest() {
        TaskTestResult taskTestResult = taskRunFacade.runTask(intTask, "public int sum(int a, int b){return a+b;}");
        assertTrue(taskTestResult.getPassedAll());
    }

    @Test
    public void runTaskIntPassedAllTestNegative() {
        TaskTestResult taskTestResult = taskRunFacade.runTask(intTask, "public int sum(int a, int b){return a-b;}");
        assertFalse(taskTestResult.getPassedAll());
    }

    @Test
    public void runTaskArrayIntPassedAllTest() {
        TaskTestResult taskTestResult = taskRunFacade.runTask(arrayIntTask, "public int[] array(int a, int b){return  new int[]{a,b};}");
        assertTrue(taskTestResult.getPassedAll());
    }

    @Test
    public void runTaskArrayIntPassedAllTestNegative() {
        TaskTestResult taskTestResult = taskRunFacade.runTask(arrayIntTask, "public int[] array(int a, int b){return  a;}");
        assertFalse(taskTestResult.getPassedAll());
    }

    @Test
    public void runTaskArraySizePassedAllTest() {
        TaskTestResult taskTestResult = taskRunFacade.runTask(arraySizeTask, "public int sizeArray(int[] z){ return  z.length;}");
        assertTrue(taskTestResult.getPassedAll());
    }

    @Test
    public void runTaskArraySizePassedAllTestNegative() {
        TaskTestResult taskTestResult = taskRunFacade.runTask(arraySizeTask, "public int sizeArray(int[] z){ return  5;}");
        assertFalse(taskTestResult.getPassedAll());
    }

    @Test
    public void runTaskLongPassedAllTest() {
        TaskTestResult taskTestResult = taskRunFacade.runTask(longTask, "public long sum(long a, long b){return a+b;}");
        assertTrue(taskTestResult.getPassedAll());
    }

    @Test
    public void runTaskLongPassedAllTestNegative() {
        TaskTestResult taskTestResult = taskRunFacade.runTask(longTask, "public long sum(long a, long b){return a-b;}");
        assertFalse(taskTestResult.getPassedAll());
    }

    @Test
    public void runTaskCharPassedAllTest() {
        TaskTestResult taskTestResult = taskRunFacade.runTask(charTask, "public String concat(char word, char b){return word+b;}");
        assertTrue(taskTestResult.getPassedAll());
    }

    @Test
    public void runTaskCharPassedAllTestNegative() {
        TaskTestResult taskTestResult = taskRunFacade.runTask(charTask, "public String concat(char word, char b){return word;}");
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

    @Test
    public void runTaskFloatPassedAllTest() {
        TaskTestResult taskTestResult = taskRunFacade.runTask(floatTask, "public float sum(float a, float b){return a+b;}");
        assertTrue(taskTestResult.getPassedAll());
    }

    @Test
    public void runTaskFloatPassedNegative() {
        TaskTestResult taskTestResult = taskRunFacade.runTask(floatTask, "public float sum(float a, float b){return b;}");
        assertFalse(taskTestResult.getPassedAll());
    }

    @Test
    public void runTaskDoublePassedAllTest() {
        TaskTestResult taskTestResult = taskRunFacade.runTask(doubleTask, "public double sum(double a, double b){return a+b;}");
        assertTrue(taskTestResult.getPassedAll());
    }

    @Test
    public void runTaskDoublePassedNegative() {
        TaskTestResult taskTestResult = taskRunFacade.runTask(doubleTask, "public double sum(double a, double b){return b;}");
        assertFalse(taskTestResult.getPassedAll());
    }

}
