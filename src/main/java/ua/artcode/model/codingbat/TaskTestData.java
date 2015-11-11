package ua.artcode.model.codingbat;

import java.util.List;

/**
 * Created by serhii on 06.11.15.
 */
public class TaskTestData {

    private List<String> inData;
    private String expectedValue;

    public TaskTestData() {
    }

    public TaskTestData(String expectedValue, List<String> inData) {
        this.expectedValue = expectedValue;
        this.inData = inData;
    }


}
