package ua.artcode.model.taskComponent;

import java.util.ArrayList;
import java.util.List;



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
        for (int i=0;i<taskTestDataList.size();i++){
            builder.append(taskTestDataList.get(i));
            if (i!=taskTestDataList.size()-1){
                builder.append("\n");
            }
        }
        return builder.toString();
    }
}
