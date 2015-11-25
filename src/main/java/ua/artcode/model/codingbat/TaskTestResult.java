package ua.artcode.model.codingbat;

import ua.artcode.model.common.UserAccount;

import java.util.List;

// todo change global structure,
public class TaskTestResult {

    private UserAccount userAccount;


    private CodingBatTask codingBatTask;

    // in data and expected values
    private TaskTestDataContainer taskTestDataContainer;
    // practical result
    private List<String> actualValues;

    private boolean passed;


    public TaskTestResult() {
    }

    public TaskTestResult(List<String> actualValues, CodingBatTask codingBatTask, boolean passed,
                          TaskTestDataContainer taskTestDataContainer, UserAccount userAccount) {
        this.actualValues = actualValues;
        this.codingBatTask = codingBatTask;
        this.passed = passed;
        this.taskTestDataContainer = taskTestDataContainer;
        this.userAccount = userAccount;
    }
}
