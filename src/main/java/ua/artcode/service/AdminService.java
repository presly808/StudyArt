package ua.artcode.service;

import ua.artcode.exception.AppException;
import ua.artcode.exception.AppValidationException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.CodingBatTask;

import java.util.List;

/**
 * Created by Razer on 10.01.16.
 */
public interface AdminService {

    CodingBatTask addTask(CodingBatTask codingBatTask) throws AppValidationException;

    CodingBatTask getTask(String id) throws NoSuchTaskException;

    int size();

    List<CodingBatTask> getAll() throws AppException;

    boolean delete(String id);

}
