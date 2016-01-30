package ua.artcode.script;

import org.codehaus.plexus.util.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class InitCodingBatTaskTriggerTest {

    @Value("${mongo.db}")
    private static String nameOfDb;
    private static File dump;
    private static File dataBase;
    private static File original;

    @Before
    public void copyOriginalDump() {
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

    @After
    public  void deleteDump() {
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
