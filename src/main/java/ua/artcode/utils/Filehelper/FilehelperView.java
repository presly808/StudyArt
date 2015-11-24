package ua.artcode.utils.Filehelper;

public class FileHelperView {
    public void display(String startLine, String middleLine, String endLine){
        if (startLine == null){
            startLine = "";
        }
        if (middleLine == null){
            middleLine = "";
        }
        if (endLine == null){
            endLine = "";
        }
        System.out.printf("%s%s%s",startLine, middleLine, endLine);

    }
    public void displayHelp(){
        String help = "FileHelper tool:\n"
                + "help - show all available commands,\n"
                + "cd  - change current location,\n"
                + "find - find file(dir),\n"
                + "ls - show directory content,\n"
                + "delete - delete file or dir\n"
                + "touch - create file\n"
                + "mkdir - create directory\n"
                + "copy - copy files\n";

        System.out.println(help);

    }
}
