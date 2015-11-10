package ua.artcode.db;

import ua.artcode.model.CodingBatTask;
import ua.artcode.model.ImplementedTask;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Alex on 10.11.2015.
 */
public class ImplementedTaskContainer implements Serializable {


    private Map<String, ImplementedTask> implementedTaskMap;

    public ImplementedTaskContainer() {
        implementedTaskMap = new TreeMap<>();
    }

    public ImplementedTask addImplementedTask(ImplementedTask implementedTask){
        implementedTaskMap.put(implementedTask.getCodingBatTask().getId(),implementedTask);
        return implementedTask;
    }

    public Collection<ImplementedTask> getTasks(){
        return implementedTaskMap.values();
    }

    public ImplementedTask getById(String id) {
        return implementedTaskMap.get(id);
    }

}
