package ua.artcode.utils;

import java.io.File;
import java.net.URL;

/**
 * Created by serhii on 04.11.15.
 */
public class ClassPathResourceLoader {


    public static File getFile(String path){
        URL resource = ClassPathResourceLoader.class.getResource(path);

        if(resource == null) {
            return new File(path);
        }

        return new File(resource.getFile());
    }
}
