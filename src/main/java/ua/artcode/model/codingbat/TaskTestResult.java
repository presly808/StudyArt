package ua.artcode.model.codingbat;

import ua.artcode.model.common.User;

import java.util.ArrayList;
import java.util.List;

// todo change global structure(add userCode for task)
public class TaskTestResult {

    // will be ref in mongo db
    private User user;

    // will be ref in mongo db
    private String codingBatId;

    // in data and expected values
    // practical result
    private List<Object> expectedValues;
    private List<String> actualValues;// change to map actualValues - passed

    private List<String> results;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    private String userCode;

    private boolean passedAll;


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
