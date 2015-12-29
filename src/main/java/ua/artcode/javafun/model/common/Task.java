package ua.artcode.javafun.model.common;


import ua.artcode.javafun.model.TestCase;

import java.io.Serializable;

public class Task implements Serializable {

    private String accessKey;
    private String title;
    private String description;
    private String example;
    private TaskMethod taskMethod;
    private TestCase testCase;

    public Task() {
    }

    public Task(String title, String description, String example) {
        this.title = title;
        this.description = description;
        this.example = example;
    }

    public Task(String title, String description, String example, TaskMethod taskMethod, TestCase testCase) {
        this.title = title;
        this.description = description;
        this.example = example;
        this.taskMethod = taskMethod;
        this.testCase = testCase;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public TaskMethod getTaskMethod() {
        return taskMethod;
    }

    public void setTaskMethod(TaskMethod taskMethod) {
        this.taskMethod = taskMethod;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    @Override
    public String toString() {
        return  "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", example='" + example + '\'';
    }
}
