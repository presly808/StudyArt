package ua.artcode.service;

import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.CodingBatTask;
import ua.artcode.model.ResultContainer;

import java.util.List;

public interface SimpleTaskService {

    List<CodingBatTask> getAll() throws AppException;

    CodingBatTask getTask(String id) throws AppException;

    ResultContainer checkTaskImplementation(CodingBatTask task) throws AppException;

}
