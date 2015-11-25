package ua.artcode.utils;

import ua.artcode.utils.CodingBatTaskChecker;

/**
 * Created by Maxim on 18.11.2015.
 */
public class TestChecker {
    public static void main(String[] args) {
        CodingBatTaskChecker checker = new CodingBatTaskChecker();

        checker.checkTask("p187868", "public boolean sleepIn(boolean weekday, boolean vacation) {\n" +
                "  return true;\n" +
                "}");

//        CodingBatTaskGrabber grabber = new CodingBatTaskGrabber();
//        grabber.getTasksFromCodingBat();

    }
}
