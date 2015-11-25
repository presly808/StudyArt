package ua.artcode.model.codingbat;

import java.util.List;

public class TaskTestResult {

    // todo must be list, rename classes
    private TaskTestData taskTestData;
    private String actual;
    private boolean passed;

    public TaskTestResult() {
    }

    public TaskTestResult(TaskTestData taskTestData) {
        this.taskTestData = taskTestData;
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

    public TaskTestData getTaskTestData() {
        return taskTestData;
    }

    public void setTaskTestData(TaskTestData taskTestData) {
        this.taskTestData = taskTestData;
    }
}
