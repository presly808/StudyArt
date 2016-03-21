package ua.artcode.to;

/**
 * Created by Maxim on 11.02.2016.
 */
public class ResultTablePart {

    private String inArgsTemplate;
    private Object actualValue;
    private Object expectedValue;
    private String done;

    public ResultTablePart(String inArgs, Object actualValue, Object expectedValue, String result) {
        this.inArgsTemplate = inArgs;
        this.actualValue = actualValue;
        this.expectedValue = expectedValue;
        this.done = result;
    }

    public String getInArgsTemplate() {
        return inArgsTemplate;
    }

    public void setInArgsTemplate(String inArgsTemplate) {
        this.inArgsTemplate = inArgsTemplate;
    }

    public Object getActualValue() {
        return actualValue;
    }

    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }

    public Object getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(Object expectedValue) {
        this.expectedValue = expectedValue;
    }

    public String getResult() {
        return done;
    }

    public void setResult(String result) {
        this.done = result;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }
}
