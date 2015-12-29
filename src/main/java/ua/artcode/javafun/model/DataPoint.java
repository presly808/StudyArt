package ua.artcode.javafun.model;

import java.io.Serializable;
import java.util.List;

public class DataPoint implements Serializable {

    private List<TestArg> in;
    private TestArg expected;
    private Object real;
    private Boolean passed;

    public DataPoint() {
    }


    public DataPoint(List<TestArg> in, TestArg expected) {
        this.in = in;
        this.expected = expected;
    }

    public List<TestArg> getIn() {
        return in;
    }

    public void setIn(List<TestArg> in) {
        this.in = in;
    }

    public TestArg getExpected() {
        return expected;
    }

    public void setExpected(TestArg expected) {
        this.expected = expected;
    }

    public Object getReal() {
        return real;
    }

    public void setReal(Object real) {
        this.real = real;
    }

    public Boolean getPassed() {
        return passed;
    }

    public void setPassed(Boolean passed) {
        this.passed = passed;
    }

    public Object[] toArray(){
        Object[] mas = new Object[in.size()];
        for(int i = 0; i <  mas.length; i++){
            mas[i] = in.get(i).getValue();
        }
        return mas;
    }


    @Override
    public String toString() {
        return "TestStep{" +
                "in=" + in +
                ", expected=" + expected +
                ", real=" + real +
                '}';
    }
}
