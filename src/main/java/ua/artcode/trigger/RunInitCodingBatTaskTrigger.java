package ua.artcode.trigger;

public class RunInitCodingBatTaskTrigger {

    public static void main(String[] args) {
        InitCodingBatTaskTrigger.loadTasksIfNeeded();
    }
}
