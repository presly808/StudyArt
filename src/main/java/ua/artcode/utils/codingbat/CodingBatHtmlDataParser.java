package ua.artcode.utils.codingbat;

import java.util.*;

/**
 * DOESN'T WORK WITH RECURSIVE DATA LIKE {1,2,3, {12, {23}}} !!!!
 */
public class CodingBatHtmlDataParser {

    // {1,2,3,4},4,5,6,"word1","word2",2.87,{2,2,2,2},2,2


    public static List<String> parseTestData(String line) {

        line = line.trim();

        if (line.isEmpty()) {
            return new LinkedList<>();
        }

        int endIndex;

        if (line.startsWith("{")) {
            endIndex = line.indexOf("}", 1) + 1;
        } else if (line.startsWith("\"")) {
            endIndex = line.indexOf("\"", 1) + 1;
        } else if (line.startsWith("[")) {
            endIndex = line.indexOf("]", 1) + 1;
        } else {
            int last = line.indexOf(",");
            endIndex = last != -1 ? last : line.length();
        }


        String res = line.substring(0, endIndex);
        res = trimStringIfNeeded(res);

        endIndex = endIndex < line.length() ? endIndex + 1 : endIndex;

        List<String> container = parseTestData(line.substring(endIndex, line.length()));

        container.add(0, res);

        return container;
    }
    // Trim string from html. Like ""something"" return "something"
    public static String trimStringIfNeeded(String str) {
        if (str != null && str.startsWith("\"")) {
            str = str.replace("\"", "");
        }
        return str;
    }

}
