package ua.artcode.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerAdapter;

import static org.springframework.test.web.ModelAndViewAssert.assertViewName;

/**
 * Created by Maxim on 24.04.2016.
 */
@Ignore
public class MainControllerTest {

    private MainController mainController;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private HandlerAdapter handlerAdapter;

    @Before
    public void setUp() {
        //Making mock Request and Response
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();

        //Creating HandlerAdapter to handle our request
        //in this case this is RequestMappingHandlerAdapter

//        handlerAdapter = new RequestMappingHandlerAdapter();
        handlerAdapter = new AnnotationMethodHandlerAdapter();

        mainController = new MainController();
    }

    @Test
    public void goTaskMenu() throws Exception {
        //Creating Mock request
        //no response is expected
        request.setRequestURI("/task-menu");
        request.setMethod("POST");
        response = null;

        //Pretending our servlet container handled our request and made something to the ModelAndView
        final ModelAndView mav = handlerAdapter.handle(request, response, mainController);

        assertViewName(mav, "main/task-menu");
    }
}
