package ua.artcode.process;


import ua.artcode.model.codingbat.TaskTestResult;
import ua.artcode.utils.codingbat.CodingBatTaskUtils;
import ua.artcode.utils.dynamic_compile.MethodInvoker;
import ua.artcode.model.codingbat.TaskTestData;
import ua.artcode.model.codingbat.TaskTestDataContainer;

import java.util.List;

/**
 * TODO create interface, SOLID code
 */
public class TestRunner {


    public static TaskTestResult run(MethodInvoker method, TaskTestDataContainer taskTestData) {
        List<TaskTestData> steps = taskTestData.getTaskTestDataList();
        TaskTestResult taskTestResult = new TaskTestResult();
        //TODO refactor this approach generate test result, dont modify exists data
        for (TaskTestData testData : steps) {
            Object[] convertedArg = testData.getInData().toArray();
            Object actualValue = method.call(convertedArg);
            Object expectedValue = testData.getExpectedValue();
            taskTestResult.addActualValues(actualValue.toString());
            taskTestResult.addExpectedValues(expectedValue);
            taskTestResult.addResult(CodingBatTaskUtils.checkResult(actualValue.toString(), expectedValue));
        }
        return taskTestResult;
    }

}
