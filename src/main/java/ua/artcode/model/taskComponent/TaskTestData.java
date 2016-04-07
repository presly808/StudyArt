package ua.artcode.model.taskComponent;

import java.util.ArrayList;
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
    //TODO check list content(how to compare arrays?)
    private List expectedValue;

    public TaskTestData() {
    }

    public TaskTestData(List expectedValue, List inData) {
        this.expectedValue = expectedValue;
        this.inData = inData;
    }

    public Object getValue() {
        return expectedValue.get(0);
    }

    public List getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(List expectedValue) {
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
        List<String> convertedList = new ArrayList<>();
        for (Object o : inData) {
            convertedList.add(o.toString());
        }
        return expectedValue.get(0) + "-" + String.join(",", convertedList);
    }
}
