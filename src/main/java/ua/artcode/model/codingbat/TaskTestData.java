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

    private List inData;

    private String expectedValue;

    public TaskTestData() {
    }

    public TaskTestData(String  expectedValue, List inData) {
        this.expectedValue = expectedValue;
        this.inData = inData;
    }

    public Object getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(String expectedValue) {
        this.expectedValue = expectedValue;
    }

    public List<Object> getInData() {
        return inData;
    }

    public void setInData(List inData) {
        this.inData = inData;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TaskTestData{");
        sb.append("inData=").append(inData);
        sb.append(", expectedValue=").append(expectedValue);
        sb.append('}');
        return sb.toString();
    }
}
