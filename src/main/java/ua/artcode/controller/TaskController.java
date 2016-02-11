package ua.artcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.artcode.exception.AppValidationException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.model.codingbat.TaskTestResult;
import ua.artcode.process.TaskRunFacade;
import ua.artcode.service.AdminService;
import ua.artcode.service.UserServiceImpl;
import ua.artcode.to.ResultTablePart;
import ua.artcode.to.ResultTableUtils;
import ua.artcode.utils.codingbat.CodingBatTaskUtils;
import ua.artcode.validation.CodingBatTaskValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Razer on 07.02.16.
 */
@Controller
public class TaskController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private TaskRunFacade taskRunFacade;

    @RequestMapping(value = "/tasks-menu/find-task")
    public ModelAndView findTask() {
        return new ModelAndView("find-task");
    }

    @RequestMapping(value = "/tasks-menu/add-task")
    public String addTask() {
        return "create-task";
    }

    @RequestMapping(value = "/tasks-menu/create-task", method = RequestMethod.POST)
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

    @RequestMapping(value = "/tasks-menu/do-task/{taskId}", method = RequestMethod.GET)
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

    @RequestMapping(value = "/tasks-menu/do-task", method = RequestMethod.POST)
    public ModelAndView doTasksPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        try {
            CodingBatTask task = adminService.getTask(req.getParameter("taskId"));
            mav.setViewName("do-task");
            req.setAttribute("task", task);
        } catch (NoSuchTaskException e) {
            mav.setViewName("find-task");
            req.setAttribute("error", e.getMessage());
        }
        return mav;
    }

    @RequestMapping(value = "/tasks-menu/check-task", method = RequestMethod.POST)
    public ModelAndView checkTask(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView mav = new ModelAndView();
        String id = req.getParameter("id");
        TaskTestResult taskTestResult = null;
        List<ResultTablePart> resultTablePartList = null;
        try {
            CodingBatTask task = adminService.getTask(id);
            taskTestResult = taskRunFacade.runTask(task, req.getParameter("userCode"));
            resultTablePartList = ResultTableUtils.getResultTableList(task,taskTestResult);
        } catch (NoSuchTaskException e) {
            e.printStackTrace();
        }
        req.setAttribute("resultList" ,resultTablePartList);
        req.setAttribute("status", taskTestResult.getStatus());
        mav.setViewName("check-task");
        return mav;
    }

    @RequestMapping(value = "/tasks-menu/size")
    public ModelAndView size() {
        ModelAndView mav = new ModelAndView("size");
        mav.addObject("size", adminService.size());
        return mav;
    }

    @RequestMapping(value = "/tasks-menu/delete-form")
    public ModelAndView deleteForm() {
        return new ModelAndView("delete-form");
    }

    @RequestMapping(value = "/tasks-menu/delete")
    public ModelAndView deleteTask(HttpServletRequest reg, HttpServletResponse resp) {
        ModelAndView mav = new ModelAndView();
        String taskId = reg.getParameter("taskId");
        if (adminService.delete(taskId)) {
            mav.setViewName("menu");
            mav.addObject("message", "Task successfully removed.");
            return mav;
        } else {
            mav.setViewName("delete-form");
            mav.addObject("error", "The task is not removed. There is no task with Id: " + taskId);
            return mav;
        }
    }

    @RequestMapping(value = "/tasks-menu/groups")
    public ModelAndView getAllGroup(HttpServletRequest reg, HttpServletResponse resp) {
        ModelAndView mav = new ModelAndView("group-list");
        mav.addObject("groupList", adminService.getGroup());
        return mav;
    }

    @RequestMapping(value = "/tasks-menu/show-group/{groupName}")
    public ModelAndView showGroup(@PathVariable String groupName, HttpServletRequest reg, HttpServletResponse resp) {
        ModelAndView mav = new ModelAndView("task-list");
        mav.addObject("taskList", adminService.getGroupTasks(groupName));
        return mav;
    }

    @RequestMapping(value = "/tasks-menu")
    public ModelAndView tasksMenu() {
        return new ModelAndView("tasks-menu");
    }
}
