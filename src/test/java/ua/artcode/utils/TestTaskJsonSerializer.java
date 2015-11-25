package ua.artcode.utils;

import jdk.nashorn.internal.runtime.Source;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import ua.artcode.model.CodingBatTask;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static ua.artcode.utils.RandomDataGenerator.generateNameWith;
import static ua.artcode.utils.RandomDataGenerator.generateRandomId;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTaskJsonSerializer {

    private static Collection<CodingBatTask> codingBatTasks;

    private static File dbTaskPath;

    private static AppDataJsonSerializer appDataJsonSerializer;

    @BeforeClass
    public static void beforeAllTest(){

        dbTaskPath = FileUtils.createNewIfNotExists("target/app_db.json");

        appDataJsonSerializer = new AppDataJsonSerializer(CodingBatTask.class);

        codingBatTasks = Stream.generate(() -> new CodingBatTask(generateRandomId(),
                    generateNameWith("title",100),
                    generateNameWith("desc ", 100),generateNameWith("example ", 100),generateNameWith("tamplate ", 100))).
                limit(10).
                collect(Collectors.toList());

    }

    @Test
    public void _01testSimpleSaving(){
        appDataJsonSerializer.save(codingBatTasks, dbTaskPath.getPath());

        try {
            String fileContent = new String(Source.readFully(dbTaskPath));

            long savedTasks = codingBatTasks.stream().filter(codingBatTask -> fileContent.contains(codingBatTask.getId())).count();

            assertEquals(savedTasks, codingBatTasks.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void _02testSimpleLoading(){
        Collection<CodingBatTask> loadedTasks = appDataJsonSerializer.load(CodingBatTask.class, dbTaskPath.getPath());
        //TODO use logger
        //loadedTasks.stream().forEach(System.out::println);

        assertTrue(loadedTasks.containsAll(codingBatTasks));
    }

    @AfterClass
    public static void afterAllTests(){
        dbTaskPath.delete();
    }


}
