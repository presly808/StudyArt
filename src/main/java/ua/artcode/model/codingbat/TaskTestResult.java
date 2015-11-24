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

    /**
     * Created by Alex on 08.11.2015.
     */
    public static class ImplementedTask {

        private CodingBatTask codingBatTask;

        private TaskTestResult taskTestResult;

        private String userInput;

        //getters
        public CodingBatTask getCodingBatTask() {
            return codingBatTask;
        }

        public TaskTestResult getTaskTestResult() {
            return taskTestResult;
        }

        public String getUserInput() {
            return userInput;
        }

        //setters


        public void setCodingBatTask(CodingBatTask codingBatTask) {
            this.codingBatTask = codingBatTask;
        }

        public void setTaskTestResult(TaskTestResult taskTestResult) {
            this.taskTestResult = taskTestResult;
        }

        public void setUserInput(String userInput) {
            this.userInput = userInput;
        }

        public ImplementedTask(CodingBatTask codingBatTask, TaskTestResult taskTestResult, String userInput) {
            this.codingBatTask = codingBatTask;
            this.taskTestResult = taskTestResult;
            this.userInput = userInput;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("ImplementedTask{");
            sb.append("codingBatTask=").append(codingBatTask);
            sb.append(", taskTestResult=").append(taskTestResult);
            sb.append(", userInput='").append(userInput).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
