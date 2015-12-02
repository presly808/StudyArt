package ua.artcode.model.codingbat;

import java.util.ArrayList;
import java.util.List;

public class MethodSignature {

    private List<InArg> inArgList;
    private String returnType;

    public MethodSignature() {
        inArgList = new ArrayList<>();
    }

    public class InArg {

        private String type;
        private String name;

        public InArg(String type, String name) {
            this.type = type;
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public String getName() {
            return name;
        }
    }

    public void addInArg(String type, String name){
        inArgList.add(new InArg(type,name));
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public List<InArg> getInArgList() {
        return inArgList;
    }

    public void setInArgList(List<InArg> inArgList) {
        this.inArgList = inArgList;
    }

    public String getReturnType() {
        return returnType;
    }
}
