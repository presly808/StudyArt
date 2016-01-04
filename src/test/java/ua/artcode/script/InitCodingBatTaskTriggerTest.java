package ua.artcode.script;

import org.codehaus.plexus.util.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ua.artcode.utils.io.AppPropertiesHolder;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class InitCodingBatTaskTriggerTest {

    private static String nameOfDb;
    private static File dump;
    private static File dataBase;
    private static File original;

    @BeforeClass
    public static void copyOriginalDump() {
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

    @Test
    public void createDumpOfDataBaseTest() {
        InitCodingBatTaskTrigger.createDumpOfDataBase();

        assertEquals(true, dataBase.exists());
    }

    @AfterClass
    public static void deleteDump() {
        try {
            FileUtils.deleteDirectory(dataBase);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (original != null) {
            original.renameTo(dataBase);
        }
    }
}
