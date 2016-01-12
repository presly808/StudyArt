package ua.artcode.service;

import org.springframework.context.ApplicationContext;
import ua.artcode.dao.CodingBatTaskDao;
import ua.artcode.dao.CodingBatTaskDaoMongoImpl;
import ua.artcode.dao.UserDao;
import ua.artcode.dao.UserDaoMongoImpl;
import ua.artcode.exception.AppValidationException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.utils.SpringContext;

/**
 * Created by Razer on 10.01.16.
 */
public class AdminServiceImpl implements AdminService {

    private CodingBatTaskDao codingBatTaskDao;
    private UserDao userDao;
    private ApplicationContext context;

    public AdminServiceImpl() {
        context = SpringContext.getContext();
        codingBatTaskDao = context.getBean(CodingBatTaskDaoMongoImpl.class);
        userDao = context.getBean(UserDaoMongoImpl.class);
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
