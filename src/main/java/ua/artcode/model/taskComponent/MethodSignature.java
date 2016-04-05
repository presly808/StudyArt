package ua.artcode.model.taskComponent;

import org.mongodb.morphia.annotations.Embedded;

import java.util.ArrayList;
import java.util.List;

/**
 * public String name(int a,int b){}
 * String-returnType
 * int a,b-inArg
 */
@Embedded
public class MethodSignature {

    @Embedded
    private List<InArg> inArgList;
    private String returnType;

    public MethodSignature() {
        inArgList = new ArrayList<>();
    }

    public void addInArg(String type, String name) {
        inArgList.add(new InArg(type, name));
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public List<InArg> getInArgList() {
        return inArgList;
    }

    public void setInArgList(List inArgList) {
        this.inArgList = inArgList;
    }

    public String getReturnType() {
        return returnType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MethodSignature{");
        sb.append("inArgList=").append(inArgList);
        sb.append(", returnType='").append(returnType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
