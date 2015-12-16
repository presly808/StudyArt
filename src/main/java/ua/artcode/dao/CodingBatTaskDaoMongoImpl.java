package ua.artcode.dao;

import ua.artcode.db.DataBaseManager;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.CodingBatTask;

import java.util.List;

public class CodingBatTaskDaoMongoImpl implements CodingBatTaskDao {

    private DataBaseManager dataBaseManager;

    public CodingBatTaskDaoMongoImpl(DataBaseManager dataBaseManager) {
        this.dataBaseManager = dataBaseManager;
    }

    @Override
    public CodingBatTask create(CodingBatTask task) {
        dataBaseManager.addTask(task);
        return task;
    }

    @Override
    public CodingBatTask findById(String id) throws NoSuchTaskException {
        dataBaseManager.findById(id);
        return null;
    }

    @Override
    public boolean delete(String id) {
        return dataBaseManager.deleteTaskById(id);
    }

    @Override
    public int size() {
        return dataBaseManager.size();
    }

    @Override
    public CodingBatTask update(CodingBatTask task) {
        return null;
    }

    @Override
    public List<CodingBatTask> getAll() throws AppException {
        return null;
    }

    @Override
    public CodingBatTask addTask(CodingBatTask codingBatTask) {
        dataBaseManager.addTask(codingBatTask);
        return codingBatTask;
    }


}
