package ua.artcode.utils;

import jdk.nashorn.internal.runtime.Source;
import org.junit.*;
import org.junit.runners.MethodSorters;
import ua.artcode.model.common.Task;
import ua.artcode.utils.io.FileUtils;
import ua.artcode.utils.serialization.AppDataJsonSerializer;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;
import static ua.artcode.utils.RandomDataGenerator.generateNameWith;
import static ua.artcode.utils.RandomDataGenerator.generateRandomId;

@Ignore
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTaskJsonSerializer {

    private static Collection<Task> codingBatTasks;

    private static File dbTaskPath;

    private static AppDataJsonSerializer appDataJsonSerializer;

    @BeforeClass
    public static void beforeAllTest() {

        dbTaskPath = FileUtils.createNewIfNotExists("target/app_db.json");

        appDataJsonSerializer = new AppDataJsonSerializer(Task.class);

        codingBatTasks = Stream.generate(() -> new Task(
                generateNameWith("title", 100),
                generateNameWith("desc ", 100), generateNameWith("example ", 100), generateNameWith("tamplate ", 100),generateRandomId())).
                limit(10).
                collect(Collectors.toList());

    }
    @Test
    public void _01testSimpleSaving() {
        appDataJsonSerializer.save(codingBatTasks, dbTaskPath.getPath());

        try {
            String fileContent = new String(Source.readFully(dbTaskPath));

            //long savedTasks = codingBatTasks.stream().filter(codingBatTask -> fileContent.contains(codingBatTask.getCodingBatId())).count();

            //assertEquals(savedTasks, codingBatTasks.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void _02testSimpleLoading() {
        Collection<Task> loadedTasks = appDataJsonSerializer.load(dbTaskPath.getPath());
        //TODO use logger
        //loadedTasks.stream().forEach(System.out::println);

        assertTrue(loadedTasks.containsAll(codingBatTasks));
    }

    @AfterClass
    public static void afterAllTests() {
        dbTaskPath.delete();
    }


}
