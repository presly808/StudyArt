package ua.artcode.to;

import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.model.codingbat.TaskTestResult;
import ua.artcode.utils.codingbat.CodingBatTaskUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxim on 11.02.2016.
 */
public class ResultTableUtils {

    public static List<ResultTablePart> getResultTableList(CodingBatTask task, TaskTestResult taskResult) {
        List<ResultTablePart> resultTablePartList = new ArrayList<>();
        for (int i = 0; i < taskResult.getActualValues().size(); i++) {
            String inArgsTemplate = getInArgsTemplate(task, i);
            String actualValue = taskResult.getActualValues().get(i);
            String expectedValue = (String) taskResult.getExpectedValues().get(i);
            String result = taskResult.getResults().get(i);

            resultTablePartList.add(new ResultTablePart(inArgsTemplate, actualValue, expectedValue, result));
        }
        return resultTablePartList;
    }

    private static String getInArgsTemplate(CodingBatTask task, int iter) {
        String inArgsTemplate = CodingBatTaskUtils.getMethodName(task.getTemplate()) + "(";
        List inArgList = task.getTaskTestDataContainer().getTaskTestDataList().get(iter).getInData();
        for (int i = 0; i < inArgList.size(); i++) {
            inArgsTemplate+=inArgList.get(i).toString();
            if (i != inArgList.size()-1) {
                inArgsTemplate+=",";
            }
        }
        return inArgsTemplate + ")";
    }
}
