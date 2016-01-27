package ua.artcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.model.codingbat.TaskTestResult;
import ua.artcode.process.TaskRunFacade;
import ua.artcode.service.AdminService;
import ua.artcode.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by Razer on 23.01.16.
 */

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private TaskRunFacade taskRunFacade;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try {
            if (userService.authenticate(email, password)) {
                mav.setViewName("menu");
            }
        } catch (AppException e) {
            request.setAttribute("error", e.getMessage());
            mav.setViewName("redirect:/index.jsp");
        }
        return mav;
    }

    @RequestMapping(value = "/find-task")
    public ModelAndView findTask() {
        return new ModelAndView("find-task");
    }

    @RequestMapping(value = "/do-task", method = RequestMethod.GET)
    public ModelAndView doTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String taskId = req.getParameter("taskId");
        try {
            CodingBatTask task = adminService.getTask(taskId);
            req.setAttribute("task", task);
            mav.setViewName("do-task");
        } catch (NoSuchTaskException e) {
            req.setAttribute("error", e.getMessage());
            mav.setViewName("do-task");
        }
        return mav;
    }

    @RequestMapping(value = "/registration")
    public ModelAndView registration(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        try {
            userService.register(userName, password, email);
            mav.setViewName("redirect:/index.jsp");
            //resp.sendRedirect("/index.jsp");
        } catch (AppException e) {
            req.setAttribute("error", e.getMessage());
            mav.setViewName("registration-form");
            //req.getRequestDispatcher("/pages/regist-form.jsp").forward(req, resp);
        }
        return mav;
    }

    @RequestMapping(value = "/check-task")
    public ModelAndView checkTask(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView mav = new ModelAndView();
        String id = req.getParameter("id");
        TaskTestResult taskTestResult = null;
        try {
            CodingBatTask task = adminService.getTask(id);
            taskTestResult = taskRunFacade.runTask(task, req.getParameter("userCode"));
        } catch (NoSuchTaskException e) {
            e.printStackTrace();
        }
        req.setAttribute("result", taskTestResult);
        mav.setViewName("check-task");
        return mav;
    }

    @RequestMapping(value = "/registration-form")
    public ModelAndView registrationForm() {
        return new ModelAndView("registration-form");
    }
}


