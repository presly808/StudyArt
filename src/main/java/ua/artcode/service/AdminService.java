package ua.artcode.service;

import ua.artcode.exception.AppValidationException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.CodingBatTask;

/**
 * Created by Razer on 10.01.16.
 */
public interface AdminService {

    CodingBatTask addTask(CodingBatTask codingBatTask) throws AppValidationException;

    CodingBatTask getTask(String id) throws NoSuchTaskException;

}
