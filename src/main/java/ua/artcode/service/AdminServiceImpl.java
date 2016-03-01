package ua.artcode.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.artcode.dao.TaskDao;
import ua.artcode.dao.UserDao;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.Task;

import java.util.List;

/**
 * Created by Razer on 10.01.16.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    @Qualifier("сodingBatTaskMongoImpl")
    private TaskDao taskDao;


    @Autowired
    @Qualifier("userDaoMongoMongoImpl")
    private UserDao userDao;

    public AdminServiceImpl() {

    }


    @Override
    public boolean delete(String title) {
        return taskDao.delete(title);
    }

    @Override
    public Task addTask(Task codingBatTask) throws AppException {
        return taskDao.addTask(codingBatTask);
    }

    @Override
    public List<Task> getAll() throws AppException {
        return taskDao.getAll();
    }

    @Override
    public Task findTaskByTitle(String title) throws NoSuchTaskException {
        return taskDao.findByTitle(title);
    }

    @Override
    public Task findTaskById(ObjectId id) throws NoSuchTaskException{
        return taskDao.findById(id);
    }

    @Override
    public int size() {
        return taskDao.size();
    }

    //TODO update
    @Override
    public Task update(String id, Task updateTask) {
        return null;
    }

    @Override
    public List<Task> getGroupTasks(String group) {
        return taskDao.getGroupTasks(group);
    }

    @Override
    public List<String> getGroups() {
        return taskDao.getGroups();
    }
}
