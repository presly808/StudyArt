package ua.artcode.utils.Filehelper;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import ua.artcode.exception.NoSuchUserException;
import ua.artcode.exception.UserAccountExistException;
import ua.artcode.exception.UserAuthenticationFailException;

import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;


//TODO finish test
@RunWith(MockitoJUnitRunner.class)
public class FileHelperModelTest {


    private FileHelperModel fileController;
    private String path1 = "..";
    private String path2 = "C:\\WORK\\JAVA\\PROJECT"; //Should be changed to UNIX path
    //for create and delete file
    private String path3 = "testFileControlTest.txt";
    //for create and delete directory
    private String path4 = "testFileControlTest";
    //For finding
    private String filter1 = "test";
    private String path5 = "DIR";
    private String path6 = "testFile.txt";

        @Before
        public void setUp() throws UserAuthenticationFailException, NoSuchUserException, UserAccountExistException {
            fileController =  new FileHelperModel();
        }


        @Test
        public void changeCurrentLocationRelativeTest() {

            System.out.println("FileHelper TEST #changeCurrentLocationRelativeTest");
            Path beforePath = fileController.getCurrentPath();
            fileController.changeCurrentLocation(path1);
            Path currentPath = fileController.getCurrentPath();
            System.out.println(beforePath.toString());
            System.out.println(currentPath.toString());
            //Verdict of test
            assertEquals(currentPath, beforePath.getParent());
        }

        @Test
        public void changeCurrentLocationAbsoluteTest() {

            System.out.println("FileHelper TEST #changeCurrentLocationAbsoluteTest");
            Path beforePath = fileController.getCurrentPath();
            fileController.changeCurrentLocation(path2);
            Path currentPath = fileController.getCurrentPath();
            System.out.println(beforePath.toString());
            System.out.println(currentPath.toString());
            //Verdict of test
            assertEquals(currentPath.toString(), path2);
    }

        @Test
        public void showDirectoryContentTest() {

            System.out.println("FileHelper TEST #showDirectoryContentTest");
            ArrayList<String> relativelist = (ArrayList<String>) fileController.showDirectoryContent(path1);
            ArrayList<String> absolutelist = (ArrayList<String>) fileController.showDirectoryContent(path2);
            System.out.println(relativelist.toString());
            System.out.println(absolutelist.toString());
            //Verdict of test
            assertEquals(relativelist, absolutelist);
        }

        @Test
        public void createNewFileDeleteTest() {

            System.out.println("FileHelper TEST #createNewFileDeleteTest");
            fileController.changeCurrentLocation(path2);
            boolean createResult = fileController.createNewFile(path3);
            ArrayList<String> listFileBefore = (ArrayList<String>) fileController.showDirectoryContent(null);
            System.out.println(listFileBefore.toString());
            assertEquals(createResult, true);
            boolean deleteResult = fileController.delete(path3);
            ArrayList<String> listFileAfter = (ArrayList<String>) fileController.showDirectoryContent(null);
            System.out.println(listFileAfter.toString());
            //Verdict of test
            assertEquals(deleteResult, true);


        }

        @Test
        public void createNewFolderDeleteTest() {

            System.out.println("FileHelper TEST #createNewFolderDeleteTest");
            fileController.changeCurrentLocation(path2);
            boolean createResult = fileController.createNewDir(path4);
            ArrayList<String> listFileBefore = (ArrayList<String>) fileController.showDirectoryContent(null);
            System.out.println(listFileBefore.toString());
            assertEquals(createResult, true);
            boolean deleteResult = fileController.delete(path4);
            ArrayList<String> listFileAfter = (ArrayList<String>) fileController.showDirectoryContent(null);
            System.out.println(listFileAfter.toString());
            //Verdict of test
            assertEquals(deleteResult, true);


        }

        @Test
        public void findFileTest() {

            System.out.println("FileHelper TEST #findFileTest");
            //create directory and file
            fileController.changeCurrentLocation(path2);
            fileController.createNewDir(path5);
            fileController.changeCurrentLocation(path5);
            fileController.createNewFile(path6);

            ArrayList<String> listFile = (ArrayList<String>) fileController.showDirectoryContent(null);
            String expectedFilePath = listFile.get(0);

            fileController.changeCurrentLocation(path1);
            ArrayList<String> listFileFiltered = (ArrayList<String>) fileController.find(filter1);
            System.out.println(expectedFilePath);
            System.out.println(listFileFiltered.toString());
            //delete directory and file
            fileController.changeCurrentLocation(path2);
            fileController.changeCurrentLocation(path5);
            fileController.delete(path6);
            fileController.changeCurrentLocation(path2);
            fileController.delete(path5);
            //Verdict of test
            assertEquals(listFileFiltered.contains(expectedFilePath), true);


        }

        @Test
        public void copyCompareTest() {

            System.out.println("FileHelper TEST #copyCompareTest");
            //create directory and file
            fileController.changeCurrentLocation(path2);
            fileController.createNewDir(path5);
            fileController.changeCurrentLocation(path5);
            fileController.createNewFile(path6);

            boolean resultCopy = fileController.copyFile(path6, path3);

            //Verdict of test
            assertEquals(resultCopy, true);

            boolean resultCompare = fileController.compareFiles(path6, path3);

            //Verdict of test
            assertEquals(resultCopy, true);

            ArrayList<String> listFile = (ArrayList<String>) fileController.showDirectoryContent(null);
            System.out.println(listFile.toString());

            //delete directory and file
            fileController.delete(path3);
            fileController.delete(path6);
            fileController.changeCurrentLocation(path2);
            fileController.delete(path5);

        }
}
