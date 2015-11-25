package ua.artcode.utils.Filehelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class FileHelper {
    public static void main(String[] args){

        InputStreamReader r=new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(r);
        FileHelperModel fileHelper = new FileHelperModel();
        FileHelperView view = new FileHelperView();

        String name="";
        // todo see try-with-resources
        try {

        while(!name.equals("exit")){

            String currentPath = fileHelper.getCurrentPath().toString().replace("\n", "").replace("\r", "");
            view.display(currentPath, "#", null);
            name=br.readLine();
            if (name.equals("help")){
                view.displayHelp();
            } else {
                List<String> listArgs = Arrays.asList(name.split(" "));
                String out = fileHelper.runCommand(listArgs).toString();
                if (!out.equals("") || !out.equals("exit") || !out.equals("quit")) {
                    view.display(null, null, out + "\n");
                }
            }
        }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                r.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}



