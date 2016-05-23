package ua.artcode.experimental.future.core.v1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by serhii on 23.05.16.
 */
public class TestReflection {

    public static void main(String[] args) throws ClassNotFoundException {

        //load class
        Class cl = Class.forName("ua.artcode.experimental.future.core.v1.Example1");

        try {
            // run tests
            Method method = cl.getMethod("runTests");
            method.invoke(null);

            // then get test results
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }


    }

}

