package ua.artcode.preprocess;

import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.model.codingbat.TaskTestData;
import ua.artcode.model.codingbat.TestArg;


public class DataUnmarshaller {

    public void convert(CodingBatTask task) {
        TestArg testArg;
        //ValueType valueType;
        //int k=0;
        for (TaskTestData data : task.getTaskTestDataContainer().getTaskTestDataList()) {
            for (int i = 0; i < data.getInData().size(); i++) {
                testArg = convertDispatcher(task.getMethodSignature().getInArgList().get(i).getType(), data.getInData().get(i));
                task.getMethodSignature().getInArgList().get(i).setType(testArg.getType());
                //TODO expected value
                data.getInData().remove(i);
                data.getInData().add(i, testArg.getValue());
            }
            //ValueType valueType1 = convertDispatcher(task.getMethodSignature().getReturnType(), task.getTaskTestDataContainer().getTaskTestDataList().get(k++).getExpectedValue());
            //data.setExpectedValue(task.getTaskTestDataContainer().getTaskTestDataList().get(0).getExpectedValue().toString());
        }
    }


    //TODO think about collection (List, Set, Map)
    private TestArg convertDispatcher(String type, Object val) {
        String value = val.toString();
        TestArg testArg = new TestArg();
        if ("byte".equals(type) || "java.lang.Byte".contains(type)) {
            testArg.setType("Byte");
            testArg.setValue(unmarshalByte(value));
        } else if ("short".equals(type) || "java.lang.Short".contains(type)) {
            testArg.setType("Short");
            testArg.setValue(unmarshalShort(value));
        } else if ("int".equals(type) || "java.lang.Integer".contains(type)) {
            testArg.setType("Integer");
            testArg.setValue(unmarshalInteger(value));
        } else if ("long".equals(type) || "java.lang.Long".contains(type)) {
            testArg.setType("Long");
            testArg.setValue(unmarshalLong(value));
        } else if ("float".equals(type) || "java.lang.Float".contains(type)) {
            testArg.setType("Float");
            testArg.setValue(unmarshalFloat(value));
        } else if ("double".equals(type) || "java.lang.Double".contains(type)) {
            testArg.setType("Double");
            testArg.setValue(unmarshalDouble(value));
        } else if ("boolean".equals(type) || "java.lang.Boolean".contains(type)) {
            testArg.setType("Boolean");
            testArg.setValue(unmarshalBoolean(value));
        } else if ("char".equals(type) || "java.lang.Character".contains(type)) {
            testArg.setType("Character");
            testArg.setValue(unmarshalCharacter(value));
        } else if ("String".equals(type) || "java.lang.String".contains(type)) {
            testArg.setType("String");
            testArg.setValue(value);
        } else {
            return null; // TODO refactor this place
        }
        return testArg;
    }

    private Byte unmarshalByte(String s) {
        return Byte.parseByte(s);
    }

    private Short unmarshalShort(String s) {
        return Short.parseShort(s);
    }

    private Integer unmarshalInteger(String s) {
        return Integer.parseInt(s);
    }

    private Long unmarshalLong(String s) {
        return Long.parseLong(s);
    }

    private Float unmarshalFloat(String s) {
        return Float.parseFloat(s);
    }

    private Double unmarshalDouble(String s) {
        return Double.parseDouble(s);
    }

    private Boolean unmarshalBoolean(String s) {
        return Boolean.parseBoolean(s);
    }

    private Character unmarshalCharacter(String s) {
        return s.charAt(0);
    }

}
