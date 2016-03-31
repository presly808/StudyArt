package ua.artcode.process;


import ua.artcode.model.codingbat.TaskTestData;
import ua.artcode.model.codingbat.TaskTestDataContainer;
import ua.artcode.model.codingbat.TaskTestResult;
import ua.artcode.utils.codingbat.CodingBatTaskUtils;
import ua.artcode.utils.dynamic_compile.MethodInvoker;

import java.util.List;

public class TestRunner {

    public static TaskTestResult run(MethodInvoker method, TaskTestDataContainer taskTestData) {
        List<TaskTestData> steps = taskTestData.getTaskTestDataList();
        TaskTestResult taskTestResult = new TaskTestResult();
        for (TaskTestData testData : steps) {
            Object[] convertedArg = testData.getInData().toArray();
            Object actualValue = method.call(convertedArg);
            Object expectedValue = testData.getValue();
            taskTestResult.addActualValues(actualValue);
            taskTestResult.addExpectedValues(expectedValue);
            taskTestResult.addResult(CodingBatTaskUtils.checkResult(actualValue, expectedValue));
        }
        return taskTestResult;
    }

}
