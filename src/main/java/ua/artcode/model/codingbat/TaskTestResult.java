package ua.artcode.model.codingbat;

import ua.artcode.model.common.UserAccount;

import java.util.ArrayList;
import java.util.List;

// todo change global structure,
public class TaskTestResult {

    private UserAccount userAccount;

    private CodingBatTask codingBatTask;

    // in data and expected values
    // practical result
    private List<String> actualValues;// change to map actualValues - passed

    private List<String> status;

    private boolean passedAll;


    public TaskTestResult() {
    }


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
