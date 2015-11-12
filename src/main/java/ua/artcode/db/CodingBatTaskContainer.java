package ua.artcode.db;

import ua.artcode.model.CodingBatTask;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;


public class CodingBatTaskContainer implements Serializable {

    private static int count = 0;// todo when load data counter did not change
    private Map<String, CodingBatTask> taskMap;

    public CodingBatTaskContainer() {
        taskMap = new TreeMap<>();
    }

    public static void setCount(int count) {
        CodingBatTaskContainer.count = count;
    }

    public CodingBatTask addTask(CodingBatTask codingBatTask) {
        String id = String.valueOf(count);

        codingBatTask.setId(id);
        taskMap.put(id, codingBatTask);

        count++;

        return codingBatTask;
    }

    public Collection<CodingBatTask> getTasks() {
        return taskMap.values();
    }

    public CodingBatTask getById(String id) {
        return taskMap.get(id);
    }

    public boolean deleteById(String id) {
        if (taskMap.containsKey(id)) {
            taskMap.remove(id);
            return true;
        } else {
            return false;
        }

    }

    public void cleanContainer(){
        taskMap.clear();
    }
}
