package ua.artcode.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import ua.artcode.dao.SimpleUserDao;
import ua.artcode.dao.SimpleUserDaoImpl;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchUserException;
import ua.artcode.exception.UserAccountExistException;
import ua.artcode.exception.UserAuthenticationFailException;
import ua.artcode.model.UserAccount;
import ua.artcode.validation.UserAccountValidator;
import java.util.HashSet;
import java.util.Set;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//TODO finish test
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private SimpleUserDao mockedUserDao;
    private SimpleUserServiceImpl userService;
    private UserAccount USER1 = new UserAccount("User1", "password1", "user1@gmail.com");
    private UserAccount USER2 = new UserAccount("User2", "password2", "user2@gmail.com");
    private UserAccount USER3 = new UserAccount("User3", "P@ssword3", "anonimus.anonim@gmail.com");
    private UserAccount USER4 = new UserAccount("User4", "password4", "user4@gmail.com");
    private Set<String> allUsers;

    @Before
    public void setUp() throws UserAuthenticationFailException, NoSuchUserException, UserAccountExistException {
        setUpMockedDao();
        // create mock for interface
        userService = new SimpleUserServiceImpl(mockedUserDao, new UserAccountValidator());
    }

    private void setUpMockedDao() throws UserAuthenticationFailException, NoSuchUserException, UserAccountExistException {
        mockedUserDao = mock(SimpleUserDaoImpl.class);

        when(mockedUserDao.findByUserName("User1")).thenReturn(USER1);
        when(mockedUserDao.findByUserName("User2")).thenReturn(USER2);
        when(mockedUserDao.findByUserName("User3")).thenReturn(USER3);
        when(mockedUserDao.findByUserName("User4")).thenReturn(USER4);
        when(mockedUserDao.create(anyObject())).thenReturn(USER3);


        allUsers = new HashSet<String>();
        allUsers.add(USER1.getUsername());
        allUsers.add(USER2.getUsername());
        allUsers.add(USER3.getUsername());
        allUsers.add(USER4.getUsername());

        when(mockedUserDao.getAllUserNames()).thenReturn(allUsers);


    }


    @Test
    public void authenticatePositiveTest(){

        // call methods
        try {
            assertEquals(USER1, userService.authenticate(USER1.getUsername(), USER1.getPassword()));
        } catch (AppException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void registrateTest(){
        UserAccount user3;

        // call methods
        try {
            user3 = userService.register(USER3.getUsername(), USER3.getPassword(), USER3.getEmail(), null);
            assertEquals(USER3, user3);
        } catch (AppException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllUserTest(){
        Set<String> users;
            users = userService.getAllUser();
            assertEquals(allUsers, users);

        }

    @Test
    public void getUserInfoTest(){
        Set<String> users;
        users = userService.getAllUser();
        assertEquals(allUsers, users);

    }
}
