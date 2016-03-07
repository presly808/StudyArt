package ua.artcode.model.codingbat;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

// todo change global structure(add userCode for task)
public class TaskTestResult {

    // will be ref in mongo db
    private String codingBatId;

    // in data and expected values
    // practical result
    private List<Object> expectedValues;
    private List<String> actualValues;
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

    private boolean passedAll;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("codingBatId", codingBatId)
                .append("expectedValues", expectedValues)
                .append("actualValues", actualValues)
                .append("status", status)
                .append("results", results)
                .append("userCode", userCode)
                .append("passedAll", passedAll)
                .toString();
    }

    public TaskTestResult() {
        actualValues = new ArrayList<>();
        expectedValues = new ArrayList<>();
        results = new ArrayList<>();
    }

    public String getCodingBatId() {
        return codingBatId;
    }

    public void setCodingBatId(String codingBatId) {
        this.codingBatId = codingBatId;
    }

    public List<String> getActualValues() {
        return actualValues;
    }

    public List<Object> getExpectedValues() {
        return expectedValues;
    }

    public void setActualValues(List<String> actualValues) {
        this.actualValues = actualValues;
    }

    public List<String> getResults() {
        return results;
    }

    public void addResult(String result) {
        results.add(result);
    }

    public void addActualValues(String actualValue) {
        actualValues.add(actualValue);
    }

    public void addExpectedValues(Object expectedValue) {
        expectedValues.add(expectedValue);
    }

}
