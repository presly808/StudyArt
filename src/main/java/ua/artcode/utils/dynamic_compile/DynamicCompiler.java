package ua.artcode.utils.dynamic_compile;

/**
 * Created by Razer on 28.12.15.
 */

import org.apache.log4j.Logger;
import ua.artcode.utils.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * use command for compile javac -cp /home/serhii/dev/apache-tomcat-7.0.57/webapps/ROOT/WEB-INF/classes/ /home/serhii/dev/javafun/temp/_sum59382.java
 */

public class DynamicCompiler {
    private static final Logger LOG = Logger.getLogger(DynamicCompiler.class);
//    public static final String LIBS_CLASSPATH = "/apache-tomcat/webapps/ROOT/WEB-INF/classes/";
    public static final String LIBS_CLASSPATH = getLibsClasspath();

    private static String getLibsClasspath() {
        StringBuilder classPaths = new StringBuilder();
        URL[] urls = ((URLClassLoader) (Thread.currentThread().getContextClassLoader())).getURLs();
        for (URL url : urls) {
            classPaths.append(url.getFile()).append(":");
        }

        return classPaths.toString();
    }


    public DynamicCompiler() {
    }

    public String compile(String path) {
        File sourceFile = new File(path); // TODO check if sourceFile redundant
        String message = null;
        try {
            String absolutePath = sourceFile.getCanonicalPath();
            String result=String.format("javac -cp  " + LIBS_CLASSPATH + " %s",absolutePath);
            //String result = "javac -cp " + LIBS_CLASSPATH + " " + absolutePath;
            Process pr = Runtime.getRuntime().exec(result);

            if (pr.waitFor() != 0) {
                message = FileUtils.getMessage(new BufferedReader(new InputStreamReader(pr.getErrorStream())));
                message = message.substring(message.indexOf(".") + 1, message.length());
            }
        } catch (IOException e) {
            LOG.error(e);
        } catch (InterruptedException e) {
            LOG.error(e);
        }

        return message;//; // return path to class
    }

}

