package ua.artcode.db;

import ua.artcode.model.CodingBatTask;
import ua.artcode.utils.Serializator;

import java.io.File;

/**
 * Created by serhii on 28.10.15.
 */
public class InitDataProcessor {

    private CodingBatTaskContainer container;
    private Serializator<CodingBatTaskContainer> serializator;

    // TODO dont use absolute path, use relative
    private String path = "/home/serhii/dev/tempVitalik/StudyArt/cache/app_db.txt";


    public InitDataProcessor(Serializator<CodingBatTaskContainer> serializator) {
        this.serializator = serializator;
    }

    private void initData(){
        container = new CodingBatTaskContainer();
        container.addTask(new CodingBatTask("1", "sum", "sum two numbers", "-", "public int sum(int a, int b){\n\n}"));
        container.addTask(new CodingBatTask("2", "mul", "multiply two numbers", "-", "public int mul(int a, int b){\n\n}"));
    }

    public CodingBatTaskContainer getContainer() {
        if(container == null){
            initData();
            /*if(new File(path).exists()){
                //container = serializator.load(path);
            } else {

                //serializator.save(path, container);
            }*/
        }

        return container;
    }

}
