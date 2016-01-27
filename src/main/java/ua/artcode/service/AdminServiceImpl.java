package ua.artcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ua.artcode.dao.CodingBatTaskDao;
import ua.artcode.dao.UserDao;
import ua.artcode.exception.AppValidationException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.CodingBatTask;

/**
 * Created by Razer on 10.01.16.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    @Qualifier("codingBatTaskDaoMongoImpl")
    private CodingBatTaskDao codingBatTaskDao;

    @Autowired
    @Qualifier("userDaoMongoImpl")
    private UserDao userDao;


    public AdminServiceImpl() {

    }


    @Override
    public CodingBatTask addTask(CodingBatTask codingBatTask) throws AppValidationException {
        return codingBatTaskDao.addTask(codingBatTask);
    }

    @Override
    public CodingBatTask getTask(String id) throws NoSuchTaskException {
        return codingBatTaskDao.findById(id);
    }
}
