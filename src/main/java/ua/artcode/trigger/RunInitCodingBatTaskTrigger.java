package ua.artcode.trigger;

import ua.artcode.db.DataBaseManager;

public class RunInitCodingBatTaskTrigger {

    public static void main(String[] args) {
        //InitCodingBatTaskTrigger.loadTasksIfNeeded();
        //InitCodingBatTaskTrigger.loadTasksToDataBase();
        DataBaseManager.getInstance().createDumpOfDataBase();
    }
}
