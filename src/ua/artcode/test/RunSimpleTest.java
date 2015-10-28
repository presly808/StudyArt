package ua.artcode.test;

import ua.artcode.dao.SimpleTaskDaoImpl;
import ua.artcode.db.CodingBatTaskContainer;
import ua.artcode.db.InitDataProcessor;
import ua.artcode.exception.AppException;
import ua.artcode.model.CodingBatTask;
import ua.artcode.service.SimpleTaskService;
import ua.artcode.service.SimpleTaskServiceImpl;
import ua.artcode.utils.AppDataStandartJavaSerializator;
import ua.artcode.utils.Serializator;

import java.util.List;

/**
 * Created by serhii on 28.10.15.
 */
public class RunSimpleTest {


    public static void main(String[] args) {
        Serializator<CodingBatTaskContainer> serializator = new AppDataStandartJavaSerializator<>();
        InitDataProcessor dataProcessor = new InitDataProcessor(serializator);
        CodingBatTaskContainer codingBatTaskContainer = dataProcessor.getContainer();

        SimpleTaskService simpleTaskService =
                new SimpleTaskServiceImpl(
                        new SimpleTaskDaoImpl(codingBatTaskContainer));

        try {
            List<CodingBatTask> taskList = simpleTaskService.getAll();
            taskList.stream().forEach(System.out::print);
            // java 8, streams, lambda -> (reference to method)
        } catch (AppException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(simpleTaskService.getTask("1"));
        } catch (AppException e) {
            e.printStackTrace();
        }


    }
}
