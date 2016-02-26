package ua.artcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.artcode.exception.AppException;
import ua.artcode.exception.AppValidationException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.model.codingbat.TaskTestResult;
import ua.artcode.process.TaskRunFacade;
import ua.artcode.service.AdminService;
import ua.artcode.to.ResultTablePart;
import ua.artcode.to.ResultTableUtils;
import ua.artcode.utils.codingbat.CodingBatTaskUtils;
import ua.artcode.validation.CodingBatTaskValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by Razer on 07.02.16.
 */
@Controller
@RequestMapping(value = "/task-menu")
public class TaskController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private TaskRunFacade taskRunFacade;

    @RequestMapping(value = "/find-task")
    public ModelAndView findTask() {
        return new ModelAndView("find-task");
    }

    @RequestMapping(value = "/add-task")
    public String addTask() {
        return "create-task-form";
    }

    @RequestMapping(value = "/create-task", method = RequestMethod.POST)
    public ModelAndView createTask(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        CodingBatTask task;
        String title = req.getParameter("task_name");
        String groupName = req.getParameter("task_group");
        String description = req.getParameter("task_description");
        String examples = req.getParameter("examples");
        String template = req.getParameter("method_template");
        String testData = req.getParameter("data_points");

        try {
            task = new CodingBatTask(title, description, examples, template, groupName);

            new CodingBatTaskValidator().validateTemplate(task.getTemplate());

            task.setMethodSignature(CodingBatTaskUtils.getMethodSignature(task.getTemplate()));
            task.setTaskTestDataContainer(CodingBatTaskUtils.getTestDataContainer(testData));

            adminService.addTask(task);
            mav.setViewName("task-menu");
            mav.addObject("message", "The task has been successfully created.");
        } catch (AppValidationException e) {
            req.setAttribute("message", e.getExceptionMessageList());
            mav.setViewName("create-task");
        } catch (AppException e) {
            req.setAttribute("message", e.getExceptionMessageList());
            mav.setViewName("create-task");
        }
        return mav;
    }

    @RequestMapping(value = "/do-task/{name}", method = RequestMethod.GET)
    public ModelAndView doTasks(@PathVariable String name, Model model) throws ServletException, IOException, NoSuchTaskException {
        CodingBatTask task = adminService.getTask(name);
        model.addAttribute("task", task);
        return new ModelAndView("do-task");
    }

    @RequestMapping(value = "/do-task", method = RequestMethod.POST)
    public ModelAndView doTasksPost(HttpServletRequest req) throws ServletException, IOException {
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

    @RequestMapping(value = "/check-task", method = RequestMethod.POST)
    public ModelAndView checkTask(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        String title = req.getParameter("title");
        TaskTestResult taskTestResult = null;
        List<ResultTablePart> resultTablePartList = null;
        try {
            CodingBatTask task = adminService.getTask(title);
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

    @RequestMapping(value = "/size")
    public ModelAndView sizeTasks() {
        ModelAndView mav = new ModelAndView("size");
        mav.addObject("size", adminService.size());
        return mav;
    }

    @RequestMapping(value = "/delete-form")
    public ModelAndView deleteForm() {
        return new ModelAndView("delete-task");
    }

    @RequestMapping(value = "/delete")
    public ModelAndView deleteTask(HttpServletRequest reg, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
        String taskId = reg.getParameter("taskId");
        if (adminService.delete(taskId)) {
            redirectAttributes.addFlashAttribute("message", "The task has been successfully removed.");
            mav.setViewName("redirect:/menu");
            return mav;
        } else {
            mav.setViewName("/delete-form");
            mav.addObject("message", "The task has been not removed. There is no task with Id: " + taskId);
        }
        return mav;
    }

    @RequestMapping(value = "/groups")
    public ModelAndView getAllGroup() {
        ModelAndView mav = new ModelAndView("group-list");
        mav.addObject("groupList", adminService.getGroups());
        return mav;
    }

    @RequestMapping(value = "/show-group/{groupName}")
    public ModelAndView showGroup(@PathVariable String groupName) {
        ModelAndView mav = new ModelAndView("list-tasks");
        mav.addObject("taskList", adminService.getGroupTasks(groupName));
        return mav;
    }

}
