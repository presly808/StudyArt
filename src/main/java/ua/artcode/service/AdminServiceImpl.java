package ua.artcode.service;

import com.mongodb.DuplicateKeyException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.artcode.dao.TaskDao;
import ua.artcode.dao.UserDao;
import ua.artcode.exception.AppException;
import ua.artcode.exception.DuplicateDataException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.common.Task;

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
        return taskDao.delete(title);
    }

    @Override
    public boolean deleteById(ObjectId id) throws NoSuchTaskException {
        return taskDao.delete(id);
    }

    @Override
    public void addTask(Task task) throws DuplicateKeyException {
         taskDao.add(task);
    }

    @Override
    public List<Task> getAllTasks() throws AppException {
        return taskDao.getAll();
    }

    @Override
    public Task findTaskByTitle(String title) throws NoSuchTaskException {
        return taskDao.find(title);
    }

    @Override
    public Task findTaskById(ObjectId id) throws NoSuchTaskException{
        return taskDao.find(id);
    }

    @Override
    public int size() {
        return taskDao.size();
    }

    @Override
    public Task update(ObjectId id,Task updateTask) throws NoSuchTaskException , DuplicateDataException{
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
