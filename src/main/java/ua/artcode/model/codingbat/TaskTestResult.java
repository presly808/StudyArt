package ua.artcode.model.codingbat;

import ua.artcode.model.common.User;

import java.util.List;

// todo change global structure(add userCode for task)
public class TaskTestResult {

    // will be ref in mongo db
    private User user;

    // will be ref in mongo db
    private CodingBatTask codingBatTask;

    // in data and expected values
    // practical result
    private List<String> actualValues;// change to map actualValues - passed

    private List<String> status;

    private String userCode;

    private boolean passedAll;


    public TaskTestResult() {
    }

    // TODO extract ot service layer
    public boolean checkPassed() {
        for (String variant : status) {
            if (!variant.equals("OK")) {
                return false;
            }
        }
        return true;
    }

    public CodingBatTask getCodingBatTask() {
        return codingBatTask;
    }

    public void setCodingBatTask(CodingBatTask codingBatTask) {
        this.codingBatTask = codingBatTask;
    }

    public List<String> getActualValues() {
        return actualValues;
    }

    public void setActualValues(List<String> actualValues) {
        this.actualValues = actualValues;
    }

    public List<String> getStatus() {
        return status;
    }

    public void setStatus(List<String> status) {
        this.status = status;
    }
}
