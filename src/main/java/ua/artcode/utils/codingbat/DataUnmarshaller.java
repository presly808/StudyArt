package ua.artcode.utils.codingbat;

import org.apache.commons.lang.StringUtils;
import ua.artcode.model.common.Task;
import ua.artcode.model.taskComponent.TaskTestData;
import ua.artcode.model.taskComponent.TestArg;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


public class DataUnmarshaller {
    //TODO
    public void convertInData(Task task) {
        TestArg testArg;
        for (TaskTestData data : task.getTaskTestDataContainer().getTaskTestDataList()) {
            if (data.getInData() != null) {
                for (int i = 0; i < data.getInData().size(); i++) {
                    testArg = convertDispatcher(task.getMethodSignature().getInArgList().get(i).getType(), data.getInData().get(i));
                    task.getMethodSignature().getInArgList().get(i).setType(testArg.getType());
                    data.getInData().remove(i);
                    data.getInData().add(i, testArg.getValue());
                }
            }
        }
    }

    public void convertExpectedValue(Task task) {
        TestArg testArg;
        List list;
        String returnType = task.getMethodSignature().getReturnType();
        for (TaskTestData data : task.getTaskTestDataContainer().getTaskTestDataList()) {
            testArg = convertDispatcher(returnType, data.getValue());
            list = new ArrayList<>();
            list.add(testArg.getValue());
            data.setExpectedValue(list);
        }
    }

    private TestArg convertDispatcher(String type, Object val) {
        String value = val.toString();
        TestArg testArg = new TestArg();
        if ("byte".equals(type) || "java.lang.Byte".contains(type)) {
            testArg.setType("Byte");
            testArg.setValue(unmarshalByte(value));
        } else if ("short".equals(type) || "java.lang.Short".contains(type)) {
            testArg.setType("Short");
            testArg.setValue(unmarshalShort(value));
        } else if ("int[]".equals(type)|| "int []".equals(type)) {
            testArg.setType("int[]");
            testArg.setValue(unmarshalIntegerArr(value));
            //TODO 000>unmarshall>0(world000>world0)
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
            boolean result = unmarshalBoolean(value);
            testArg.setValue(result);
        } else if ("char".equals(type) || "java.lang.Character".contains(type)) {
            testArg.setType("Character");
            testArg.setValue(unmarshalCharacter(value));
        } else if ("String[]".equals(type)) {
            testArg.setType("String[]");
            testArg.setValue(unmarshalStringArr(value));
        } else if ("String".equals(type) || "java.lang.String".contains(type)) {
            testArg.setType("String");
            String withoutBraces = value.substring(0, value.length());
            testArg.setValue(withoutBraces);
        } else if ("List".equals(type) || "java.util.List".contains(type)) {
            testArg.setType("java.util.List");
            testArg.setValue(Arrays.asList(value));
        } else {

        }
        return testArg;
    }

    private int[] unmarshalIntegerArr(String s) {
        if (s.length() > 2) {
            String allArgs = StringUtils.substringBetween(s, "{", "}");
            String[] partsArgs = allArgs.split(",");
            int[] result = new int[partsArgs.length];
            for (int i = 0; i < partsArgs.length; i++) {
                result[i] = Integer.parseInt(partsArgs[i].trim());
            }
            return result;
        }
        return new int[0];
    }

    private String[] unmarshalStringArr(String s) {
        if (s.length() > 2) {
            String allArgs = StringUtils.substringBetween(s, "{", "}");
            String[] partsArgs = allArgs.split(",");
            String[] result = new String[partsArgs.length];
            for (int i = 0; i < partsArgs.length; i++) {
                result[i] = partsArgs[i].replaceAll("\"", "").trim();
            }
            return result;
        }
        return new String[0];
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
