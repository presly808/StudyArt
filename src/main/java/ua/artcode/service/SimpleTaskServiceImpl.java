package ua.artcode.service;

import org.apache.log4j.Logger;
import ua.artcode.dao.CodingBatTaskDao;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.ImplementedTask;
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.model.codingbat.ResultContainer;
import ua.artcode.model.codingbat.TaskTestResult;
import ua.artcode.utils.serialization.AppDataStandartJavaSerializator;

import java.util.List;
import java.util.Scanner;

//@Service
public class SimpleTaskServiceImpl implements CodingBatTaskService {

    private String path;

    //@Autowired
    private AppDataStandartJavaSerializator appDataStandartJavaSerializator;

    //@Autowired
    private CodingBatTaskDao codingBatTaskDao;

    private Scanner scanner;

    private static final Logger LOGGER = Logger.getLogger(CodingBatTaskService.class);

    public SimpleTaskServiceImpl() {
    }

    public SimpleTaskServiceImpl(CodingBatTaskDao codingBatTaskDao){
        this.codingBatTaskDao = codingBatTaskDao;

        scanner = new Scanner(System.in);
    }

    @Override
    public List<CodingBatTask> getAllByGroup(String groupName) throws AppException {
        return null;
    }

    @Override
    public CodingBatTask addTask(CodingBatTask codingBatTask) throws AppException {
        return codingBatTaskDao.addTask(codingBatTask) ;
    }

    @Override
    public List<CodingBatTask> getAll() throws AppException {
        return codingBatTaskDao.getAll();
    }

    @Override
    public CodingBatTask getTask(String id) throws NoSuchTaskException {
        return codingBatTaskDao.findById(id);
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
    public ImplementedTask loadImplementedTaskFromFile() {
        return (ImplementedTask) appDataStandartJavaSerializator.load(path);

    }

    @Override
    public CodingBatTask taskCreation() {

        return null;
    }

}
