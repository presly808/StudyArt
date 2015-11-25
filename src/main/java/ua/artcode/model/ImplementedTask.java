package ua.artcode.model;

import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.model.codingbat.TaskTestResult;

/**
 * Created by Alex on 08.11.2015.
 */
public class ImplementedTask {

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
