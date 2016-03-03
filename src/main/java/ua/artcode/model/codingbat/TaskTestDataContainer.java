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
        StringBuilder builder=new StringBuilder();
//        for (TaskTestData taskTestData : taskTestDataList) {
//            builder.append(taskTestData+"\n");
//        }
        for (int i=0;i<taskTestDataList.size();i++){
            builder.append(taskTestDataList.get(i));
            if (i!=taskTestDataList.size()-1){
                builder.append("\n");
            }
        }
        return builder.toString();
    }
}
