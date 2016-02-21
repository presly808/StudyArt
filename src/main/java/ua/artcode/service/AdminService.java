package ua.artcode.service;

import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.CodingBatTask;

import java.util.List;

/**
 * Created by Razer on 10.01.16.
 */
public interface AdminService {

    CodingBatTask addTask(CodingBatTask codingBatTask) throws AppException;

    CodingBatTask getTask(String title) throws NoSuchTaskException;

    int size();

    List<CodingBatTask> getAll() throws AppException;

    boolean delete(String title);

    CodingBatTask update(String id, CodingBatTask updateTask);

    List<CodingBatTask> getGroupTasks(String group);

    List<String> getGroups();
}
