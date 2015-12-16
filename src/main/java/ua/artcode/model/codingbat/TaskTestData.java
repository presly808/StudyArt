package ua.artcode.model.codingbat;

import java.util.List;

/**
 * sleepIn(false, false) → true	true	OK
 * <p>
 * EXAMPLE of FILL IN Object
 * inData = {"false","false"}
 * expectedValue = "true"
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

    public String getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(String expectedValue) {
        this.expectedValue = expectedValue;
    }

    public List<String> getInData() {
        return inData;
    }

    public void setInData(List<String> inData) {
        this.inData = inData;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TaskTestData{");
        sb.append("inData=").append(inData);
        sb.append(", expectedValue='").append(expectedValue).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
