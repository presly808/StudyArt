package ua.artcode.model.codingbat;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;


public class TaskTestResult {

    public TaskTestResult() {
        actualValues = new ArrayList<>();
        expectedValues = new ArrayList<>();
        results = new ArrayList<>();
    }

    private boolean passedAll;

    private List<Object> expectedValues;

    private List<Object> actualValues;

    private List<String> results;

    private String status;

    private String userCode;



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public boolean getPassedAll() {
        return passedAll;
    }

    public void setPassedAll(boolean passedAll) {
        this.passedAll = passedAll;
    }

    public List<Object> getActualValues() {
        return actualValues;
    }

    public List<Object> getExpectedValues() {
        return expectedValues;
    }

    public void setActualValues(List<Object> actualValues) {
        this.actualValues = actualValues;
    }

    public List<String> getResults() {
        return results;
    }

    public void addResult(String result) {
        results.add(result);
    }

    public void addActualValues(Object actualValue) {
        actualValues.add(actualValue);
    }

    public void addExpectedValues(Object expectedValue) {
        expectedValues.add(expectedValue);
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("expectedValues", expectedValues)
                .append("actualValues", actualValues)
                .append("status", status)
                .append("results", results)
                .append("userCode", userCode)
                .append("passedAll", passedAll)
                .toString();
    }

}
