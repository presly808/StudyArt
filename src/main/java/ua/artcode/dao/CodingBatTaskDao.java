package ua.artcode.dao;

import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.CodingBatTask;

import java.util.List;

public interface CodingBatTaskDao {

    CodingBatTask findByTitle(String title) throws NoSuchTaskException;

    boolean delete(String title);

    CodingBatTask update(String id, CodingBatTask updateTask) throws AppException;

    List<CodingBatTask> getAll() throws AppException;

    CodingBatTask addTask(CodingBatTask codingBatTask) throws AppException;

    boolean isExist(String title);

    int size();

    List<CodingBatTask> getGroupTasks(String group);

    List<String> getGroups();

}
