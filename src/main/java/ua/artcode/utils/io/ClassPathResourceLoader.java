package ua.artcode.utils.io;

import java.io.File;
import java.net.URL;

public class ClassPathResourceLoader {

    public static File getFile(String path){
        URL resource = ClassPathResourceLoader.class.getResource(path);

        if(resource == null) {
            return new File(path);
        }

        return new File(resource.getFile());
    }
}
