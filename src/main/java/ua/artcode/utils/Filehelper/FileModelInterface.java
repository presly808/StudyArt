package ua.artcode.utils.Filehelper;

import java.util.Collection;

public interface FileModelInterface {

    boolean createNewDir(String dirName);

    boolean createNewFile(String dirName);

    boolean changeCurrentLocation(String dirName);

    Collection<String> find(String filter);

    Collection<String> showDirectoryContent(String path);

    boolean delete (String name);

    boolean copyFile (String src, String dst);

    boolean compareFiles(String pathToFile1, String pathToFile2);

}
