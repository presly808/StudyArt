package ua.artcode.to;

import java.util.List;

/**
 * Created by serhii on 22.05.16.
 */
public class TaskTestResults {

    private String taskName;
    private String status;
    private List<ResultTablePart> resultTablePartList;

    public TaskTestResults() {
    }

    public TaskTestResults(String taskName, String status, List<ResultTablePart> resultTablePartList) {
        this.taskName = taskName;
        this.status = status;
        this.resultTablePartList = resultTablePartList;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultTablePart> getResultTablePartList() {
        return resultTablePartList;
    }

    public void setResultTablePartList(List<ResultTablePart> resultTablePartList) {
        this.resultTablePartList = resultTablePartList;
    }
}
