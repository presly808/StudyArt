package ua.artcode.controller;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import ua.artcode.model.common.User;
import ua.artcode.model.common.UserType;
import ua.artcode.service.UserService;
import ua.artcode.service.UserServiceImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Maxim on 26.04.2016.
 */
@Ignore
public class UserRegistrationTest {

    private static UserService userService;
    private static MainController mainController;
    private RedirectAttributes redirectAttributes;

    @BeforeClass
    public static void init() {
        userService = new UserServiceImpl();
        mainController = new MainController();
    }

    @Before
    public void setUp() {
        redirectAttributes = new RedirectAttributesModelMap();
    }



    @Test
    public void testAddUserFromForm_WithoutErrors() {
        User user = new User("Franky", "123456aw", "frank@mail.com", UserType.ROLE_STUDENT);

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(false);

        String result = mainController.registration(user, bindingResult, redirectAttributes).getViewName();
        assertEquals("redirect:/login", result);
    }

    @Test
    public void testAddUserFromForm_WithErrors() {
        User user = new User();

        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.hasErrors()).thenReturn(true);

        String result = mainController.registration(user, bindingResult, redirectAttributes).getViewName();
        assertEquals("main/registration", result);
    }
}
