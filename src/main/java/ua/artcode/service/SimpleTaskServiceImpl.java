package ua.artcode.service;

import org.apache.log4j.Logger;
import ua.artcode.dao.SimpleTaskDao;
import ua.artcode.db.ImplementedTaskContainer;
import ua.artcode.exception.AppException;
import ua.artcode.exception.AppValidationException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.model.codingbat.ResultContainer;
import ua.artcode.model.codingbat.TaskTestResult;
import ua.artcode.validation.TaskCreationValidator;
import ua.artcode.validation.Validator;

import ua.artcode.utils.serialization.AppDataStandartJavaSerializator;

import java.util.List;
import java.util.Scanner;

public class SimpleTaskServiceImpl implements SimpleTaskService {
    private String path;
    AppDataStandartJavaSerializator appDataStandartJavaSerializator;

    private SimpleTaskDao simpleTaskDao;
    private Scanner scanner;
    private static final Logger LOGGER = Logger.getLogger(SimpleTaskService.class);

    public SimpleTaskServiceImpl(SimpleTaskDao simpleTaskDao){
        this.simpleTaskDao = simpleTaskDao;
        appDataStandartJavaSerializator = new AppDataStandartJavaSerializator();
        scanner = new Scanner(System.in);
    }

    @Override
    public List<CodingBatTask> getAllByGroup(String groupName) throws AppException {
        return null;
    }

    @Override
    public CodingBatTask addTask(CodingBatTask codingBatTask) throws AppException {
        return simpleTaskDao.addTask(codingBatTask) ;
    }

    @Override
    public List<CodingBatTask> getAll() throws AppException {
        return simpleTaskDao.getAll();
    }

    @Override
    public CodingBatTask getTask(String id) throws NoSuchTaskException {
        return simpleTaskDao.findById(id);
    }


    @Override
    public ResultContainer checkTaskImplementation(CodingBatTask task) throws AppException {
        return null;
    }

    @Override
       public void saveTemplateToFile(CodingBatTask task) throws NoSuchTaskException {
        appDataStandartJavaSerializator.save(path,task);

    }

    @Override
    public void saveTaskTestResultToFile(TaskTestResult result) {
        appDataStandartJavaSerializator.save(path,result);
    }

    @Override
    public TaskTestResult.ImplementedTask loadImplementedTaskFromFile() {
        return (TaskTestResult.ImplementedTask) appDataStandartJavaSerializator.load(path);

    }

    @Override
    public void saveHistory(ImplementedTaskContainer implementedTaskContainer) {
        appDataStandartJavaSerializator.save(path,implementedTaskContainer);
    }



    @Override
    public CodingBatTask taskCreation() {
        Validator validator =  new TaskCreationValidator();
        boolean inputPass = false;
        CodingBatTask codingBatTask;
        do {
            System.out.println("Please enter title");
            String title = scanner.nextLine();
            System.out.println("Please enter description");
            String description = scanner.nextLine();
            System.out.println("Please enter example");
            String example = scanner.nextLine();
            System.out.println("Please enter template");
            String template = scanner.nextLine();
             codingBatTask = new CodingBatTask(title, description, example, template);
            try {
                if(validator.validate(codingBatTask) == true){
                    inputPass = true;
                }
            } catch (AppValidationException e) {
                e.printStackTrace();
            }
        } while(inputPass==false);
        return codingBatTask;
    }

}
