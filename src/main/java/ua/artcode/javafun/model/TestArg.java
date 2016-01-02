package ua.artcode.javafun.model;

import java.io.Serializable;

public class TestArg implements Serializable {

    private Integer order;
    private String type;
    private Object value;

    public TestArg() {
    }

    //constructor for return type and value
    public TestArg(String type, Object value) {
        this.type = type;
        this.value = value;
    }

    public TestArg(int order, String type, Object value, String name) {
        this.order = order;
        this.type = type;
        this.value = value;
    }

    public TestArg(int order, String type, Object value) {
        this.order = order;
        this.type = type;
        this.value = value;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "TestArg{" +
                "order=" + order +
                ", type='" + type + '\'' +
                ", value=" + value +
                '}';
    }
}
