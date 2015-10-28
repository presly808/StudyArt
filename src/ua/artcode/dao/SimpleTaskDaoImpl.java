package ua.artcode.dao;

import ua.artcode.db.CodingBatTaskContainer;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.CodingBatTask;
import ua.artcode.to.TaskFilter;

import java.util.Collection;
import java.util.List;

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
    public CodingBatTask findById(String id) throws NoSuchTaskException {
        CodingBatTask codingBatTask = taskContainer.getById(id);

        if(codingBatTask == null){
            throw new NoSuchTaskException("No task with id " + id);
        }

        return codingBatTask;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public CodingBatTask update(CodingBatTask task) {
        return null;
    }

    @Override
    public List<CodingBatTask> getAll() throws AppException {
        Collection<CodingBatTask> collection = taskContainer.getTasks();

        if(collection instanceof List)
            return (List<CodingBatTask>) collection;
        else
            throw new AppException("can not cast Collection to List");

    }

    @Override
    public List<CodingBatTask> search(TaskFilter filter) {
        return null;
    }
}
