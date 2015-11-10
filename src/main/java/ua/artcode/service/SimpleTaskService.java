package ua.artcode.service;

import ua.artcode.db.ImplementedTaskContainer;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.CodingBatTask;
import ua.artcode.model.ImplementedTask;
import ua.artcode.model.ResultContainer;
import ua.artcode.model.TaskTestResult;

import java.util.List;

public interface SimpleTaskService {

    List<CodingBatTask> getAllByGroup(String groupName) throws AppException;

    List<CodingBatTask> addTask(CodingBatTask codingBatTask) throws AppException;

    List<CodingBatTask> getAll() throws AppException;

    CodingBatTask getTask(String id) throws AppException;

    ResultContainer checkTaskImplementation(CodingBatTask task) throws AppException;

    void saveTemplateToFile (CodingBatTask task) throws NoSuchTaskException;

    void saveTaskTestResultToFile (TaskTestResult result);

    ImplementedTask loadImplementedTaskFromFile();

    void saveHistory(ImplementedTaskContainer implementedTaskContainer);



}
