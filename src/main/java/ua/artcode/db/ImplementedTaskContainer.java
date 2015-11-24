package ua.artcode.db;

import ua.artcode.model.codingbat.TaskTestResult;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Alex on 10.11.2015.
 */
public class ImplementedTaskContainer implements Serializable {


    private Map<String, TaskTestResult.ImplementedTask> implementedTaskMap;

    public ImplementedTaskContainer() {
        implementedTaskMap = new TreeMap<>();
    }

    public TaskTestResult.ImplementedTask addImplementedTask(TaskTestResult.ImplementedTask implementedTask){
        implementedTaskMap.put(implementedTask.getCodingBatTask().getId(),implementedTask);
        return implementedTask;
    }

    public Collection<TaskTestResult.ImplementedTask> getImplementedTasks(){
        return implementedTaskMap.values();
    }

    public TaskTestResult.ImplementedTask getById(String id) {
        return implementedTaskMap.get(id);
    }

}
