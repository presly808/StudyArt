package ua.artcode.utils.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

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
    public static String getFile(String path){
        StringBuilder sb = new StringBuilder();
        try {
            Scanner sc = new Scanner(new File(path));
            while(sc.hasNextLine()){
                sb.append(sc.nextLine()).append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String getMessage(BufferedReader bf){
        StringBuilder sb = new StringBuilder();
        try {
            while(bf.ready()){
                sb.append(bf.readLine()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();

    }
}
