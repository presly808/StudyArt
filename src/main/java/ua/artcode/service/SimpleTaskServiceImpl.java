package ua.artcode.service;

import ua.artcode.dao.SimpleTaskDao;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.CodingBatTask;
import ua.artcode.model.ImplementedTask;
import ua.artcode.model.ResultContainer;
import ua.artcode.model.TaskTestResult;
import ua.artcode.utils.serialization.AppDataStandartJavaSerializator;

import java.util.List;

public class SimpleTaskServiceImpl implements SimpleTaskService {
    private String path;
    AppDataStandartJavaSerializator appDataStandartJavaSerializator;

    private SimpleTaskDao simpleTaskDao;

    public SimpleTaskServiceImpl(SimpleTaskDao simpleTaskDao){
        this.simpleTaskDao = simpleTaskDao;
        appDataStandartJavaSerializator = new AppDataStandartJavaSerializator();
    }

    @Override
    public List<CodingBatTask> getAllByGroup(String groupName) throws AppException {
        return null;
    }

    @Override
    public List<CodingBatTask> addTask(CodingBatTask codingBatTask) throws AppException {
        return null;
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
    public void saveTaskTestResult(TaskTestResult result) {
        appDataStandartJavaSerializator.save(path,result);
    }

    @Override
    public ImplementedTask loadImplementedTaskFromFile() {
        return (ImplementedTask) appDataStandartJavaSerializator.load(path);

    }


}
