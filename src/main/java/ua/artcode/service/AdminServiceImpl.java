package ua.artcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.artcode.dao.CodingBatTaskDao;
import ua.artcode.dao.UserDao;
import ua.artcode.exception.AppException;
import ua.artcode.exception.AppValidationException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.CodingBatTask;

import java.util.List;

/**
 * Created by Razer on 10.01.16.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    @Qualifier("сodingBatTaskMongoImpl")
    private CodingBatTaskDao codingBatTaskDao;


    @Autowired
    @Qualifier("userDaoMongoMongoImpl")
    private UserDao userDao;

    public AdminServiceImpl() {

    }


    @Override
    public boolean delete(String id) {
        return codingBatTaskDao.delete(id);
    }

    @Override
    public CodingBatTask addTask(CodingBatTask codingBatTask) throws AppValidationException {
        return codingBatTaskDao.addTask(codingBatTask);
    }

    @Override
    public List<CodingBatTask> getAll() throws AppException {
        return codingBatTaskDao.getAll();
    }

    @Override
    public CodingBatTask getTask(String id) throws NoSuchTaskException {
        return codingBatTaskDao.findById(id);
    }

    @Override
    public int size() {
        return codingBatTaskDao.size();
    }

    @Override
    public CodingBatTask update(String id, CodingBatTask updateTask) {
        return null;
    }

    @Override
    public List<CodingBatTask> getGroupTasks(String group) {
        return codingBatTaskDao.getGroupTasks(group);
    }

    //teacher add group
    //teacher add task to group
    //teacher show statick

    @Override
    public List<String> getGroup() {
        return codingBatTaskDao.getGroups();
    }
}
