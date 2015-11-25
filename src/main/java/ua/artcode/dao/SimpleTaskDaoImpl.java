package ua.artcode.dao;

import ua.artcode.db.CodingBatTaskContainer;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.CodingBatTask;

import java.util.List;
import java.util.stream.Collectors;

public class SimpleTaskDaoImpl implements SimpleTaskDao {

    private CodingBatTaskContainer taskContainer;

    public SimpleTaskDaoImpl(CodingBatTaskContainer taskContainer) {
        this.taskContainer = taskContainer;
    }

    @Override
    public CodingBatTask create(CodingBatTask task) {
        return taskContainer.addTask(task);
    }

    @Override
    public CodingBatTask addTask(CodingBatTask codingBatTask) {
        return taskContainer.addTask(codingBatTask);
    }

    @Override
    public CodingBatTask findById(String id) throws NoSuchTaskException {
        CodingBatTask codingBatTask = taskContainer.getById(id);

        if (codingBatTask == null) {
            throw new NoSuchTaskException("No task with id " + id);
        }

        return codingBatTask;
    }

    @Override
    public boolean delete(String id) {
        return taskContainer.deleteById(id);
    }

    @Override
    public CodingBatTask update(CodingBatTask task) {
        return null;
    }

    @Override
    public List<CodingBatTask> getAll() throws AppException {
        // todo it may be not good in future with big collection
        return taskContainer.getTasks().stream().collect(Collectors.toList());
    }

    @Override
    public List<CodingBatTask> search() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }
}
