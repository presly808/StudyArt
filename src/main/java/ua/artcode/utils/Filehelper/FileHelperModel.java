package ua.artcode.utils.Filehelper;


import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;


public class FileHelperModel implements FileModelInterface {



    private Path currentPath = Paths.get(System.getProperty("user.dir"));


    public Path getCurrentPath() {
        return currentPath;
    }

    public void setCurrentPath(Path currentPath) {
        this.currentPath = currentPath;
    }

    public boolean createNewFile(String fileName){

        try {
            Files.createFile(preparePath(fileName));
        } catch (IOException e) {
            System.out.printf("Error to create file: %s\nERROR- %s", preparePath(fileName).toString(), e.toString());
            return false;
        }
        return true;
    }



    public boolean changeCurrentLocation(String path){
        Path newPath = preparePath(path);

        if (Files.isDirectory(newPath.toAbsolutePath())){
            currentPath = newPath.toAbsolutePath();
            return true;
        }
        return false;
    }


    public List<String> find(String filter) {
        ArrayList foundList = new ArrayList();

        Path start = currentPath;
        int maxDepth = 5;
        try (Stream<Path> stream = Files.find(start, maxDepth, (path, attr) ->
                String.valueOf(path).contains(filter))) {
            stream.sorted().map(String::valueOf).forEach(foundList::add);

            stream.close();

        }  catch (IOException e) {
            System.out.printf("Error to output content: %s\nERROR- %s", filter, e.toString());
        }

        return foundList;
    }

    @Override
    public List<String> showDirectoryContent(String pathDirectory) {
        Path path = preparePath(pathDirectory);

        List<String> outList = new ArrayList<>();

        try (DirectoryStream<Path> dirStream = Files.newDirectoryStream(path)) {
            for (Path child : dirStream) {
                outList.add(child.getFileName().toString());
            }
            dirStream.close();
        } catch (IOException e) {
            System.out.printf("Error to output content: %s\nERROR- %s", path.toString(), e.toString());
        }
        return  outList;


    }


    public boolean delete(String dirName) {

        try {
            return Files.deleteIfExists(preparePath(dirName));
        } catch (IOException e) {
            System.out.printf("Error to delete: %s\nERROR- %s", preparePath(dirName).toString(), e.toString());
            return false;
        }

    }


    public boolean copyFile(String src, String dst) {
        Path srcPath = preparePath(src);
        Path dstPath = preparePath(dst);
        try {
            Files.copy(srcPath, dstPath);
        } catch (IOException e) {
            System.out.printf("Error to copy file: %s\nERROR- %s", srcPath.toString(), e.toString());
            return false;
        }
        return true;
    }

    @Override
    public boolean compareFiles(String pathToFile1, String pathToFile2) {
        Path pathFile1 = preparePath(pathToFile1);
        Path pathFile2 = preparePath(pathToFile2);

        try {
            return Files.isSameFile(pathFile1,pathFile2);
        } catch (IOException e) {
            System.out.printf("Error to compare:%s with %s\nERROR- %s", pathFile1.toString(), pathFile2.toString(), e.toString());
            return false;
        }
    }

    public boolean createNewDir(String dirName){

        try {
            Files.createDirectory(preparePath(dirName));
        } catch (IOException e) {
            System.out.printf("Error to create directory: %s\nERROR- %s", preparePath(dirName).toString(), e.toString());
            return false;
        }
        return true;
    }

    private Path preparePath(String path){

        if (path == "" || path == null) {
            return currentPath;
        }

        Path pathFile = Paths.get(path);
        if (!pathFile.isAbsolute()){
            pathFile = currentPath.resolve(pathFile);
        }
        return  pathFile.normalize();
    }

    public String runCommand(List<String> args){
        if (args.isEmpty() || args.get(0).equals("") || args.get(0).equals("exit") ){
            return "";
        }
        if (checkArguments(args, 1, "ls")){
            return String.join("\n", showDirectoryContent(null));
        }
        if (checkArguments(args, 2, "ls")){
            return String.join("\n", showDirectoryContent(args.get(1)));
        }
        if (checkArguments(args, 2, "mkdir")){
            if (createNewDir(args.get(1))){
                return "";
            }else {
                return " cannot create directory";
            }
        }
        if (checkArguments(args, 2, "touch")){
            if (createNewFile(args.get(1))){
                return "";
            }else {
                return " cannot create file";
            }
        }
        if (checkArguments(args, 2, "del")){
            if (delete(args.get(1))){
                return "";
            }else {
                return " cannot delete";
            }
        }
        if (checkArguments(args, 2, "cd")){
            if (changeCurrentLocation(args.get(1))){
                return "";
            }else {
                return " error";
            }
        }

        if (checkArguments(args, 2, "find")){
            return String.join("\n", find(args.get(1)));
        }

        if (checkArguments(args, 3, "copy")){
            if (copyFile(args.get(1), args.get(2))){
                return "";
            } else{
                return " error";
            }
        }


        return " command not found";
    }

    private boolean checkArguments (List<String> args, int lenght, String name){

        if (args.size() > 0 && args.size() == lenght){
            if (args.get(0).equals(name)){
                return true;
            }
        }
        return  false;
    }

}
