package ua.artcode.utils.codingbat;

import org.apache.commons.lang.StringUtils;
import ua.artcode.model.codingbat.Task;
import ua.artcode.model.codingbat.TaskTestData;
import ua.artcode.model.codingbat.TestArg;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


public class DataUnmarshaller {

    public void convert(Task task) {
        TestArg testArg;
        for (TaskTestData data : task.getTaskTestDataContainer().getTaskTestDataList()) {
            for (int i = 0; i < data.getInData().size(); i++) {
                testArg = convertDispatcher(task.getMethodSignature().getInArgList().get(i).getType(), data.getInData().get(i));
                task.getMethodSignature().getInArgList().get(i).setType(testArg.getType());
                data.getInData().remove(i);
                data.getInData().add(i, testArg.getValue());
            }
        }
    }

    public void convertExpectedValue(Task task) {
        TestArg testArg;
        List list;
        String returnType = task.getMethodSignature().getReturnType();
        for (TaskTestData data : task.getTaskTestDataContainer().getTaskTestDataList()) {
            testArg = convertDispatcher(returnType, data.getExpectedValue());
            list = new ArrayList<>();
            list.add(testArg.getValue());
            data.setExpectedValue(list);
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
        } else if ("int[]".equals(type) || "java.lang.Integer".contains(type)) {
            testArg.setType("int[]");
            testArg.setValue(unmarshalIntegerArr(value));
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
        } else if ("List".equals(type) || "java.util.List".contains(type)) {
            testArg.setType("java.util.List");
            testArg.setValue(Arrays.asList(value));
        } else {

        }
        return testArg;
    }

    private int[] unmarshalIntegerArr(String s) {
        String allArgs = StringUtils.substringBetween(s, "{", "}");
        String[] partsArgs = allArgs.split(",");
        int[] result = new int[partsArgs.length];
        for (int i = 0; i < partsArgs.length; i++) {
            result[i] = Integer.parseInt(partsArgs[i].trim());
        }
        return result;
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
