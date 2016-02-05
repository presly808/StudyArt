package ua.artcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    @RequestMapping(value = "/menu")
    public ModelAndView menu() {
        return new ModelAndView("menu");
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {
        ModelAndView model = new ModelAndView();
        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
        }

        model.setViewName("403");
        return model;

    }

    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam(value = "error", required = false) String error, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        if (error != null) {
            mav.addObject("error", "Invalid username or password!");
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

    @RequestMapping(value = "/do-task/{taskId}", method = RequestMethod.GET)
    public ModelAndView doTasks(@PathVariable String taskId, Model model) throws ServletException, IOException, NoSuchTaskException {
        //ModelAndView mav = new ModelAndView();
        CodingBatTask task = adminService.getTask(taskId);
        model.addAttribute("task", task);
//        ModelAndView mav = new ModelAndView();
//        try {
//            CodingBatTask task = adminService.getTask(taskId);
//            req.setAttribute("task", task);
//            mav.setViewName("do-task");
//        } catch (NoSuchTaskException e) {
//            req.setAttribute("error", e.getMessage());
//            mav.setViewName("do-task");
//        }
//        return mav;
        return new ModelAndView("do-task");
    }

    @RequestMapping(value = "/registration")
    public ModelAndView registration(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        UserType userType = req.getParameter("role") != null ? UserType.ROLE_TEACHER : UserType.ROLE_USER;
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

    @RequestMapping(value = "/groups")
    public ModelAndView getAllGroup(HttpServletRequest reg, HttpServletResponse resp) {
        ModelAndView mav = new ModelAndView("group-list");
        mav.addObject("groups", adminService.getGroup());
        return mav;
    }

    @RequestMapping(value = "/show-group/{groupName}")
    public ModelAndView showGroup(@PathVariable String groupName, HttpServletRequest reg, HttpServletResponse resp) {
        ModelAndView mav = new ModelAndView("task-list");
        mav.addObject("taskList", adminService.getGroupTasks(groupName));
        return mav;
    }

}



