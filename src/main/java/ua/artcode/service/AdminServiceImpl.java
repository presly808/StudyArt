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
    public boolean deleteByTitle(String title) throws NoSuchTaskException {
        return taskDao.deleteByTitle(title);
    }

    @Override
    public boolean deleteById(ObjectId id) {
        return taskDao.deleteById(id);
    }

    @Override
    public Task addTask(Task codingBatTask) throws AppException {
        return taskDao.addTask(codingBatTask);
    }

    @Override
    public List<Task> getAllTasks() throws AppException {
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
    public Task update(ObjectId id,Task updateTask) throws AppException {
        return taskDao.update(id,updateTask);
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
