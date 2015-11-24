package ua.artcode.service;

import ua.artcode.db.ImplementedTaskContainer;
import ua.artcode.exception.AppException;
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.model.codingbat.ResultContainer;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.TaskTestResult;

import java.util.List;

public interface SimpleTaskService {

    List<CodingBatTask> getAllByGroup(String groupName) throws AppException;

    CodingBatTask addTask(CodingBatTask codingBatTask) throws AppException;

    List<CodingBatTask> getAll() throws AppException;

    CodingBatTask getTask(String id) throws AppException;

    ResultContainer checkTaskImplementation(CodingBatTask task) throws AppException;

    CodingBatTask taskCreation();

    void saveTemplateToFile (CodingBatTask task) throws NoSuchTaskException;

    void saveTaskTestResultToFile (TaskTestResult result);

    TaskTestResult.ImplementedTask loadImplementedTaskFromFile();

    void saveHistory(ImplementedTaskContainer implementedTaskContainer);



}
