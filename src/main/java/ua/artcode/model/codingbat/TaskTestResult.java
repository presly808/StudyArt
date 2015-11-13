package ua.artcode.model.codingbat;

import java.util.List;

public class TaskTestResult {

    private List<String> inData;
    private String expected;
    private String actual;
    private boolean passed;

    public TaskTestResult() {
    }

    public TaskTestResult(List<String> inData, String expected,
                          String actual, boolean passed) {
        this.inData = inData;
        this.expected = expected;
        this.actual = actual;
        this.passed = passed;
    }

    public List<String> getInData() {
        return inData;
    }

    public void setInData(List<String> inData) {
        this.inData = inData;
    }

    public String getExpected() {
        return expected;
    }

    public void setExpected(String expected) {
        this.expected = expected;
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
