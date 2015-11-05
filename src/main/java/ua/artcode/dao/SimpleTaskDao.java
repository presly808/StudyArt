package ua.artcode.dao;

import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.CodingBatTask;

import java.util.List;

// CRUD
public interface SimpleTaskDao {

    CodingBatTask create(CodingBatTask task);

    CodingBatTask findById(String id) throws NoSuchTaskException;

    boolean delete(String id);

    CodingBatTask update(CodingBatTask task);

    List<CodingBatTask> getAll() throws AppException;

    List<CodingBatTask> search();
}
