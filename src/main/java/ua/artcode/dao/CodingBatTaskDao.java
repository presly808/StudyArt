package ua.artcode.dao;

import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.CodingBatTask;

import java.util.List;

// CRUD
public interface CodingBatTaskDao {

    CodingBatTask findById(String id) throws NoSuchTaskException;

    boolean delete(String id);

    CodingBatTask update(CodingBatTask task);

    List<CodingBatTask> getAll() throws AppException;

    CodingBatTask addTask(CodingBatTask codingBatTask);

    int size();
}
