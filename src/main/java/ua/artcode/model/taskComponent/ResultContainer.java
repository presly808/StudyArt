package ua.artcode.model.taskComponent;

import java.util.List;

public class ResultContainer {

    private List<TaskTestResult> results;

    public void addTestResult(TaskTestResult taskTestResult){
        results.add(taskTestResult);
    }

    public List<TaskTestResult> getResults() {
        return results;
    }

    public void setResults(List<TaskTestResult> results) {
        this.results = results;
    }
}
