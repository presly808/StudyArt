package ua.artcode.model.codingbat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by serhii on 25.11.15.
 */
public class TaskTestDataContainer {


    private List<TaskTestData> taskTestDataList;


    public TaskTestDataContainer() {
        taskTestDataList = new ArrayList<>();
    }

    public TaskTestDataContainer(List<TaskTestData> taskTestDataList) {
        this.taskTestDataList = taskTestDataList;
    }

    public List<TaskTestData> getTaskTestDataList() {
        return taskTestDataList;
    }

    public void setTaskTestDataList(List<TaskTestData> taskTestDataList) {
        this.taskTestDataList = taskTestDataList;
    }

    public void addTaskTestData(TaskTestData taskTestData) {
        taskTestDataList.add(taskTestData);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TaskTestDataContainer{");
        sb.append("taskTestDataList=").append(taskTestDataList);
        sb.append('}');
        return sb.toString();
    }
}
