package ua.artcode.script;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.artcode.utils.SpringContext;
import ua.artcode.utils.io.AppPropertiesHolder;

import java.io.File;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class InitCodingBatTaskTriggerTest {

    private static String nameOfDb;
    private static File dump;
    private static File dataBase;
    private static File original;

    @BeforeClass
    public static void copyOriginalDump() {
        // todo get from spring
        String proper = SpringContext.getContext().getEnvironment().getProperty("mongo.db");
        nameOfDb = AppPropertiesHolder.getProperty("mongo.db");
        dump = new File("dump");
        if (!dump.exists()) {
            dump.mkdir();
            dump.deleteOnExit();
        }

        dataBase = new File(dump + "/" + nameOfDb);
        if (dataBase.exists()) {
            original = new File(dump + "/" + "original");
            dataBase.renameTo(original);
        }

    }

    public static void removeDirectory(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null && files.length > 0) {
                for (File aFile : files) {
                    removeDirectory(aFile);
                }
            }
        }

        dir.delete();
    }

    @Test
    public void createDumpOfDataBaseTest() {
        InitCodingBatTaskTrigger.createDumpOfDataBase();

        assertEquals(true, dataBase.exists());
    }

    @AfterClass
    public static void deleteDump() {
        removeDirectory(dataBase);
        if (original != null) {
            original.renameTo(dataBase);
        }
    }
}
