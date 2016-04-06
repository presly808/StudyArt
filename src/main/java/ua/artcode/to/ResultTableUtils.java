package ua.artcode.to;

import ua.artcode.model.common.Task;
import ua.artcode.model.taskComponent.TaskTestResult;
import ua.artcode.utils.codingbat.CodingBatTaskUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Maxim on 11.02.2016.
 */
public class ResultTableUtils {

    public static List<ResultTablePart> createTable(Task task) {
        List<ResultTablePart> resultTablePartList = new ArrayList<>();
        int size = task.getTaskTestDataContainer().getTaskTestDataList().size();
        for (int i = 0; i < size; i++) {
            Object value = task.getTaskTestDataContainer().getTaskTestDataList().get(i).getValue();
            String expectedValue = String.valueOf(value);
            String inArgsTemplate = getInArgsTemplate(task, i);
            resultTablePartList.add(new ResultTablePart(expectedValue, inArgsTemplate));
            getInArgsTemplate(task, i);
        }
        return resultTablePartList;
    }

    public static List<ResultTablePart> getResultTableList(Task task, TaskTestResult taskResult, List<ResultTablePart> resultTablePartList) {
        for (int i = 0; i < taskResult.getActualValues().size(); i++) {
            ResultTablePart tablePart = resultTablePartList.get(i);
            Object actualValue = taskResult.getActualValues().get(i);
            if (actualValue.getClass().isArray()) {
                String actualValueToString = Arrays.toString((Object[]) actualValue);
                tablePart.setActualValue(actualValueToString);
            } else {
                tablePart.setActualValue(actualValue.toString());
            }
            String result = taskResult.getResults().get(i);
            tablePart.setResult(result);
        }
        return resultTablePartList;
    }

    private static String getInArgsTemplate(Task task, int iter) {
        String inArgsTemplate = CodingBatTaskUtils.getMethodName(task.getTemplate()) + "(";
        List inArgList = task.getTaskTestDataContainer().getTaskTestDataList().get(iter).getInData();
        for (int i = 0; i < inArgList.size(); i++) {
            inArgsTemplate += inArgList.get(i).toString();
            if (i != inArgList.size() - 1) {
                inArgsTemplate += ",";
            }
        }
        return inArgsTemplate + ")";
    }
}
