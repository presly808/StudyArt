package ua.artcode.validation;

import ua.artcode.model.codingbat.TaskTestData;
import ua.artcode.model.codingbat.TaskTestDataContainer;

/**
 * Created by Maxim on 26.02.2016.
 */
public class TestDataValidator {

    public static boolean isValid(TaskTestDataContainer testData) {
        if (testData == null || testData.getTaskTestDataList().size() == 0) {
            return false;
        }
        for (TaskTestData dataPoint:testData.getTaskTestDataList()) {
            if (dataPoint.getExpectedValue() == null) {
                return false;
            } else if (dataPoint.getInData() == null || dataPoint.getInData().size() == 0) {
                return false;
            }
        }
        return true;
    }
}
