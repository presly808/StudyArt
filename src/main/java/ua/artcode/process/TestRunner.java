package ua.artcode.process;


import org.apache.log4j.Logger;
import ua.artcode.model.taskComponent.TaskTestData;
import ua.artcode.model.taskComponent.TaskTestDataContainer;
import ua.artcode.model.taskComponent.TaskTestResult;
import ua.artcode.utils.codingbat.CodingBatTaskUtils;
import ua.artcode.utils.dynamic_compile.MethodInvoker;

import java.util.List;

public class TestRunner {

    private static final Logger LOG = Logger.getLogger(TestRunner.class);

    public static TaskTestResult run(MethodInvoker method, TaskTestDataContainer taskTestData) {
        LOG.debug("Run test for task.");
        List<TaskTestData> steps = taskTestData.getTaskTestDataList();
        TaskTestResult taskTestResult = new TaskTestResult();
        for (TaskTestData testData : steps) {

            //Object[] convertedArg = testData.getInData().toArray();
            Object actualValue = method.call(null);

            Object expectedValue = testData.getValue();
            taskTestResult.addActualValues(actualValue);
            taskTestResult.addExpectedValues(expectedValue);
            taskTestResult.addResult(CodingBatTaskUtils.checkResult(actualValue, expectedValue));
        }
        return taskTestResult;
    }

}
