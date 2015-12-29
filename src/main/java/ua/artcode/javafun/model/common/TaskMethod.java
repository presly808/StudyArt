package ua.artcode.javafun.model.common;

import java.io.Serializable;

/**
 * Created by admin on 29.11.2014.
 */
public class TaskMethod implements Serializable {

    private String methodName;
    private String methodBody;
    private String example;
    private String solution;

    public TaskMethod() {
    }

    public TaskMethod(String methodName, String methodBody, String example, String solution) {
        this.methodName = methodName;
        this.methodBody = methodBody;
        this.example = example;
        this.solution = solution;
    }

    public TaskMethod(String methodName, String methodBody) {
        this.methodName = methodName;
        this.methodBody = methodBody;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodBody() {
        return methodBody;
    }

    public void setMethodBody(String methodBody) {
        this.methodBody = methodBody;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    @Override
    public String toString() {
        return "TaskMethod{" +
                "methodName='" + methodName + '\'' +
                ", methodBody='" + methodBody + '\'' +
                '}';
    }
}
