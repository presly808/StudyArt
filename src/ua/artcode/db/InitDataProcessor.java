package ua.artcode.db;

import ua.artcode.model.CodingBatTask;
import ua.artcode.utils.Serializator;

import java.io.File;

/**
 * If you have some problem with serialization, just delete cache, then rerun
 */
public class InitDataProcessor {

    private CodingBatTaskContainer container;
    private Serializator<CodingBatTaskContainer> serializator;

    // TODO dont use absolute path, use relative, add resource to classpath then get via getResource method
    final File f = new File(InitDataProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath());
    String study_art_dir = absoluteParentPath(f.getPath(), 3);
    String default_db = "/cache/app_db.txt";
    private String path = study_art_dir.concat(default_db);
    //private String path = "/home/serhii/dev/tempVitalik/StudyArt/cache/app_db.txt";


    public InitDataProcessor(Serializator<CodingBatTaskContainer> serializator) {
        this.serializator = serializator;
    }

    private void initData(){
        container = new CodingBatTaskContainer();
        container.addTask(new CodingBatTask("1", "sum", "sum two numbers", "-", "public int sum(int a, int b){}"));
        container.addTask(new CodingBatTask("2", "mul", "multiply two numbers", "-", "public int mul(int a, int b){}"));
    }

    public CodingBatTaskContainer getContainer() {
        if(container == null){
            if(new File(path).exists()){
                container = serializator.load(path);
            } else {
                initData();
                serializator.save(path, container);
            }
        }

        return container;
    }

    public static String absoluteParentPath(String path, int levelUp) {
        if (levelUp-- > 0 && levelUp < 5) {
            String parentDir = new File(path).getParent();
            return absoluteParentPath(parentDir, levelUp);
        }
        return path;
    }

}
