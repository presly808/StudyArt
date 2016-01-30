package ua.artcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.artcode.exception.AppException;
import ua.artcode.exception.AppValidationException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.model.codingbat.TaskTestResult;
import ua.artcode.model.common.UserType;
import ua.artcode.process.TaskRunFacade;
import ua.artcode.service.AdminService;
import ua.artcode.service.UserServiceImpl;
import ua.artcode.utils.codingbat.CodingBatTaskUtils;
import ua.artcode.validation.CodingBatTaskValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Created by Razer on 23.01.16.
 */

@Controller
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

    @RequestMapping(value = "/add-task")
    public String addTask() {
        return "create-task";
    }

    @RequestMapping(value = "/create-task")
    public ModelAndView createTask(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView mav = new ModelAndView();
        CodingBatTask task;
        String title = req.getParameter("task_name");
        String groupName = req.getParameter("task_group");
        String description = req.getParameter("task_description");
        String examples = req.getParameter("examples");
        String template = req.getParameter("method_template");
        String testData = req.getParameter("data_points");

        try {
            task = new CodingBatTask("p11111", title, description, examples, template, groupName);

            new CodingBatTaskValidator().validateTemplate(task.getTemplate());

            task.setMethodSignature(CodingBatTaskUtils.getMethodSignature(task.getTemplate()));
            task.setTaskTestDataContainer(CodingBatTaskUtils.getTestDataContainer(testData));

            adminService.addTask(task);
            mav.setViewName("menu");
        } catch (AppValidationException e) {
            req.setAttribute("error", e.getExceptionMessageList());
            mav.setViewName("create-task");
        }
//        catch (Exception e) {
//            req.setAttribute("error", "One of the field is empty");
//            mav.setViewName("create-task");
//        }
        return mav;
    }

    @RequestMapping(value = "/do-task", method = RequestMethod.POST)
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
        UserType userType = req.getParameter("role") != null ? UserType.TEACHER : UserType.USER;
        try {
            userService.register(userName, password, email, userType);
            //TODO without .jsp
            mav.setViewName("redirect:/index.jsp");
            //resp.sendRedirect("/index.jsp");
        } catch (AppException e) {
            req.setAttribute("error", e.getMessage());
            mav.setViewName("registration-form");
        }
        return mav;
    }

    @RequestMapping(value = "/check-task", method = RequestMethod.POST)
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

    @RequestMapping(value = "/size")
    public ModelAndView size() {
        ModelAndView mav = new ModelAndView("size");
        mav.addObject("size", adminService.size());
        return mav;
    }

    @RequestMapping(value = "/task-list")
    public ModelAndView getAllTasks() {
        ModelAndView mav = new ModelAndView("task-list");
        try {
            mav.addObject("taskList", adminService.getAll());
        } catch (AppException e) {
            e.printStackTrace();
        }
        return mav;
    }

    @RequestMapping(value = "/delete-form")
    public ModelAndView deleteForm() {
        return new ModelAndView("delete-form");
    }

    @RequestMapping(value = "/delete")
    public ModelAndView deleteTask(HttpServletRequest reg, HttpServletResponse resp) {
        ModelAndView mav = new ModelAndView();
        if (adminService.delete(reg.getParameter("taskId"))) {
            mav.setViewName("menu");
            return mav;
        } else {
            mav.setViewName("redirect:/delete-form");
            return mav;
        }
    }
}



