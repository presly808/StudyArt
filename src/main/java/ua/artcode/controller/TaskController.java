package ua.artcode.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.artcode.exception.AppException;
import ua.artcode.exception.AppValidationException;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.model.codingbat.Task;
import ua.artcode.model.codingbat.TaskTestResult;
import ua.artcode.process.TaskRunFacade;
import ua.artcode.service.AdminService;
import ua.artcode.to.ResultTablePart;
import ua.artcode.to.ResultTableUtils;
import ua.artcode.utils.codingbat.CodingBatTaskUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
        return new ModelAndView("task/find-task");
    }

    @RequestMapping(value = "/add-task")
    public String addTask(Model model) {
        model.addAttribute("task", new Task());
        return "task/create-task";
    }

    @RequestMapping(value = "/create-task", method = RequestMethod.POST)
    public ModelAndView createTask(@Valid Task task, BindingResult result, HttpServletRequest req, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("task/create-task");
        if (!result.hasErrors()) {
            try {
                String testData = req.getParameter("data_points");
                task.setMethodSignature(CodingBatTaskUtils.getMethodSignature(task.getTemplate()));
                task.setTaskTestDataContainer(CodingBatTaskUtils.getTestDataContainer(testData));

                adminService.addTask(task);
                mav.setViewName("redirect:/task-menu");
                redirectAttributes.addFlashAttribute("message", "The task has been successfully created.");
            } catch (AppValidationException e) {
                req.setAttribute("message", "Invalid test points");
            } catch (AppException e) {
                req.setAttribute("message", "Task with title: " + task.getTitle() + " already exist.");
            }
        }
        return mav;
    }

    @RequestMapping(value = "/update-task", method = RequestMethod.POST)
    public ModelAndView updateTask(@Valid Task task, BindingResult result, HttpServletRequest req, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/task-menu/edit-task");
        String id="";
        if (result.hasErrors()) {
            return mav;
        }
        try {
            String testData = req.getParameter("data_points");
            task.setMethodSignature(CodingBatTaskUtils.getMethodSignature(task.getTemplate()));
            task.setTaskTestDataContainer(CodingBatTaskUtils.getTestDataContainer(testData));

            id = req.getParameter("id");
            adminService.update(new ObjectId(id), task);

            mav.setViewName("redirect:/task-menu");
            redirectAttributes.addFlashAttribute("message", "The task has been successfully updated.");
        } catch (AppValidationException e) {
            req.setAttribute("message", "Invalid test points");
            redirectAttributes.addFlashAttribute("id",id);
        } catch (AppException e) {
            e.printStackTrace();
        }
        return mav;
    }

    @RequestMapping(value = "/do-task/{name}", method = RequestMethod.GET)
    public ModelAndView doTasks(@PathVariable String name, Model model) throws ServletException, IOException, NoSuchTaskException {
        Task task = adminService.findTaskByTitle(name);
        model.addAttribute("task", task);
        return new ModelAndView("task/do-task");
    }

    @RequestMapping(value = "/do-task", method = RequestMethod.POST)
    public ModelAndView doTasksPost(HttpServletRequest req) throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        try {
            String id = req.getParameter("taskId");
            Task task = adminService.findTaskByTitle(id);
            mav.setViewName("task/do-task");
            mav.addObject(task);
            //req.setAttribute("task", task);
        } catch (NoSuchTaskException e) {
            mav.setViewName("task/find-task");
            mav.addObject("error", e.getMessage());
            //req.setAttribute("error", e.getMessage());
        }
        return mav;
    }

    @RequestMapping(value = "/check-task", method = RequestMethod.POST)
    public ModelAndView checkTask(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        String id = req.getParameter("id");
        TaskTestResult taskTestResult = null;
        List<ResultTablePart> resultTablePartList = null;
        try {
            Task task = adminService.findTaskById(new ObjectId(id));
            taskTestResult = taskRunFacade.runTask(task, req.getParameter("userCode"));
            resultTablePartList = ResultTableUtils.getResultTableList(task, taskTestResult);
        } catch (NoSuchTaskException e) {
            e.printStackTrace();
        }
        req.setAttribute("resultList", resultTablePartList);
        req.setAttribute("status", taskTestResult.getStatus());
        mav.setViewName("task/check-task");
        return mav;
    }

    @RequestMapping(value = "/edit-task", method = RequestMethod.POST)
    public ModelAndView editTask(HttpServletRequest req) throws NoSuchTaskException {
        ModelAndView mav = new ModelAndView("task/edit-task");
        String id = req.getParameter("id");
        Task task = adminService.findTaskById(new ObjectId(id));
        mav.addObject(task);
        return mav;
    }

    @RequestMapping(value = "/size")
    public ModelAndView sizeTasks() {
        ModelAndView mav = new ModelAndView("task/size-tasks");
        mav.addObject("size", adminService.size());
        return mav;
    }

    @RequestMapping(value = "/delete-form")
    public ModelAndView deleteForm() {
        return new ModelAndView("task/delete-task");
    }

    @RequestMapping(value = "/delete")
    public ModelAndView deleteTask(HttpServletRequest reg, RedirectAttributes redirectAttributes) throws NoSuchTaskException {
        ModelAndView mav = new ModelAndView();
        String title = reg.getParameter("title");
        if (adminService.deleteByTitle(title)) {
            //redirectAttributes.addFlashAttribute("message", "The task has been successfully removed.");
            mav.addObject("message", "The task has been successfully removed.");
            mav.setViewName("main/task-menu");
        } else {
            mav.setViewName("delete-task-form");
            mav.addObject("message", "The task has been not removed. There is no task with title: " + title);
        }
        return mav;
    }

    @RequestMapping(value = "/groups")
    public ModelAndView getAllGroup() {
        ModelAndView mav = new ModelAndView("task/task-group-list");
        mav.addObject("groupList", adminService.getGroups());
        return mav;
    }

    @RequestMapping(value = "/show-group/{groupName}")
    public ModelAndView showGroup(@PathVariable String groupName) {
        ModelAndView mav = new ModelAndView("task/list-tasks");
        mav.addObject("taskList", adminService.getGroupTasks(groupName));
        return mav;
    }

}
