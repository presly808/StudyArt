package ua.artcode.db;

import ua.artcode.model.CodingBatTask;
import ua.artcode.utils.ClassPathResourceLoader;
import ua.artcode.utils.serialization.Serializator;

import java.io.File;

/**
 * If you have some problem with serialization, just delete cache, then rerun
 */
public class InitDataProcessor {

    private CodingBatTaskContainer container;
    private Serializator<CodingBatTaskContainer> serializator;

    // TODO dont use absolute path, use relative, add resource to classpath then get via getResource method
    final File dbFile;
    //private String path = "/home/serhii/dev/tempVitalik/StudyArt/cache/app_db.txt";


    public InitDataProcessor(Serializator<CodingBatTaskContainer> serializator) {
        this.serializator = serializator;

        dbFile = ClassPathResourceLoader.getFile("/user_db.txt");
    }

    private void initData(){
        container = new CodingBatTaskContainer();
        container.addTask(new CodingBatTask("1", "sum", "sum two numbers", "-", "public int sum(int a, int b){}"));
        container.addTask(new CodingBatTask("2", "mul", "multiply two numbers", "-", "public int mul(int a, int b){}"));
    }

    public CodingBatTaskContainer getContainer() {
        if(container == null){
            if(dbFile.exists()){
                container = serializator.load(dbFile.getPath());
            } else {
                initData();
                serializator.save(dbFile.getPath(), container);
            }
        }

        return container;
    }

}
