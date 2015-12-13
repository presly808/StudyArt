package ua.artcode.db;

import ua.artcode.model.ImplementedTask;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

public class ImplementedTaskContainer implements Serializable {


    private Map<String, ImplementedTask> implementedTaskMap;

    public ImplementedTaskContainer() {
        implementedTaskMap = new TreeMap<>();
    }

    public ImplementedTask addImplementedTask(ImplementedTask implementedTask){
        implementedTaskMap.put(implementedTask.getCodingBatTask().getCodingBatId(),implementedTask);
        return implementedTask;
    }

    public Collection<ImplementedTask> getImplementedTasks(){
        return implementedTaskMap.values();
    }

    public ImplementedTask getById(String id) {
        return implementedTaskMap.get(id);
    }

}
