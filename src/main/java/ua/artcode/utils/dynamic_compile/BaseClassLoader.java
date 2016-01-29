package ua.artcode.utils.dynamic_compile;

import org.apache.log4j.Logger;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

//todo try to init class loader only once for several loading
public class BaseClassLoader {

    private static final Logger LOG = Logger.getLogger(BaseClassLoader.class);

    private static URLClassLoader classLoader = null;

    public static Class uriLoadClass(File root, String className) {
        Class cl = null;

        try {
            if (classLoader == null)
                classLoader = URLClassLoader.newInstance(new URL[]{root.toURI().toURL()}, BaseClassLoader.class.getClassLoader());

            cl = Class.forName(className, true, classLoader);
        } catch (ClassNotFoundException | MalformedURLException e) {
            LOG.error(e.getMessage());
        }

        return cl;
    }

}
