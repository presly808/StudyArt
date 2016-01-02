package ua.artcode.preprocess;

import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.model.codingbat.TaskTestData;


public class DataUnmarshaller {

    private class ValueType {

        private String type;
        private Object value;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }

    public void convert(CodingBatTask task) {
        ValueType valueType;
        //int k=0;
        for (TaskTestData data : task.getTaskTestDataContainer().getTaskTestDataList()) {
            for (int i = 0; i < data.getInData().size(); i++) {
                valueType = convertDispatcher(task.getMethodSignature().getInArgList().get(i).getType(), data.getInData().get(i));
                task.getMethodSignature().getInArgList().get(i).setType(valueType.getType());
                //TODO expected value
                data.getInData().remove(i);
                data.getInData().add(i, valueType.getValue());
            }
            //ValueType valueType1 = convertDispatcher(task.getMethodSignature().getReturnType(), task.getTaskTestDataContainer().getTaskTestDataList().get(k++).getExpectedValue());
            //data.setExpectedValue(task.getTaskTestDataContainer().getTaskTestDataList().get(0).getExpectedValue().toString());
        }
    }


    //TODO think about collection (List, Set, Map)
    private ValueType convertDispatcher(String type, Object val) {
        String value = val.toString();
        ValueType valueType = new ValueType();
        if ("byte".equals(type) || "java.lang.Byte".contains(type)) {
            valueType.setType("Byte");
            valueType.setValue(unmarshalByte(value));
        } else if ("short".equals(type) || "java.lang.Short".contains(type)) {
            valueType.setType("Short");
            valueType.setValue(unmarshalShort(value));
        } else if ("int".equals(type) || "java.lang.Integer".contains(type)) {
            valueType.setType("Integer");
            valueType.setValue(unmarshalInteger(value));
        } else if ("long".equals(type) || "java.lang.Long".contains(type)) {
            valueType.setType("Long");
            valueType.setValue(unmarshalLong(value));
        } else if ("float".equals(type) || "java.lang.Float".contains(type)) {
            valueType.setType("Float");
            valueType.setValue(unmarshalFloat(value));
        } else if ("double".equals(type) || "java.lang.Double".contains(type)) {
            valueType.setType("Double");
            valueType.setValue(unmarshalDouble(value));
        } else if ("boolean".equals(type) || "java.lang.Boolean".contains(type)) {
            valueType.setType("Boolean");
            valueType.setValue(unmarshalBoolean(value));
        } else if ("char".equals(type) || "java.lang.Character".contains(type)) {
            valueType.setType("Character");
            valueType.setValue(unmarshalCharacter(value));
        } else if ("String".equals(type) || "java.lang.String".contains(type)) {
            valueType.setType("String");
            valueType.setValue(value);
        } else {
            return null; // TODO refactor this place
        }
        return valueType;
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
