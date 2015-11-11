package ua.artcode.model.codingbat;

import java.util.List;

/**
 * Created by serhii on 09.11.15.
 */
public class MethodSignature {

    private List<InArg> inArgList;
    private String returnType;

    public MethodSignature() {
    }

    private class InArg {

        private String type;
        private String name;

    }


}
