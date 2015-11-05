package ua.artcode.utils;

import ua.artcode.exception.PathNotFoundException;

import java.io.File;
import java.io.IOException;

/**
 * Created by Razer on 05.11.15.
 */
public  class  FileUtils {

    public static boolean isFile(String path) {
        File file = new File(path);
        if (file.isFile()) {
            return true;
        } else {
            try {
                throw new PathNotFoundException("Path not found");
            } catch (PathNotFoundException e) {
                e.printStackTrace();
            }
        }
        return false;
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
