package ua.artcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;
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
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

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
    public String addTask(Model model) {
        model.addAttribute("codingBatTask", new CodingBatTask());
        return "create-task-form";
    }

    @RequestMapping(value = "/create-task", method = RequestMethod.POST)
    public String createTask(@Valid CodingBatTask codingBatTask, BindingResult result, Model model, HttpServletRequest req, RedirectAttributes redirectAtri) {
        if (result.hasErrors()) {
            return "create-task-form";
        }
        String testData = req.getParameter("data_points");

        try {
            codingBatTask.setMethodSignature(CodingBatTaskUtils.getMethodSignature(codingBatTask.getTemplate()));
            codingBatTask.setTaskTestDataContainer(CodingBatTaskUtils.getTestDataContainer(testData));

            adminService.addTask(codingBatTask);
            redirectAtri.addFlashAttribute("message", "The task has been successfully created.");
            return "redirect:/task-menu";
        } catch (AppValidationException e) {
            req.setAttribute("message", "Invalid test points");
        } catch (AppException e) {
            req.setAttribute("message", "Task with title: " + codingBatTask.getTitle() + " already exist.");
        }
        return "create-task-form";
    }

//     working. Don't delete!!! PRG pattern
//    @RequestMapping(value = "/redirect-menu", method = RequestMethod.GET)
//    public String goTaskMenu(HttpServletRequest request) {
//        Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
//        if (map != null) {
//            request.setAttribute("message", map.get("message"));
//        }
//        return "task-menu";
//    }

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
        return new ModelAndView("delete-task-form");
    }

    @RequestMapping(value = "/delete")
    public ModelAndView deleteTask(HttpServletRequest reg, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
        String title = reg.getParameter("title");
        if (adminService.delete(title)) {
            //redirectAttributes.addFlashAttribute("message", "The task has been successfully removed.");
            mav.addObject("message", "The task has been successfully removed.");
            mav.setViewName("task-menu");
        } else {
            mav.setViewName("delete-task-form");
            mav.addObject("message", "The task has been not removed. There is no task with title: " + title);
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
