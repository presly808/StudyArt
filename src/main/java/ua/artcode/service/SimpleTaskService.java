package ua.artcode.service;

import ua.artcode.exception.AppException;
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.model.codingbat.ResultContainer;

import java.util.List;

public interface SimpleTaskService {

    List<CodingBatTask> getAllByGroup(String groupName) throws AppException;

    List<CodingBatTask> addTask(CodingBatTask codingBatTask) throws AppException;

    List<CodingBatTask> getAll() throws AppException;

    CodingBatTask getTask(String id) throws AppException;

    ResultContainer checkTaskImplementation(CodingBatTask task) throws AppException;

}
