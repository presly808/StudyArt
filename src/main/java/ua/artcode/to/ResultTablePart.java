package ua.artcode.to;

/**
 * Created by Maxim on 11.02.2016.
 */
public class ResultTablePart {

    private String inArgsTemplate;
    private String actualValue;
    private String expectedValue;
    private String done;

    public ResultTablePart(String inArgs, String actualValue, String expectedValue, String result) {
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

    public String getActualValue() {
        return actualValue;
    }

    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }

    public String getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(String expectedValue) {
        this.expectedValue = expectedValue;
    }

    public String getResult() {
        return done;
    }

    public void setResult(String result) {
        this.done = result;
    }
}
