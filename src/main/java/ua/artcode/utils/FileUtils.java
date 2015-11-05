package ua.artcode.utils;

import ua.artcode.exception.PathNotFoundException;

import java.io.File;
import java.io.IOException;

/**
 * Created by Razer on 05.11.15.
 */
public  class  FileUtils {

    public static boolean exists(String path) {
        return new File(path).exists();
    }

    public static File createNewIfNotExists(String path){
        File file = new File(path);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return file;
    }
}
