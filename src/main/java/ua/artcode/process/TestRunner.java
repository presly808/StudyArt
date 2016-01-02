package ua.artcode.process;


import ua.artcode.utils.dynamic_compile.MethodInvoker;
import ua.artcode.model.codingbat.TaskTestData;
import ua.artcode.model.codingbat.TaskTestDataContainer;

import java.util.List;

/**
 * TODO create interface, SOLID code
 */
public class TestRunner {


    public static void run(MethodInvoker method, TaskTestDataContainer taskTestData) {
        List<TaskTestData> steps = taskTestData.getTaskTestDataList();

        //TODO refactor this approach generate test result, dont modify exists data

        for (TaskTestData testData : steps) {
            Object[] convertedArg = testData.getInData().toArray();
            Object real = method.call(convertedArg);
            real=real.toString();

            Object expectedValue=testData.getExpectedValue();
            System.out.println((real.equals(expectedValue))); //TODO check this place
        }
    }

}
