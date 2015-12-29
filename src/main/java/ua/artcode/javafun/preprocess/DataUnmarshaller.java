package ua.artcode.javafun.preprocess;

import ua.artcode.javafun.model.DataPoint;
import ua.artcode.javafun.model.TestArg;
import ua.artcode.javafun.model.TestCase;


public class DataUnmarshaller {

    public void convert(TestCase testCase) {
        for (DataPoint step : testCase.getDataPointList()) {
            for (TestArg arg : step.getIn()) {
                arg.setValue(convertDispatcher(arg));
            }

            TestArg expected = step.getExpected();
            expected.setValue(convertDispatcher(expected));

        }
    }

    //TODO think about collection (List, Set, Map)
    private Object convertDispatcher(TestArg arg) {
        String type = arg.getType();
        String value = arg.getValue().toString();
        if ("byte".equals(type) || "java.lang.Byte".contains(type)) {
            arg.setType("Byte");
            return unmarshalByte(value);
        } else if ("short".equals(type) || "java.lang.Short".contains(type)) {
            arg.setType("Short");
            return unmarshalShort(value);
        } else if ("int".equals(type) || "java.lang.Integer".contains(type)) {
            arg.setType("Integer");
            return unmarshalInteger(value);
        } else if ("long".equals(type) || "java.lang.Long".contains(type)) {
            arg.setType("Long");
            return unmarshalLong(value);
        } else if ("float".equals(type) || "java.lang.Float".contains(type)) {
            arg.setType("Float");
            return unmarshalFloat(value);
        } else if ("double".equals(type) || "java.lang.Double".contains(type)) {
            arg.setType("Double");
            return unmarshalDouble(value);
        } else if ("boolean".equals(type) || "java.lang.Boolean".contains(type)) {
            arg.setType("Boolean");
            return unmarshalBoolean(value);
        } else if ("char".equals(type) || "java.lang.Character".contains(type)) {
            arg.setType("Character");
            return unmarshalCharacter(value);
        } else if ("String".equals(type) || "java.lang.String".contains(type)) {
            return arg.getValue();
        } else {
            return arg.getValue(); // TODO refactor this place
        }
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
