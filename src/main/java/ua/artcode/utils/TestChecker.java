package ua.artcode.utils;

import org.apache.commons.lang.StringUtils;
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.utils.CodingBatTaskChecker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.*;

/**
 * Created by Maxim on 18.11.2015.
 */
public class TestChecker {
    public static void main(String[] args) {
//        CodingBatTaskChecker checker = new CodingBatTaskChecker();
//        checker.checkTask("p194667", "int countXX(String str) {\n" +
//                "  return 0;\n" +
//                "}");
 //       String str = "example";


//        CodingBatTaskGrabber grabber = new CodingBatTaskGrabber();
//        grabber.getTasksFromCodingBat();

//        CodingBatTask task = new CodingBatTask("54","p4567", "title", "description", "examples",
//                "public String test(String str, int[] arr) {  }", "testGroup");

       String str = "{6, 8, 9, 15, 44}, 66, 88, 1, {1, 44, 35}, 7, {9999}";
        String str2 = "8, {\"cat\", \"dog\", \"monkey\"}, 77, 11, \"fish\", {1, 2, 3}";
//
//        Pattern pattern = Pattern.compile("(\\{(\\.?[^\\{])+\\})+");
//        Matcher matcher = pattern.matcher(str);
//        while (matcher.find()) {
//            System.out.println(matcher.group());
//        }

//        Pattern pattern1 = Pattern.compile("\\d+[^\\{d+\\}\\,]");
//        Matcher matcher1 = pattern1.matcher(str);
//        while (matcher1.find()) {
//            System.out.println(matcher1.group());
//        }

        List<String> testData = parseTestData(str);
        for (String unit : testData) {
            System.out.println(unit);
        }

    }

    public static List<String> parseTestData(String line){

        line = line.trim();

        if(line.isEmpty()){
            return new LinkedList<>();
        }

        int endIndex = 0;

        if(line.startsWith("{")){
            endIndex = line.indexOf("}",1) + 1;
        } else if(line.startsWith("\"")){
            endIndex = line.indexOf("\"",1) + 1;
        } else if(line.startsWith("[")) {
            endIndex = line.indexOf("]",1) + 1;
        } else {
            int last = line.indexOf(",");
            endIndex = last != -1 ? last : line.length();
        }

        String res = line.substring(0, endIndex);

        endIndex = endIndex < line.length() ? endIndex + 1: endIndex;

        List<String> container = parseTestData(line.substring(endIndex, line.length()));
        container.add(0,res);

        return container;
    }
}
