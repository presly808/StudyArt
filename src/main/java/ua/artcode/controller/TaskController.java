package ua.artcode.controller;

import com.mongodb.DuplicateKeyException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.artcode.exception.*;
import ua.artcode.model.common.Task;
import ua.artcode.model.taskComponent.TaskTestResult;
import ua.artcode.model.common.User;
import ua.artcode.process.TaskRunFacade;
import ua.artcode.service.AdminService;
import ua.artcode.service.UserService;
import ua.artcode.to.ResultTablePart;
import ua.artcode.to.ResultTableUtils;
import ua.artcode.utils.codingbat.CodingBatTaskUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
    private UserService userService;

    @Autowired
    private TaskRunFacade taskRunFacade;

    @RequestMapping(value = "/find-task")
    public String findTask() {
        return "task/find-task";
    }

    @RequestMapping(value = "/create-task")
    public ModelAndView addTask() {
        ModelAndView mav = new ModelAndView("task/create-task");
        mav.addObject("title", "Create task");
        mav.addObject("task", new Task());
        return mav;
    }

    @RequestMapping(value = "/add-task", method = RequestMethod.POST)
    public ModelAndView createTask(@Valid Task task, BindingResult result, HttpServletRequest req, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("task/create-task");
        String testData = req.getParameter("data_points");
        String solution = req.getParameter("code_solution");
        String operationType = req.getParameter("title");
        if (!result.hasErrors()) {
            try {
                task.setMethodSignature(CodingBatTaskUtils.getMethodSignature(task.getTemplate()));
                task.setTaskTestDataContainer(CodingBatTaskUtils.getTestDataContainer(testData));

                TaskTestResult testResult = taskRunFacade.runTask(task, solution);
                if (testResult.getPassedAll()) {
                    if (operationType.equals("Create task")) {
                        adminService.addTask(task);
                        mav.setViewName("redirect:/task-menu");
                        redirectAttributes.addFlashAttribute("message", "The task has been successfully created.");
                    } else {
                        adminService.update(task.getId(), task);
                        mav.setViewName("redirect:/task-menu");
                        redirectAttributes.addFlashAttribute("message", "The task has been successfully updated.");
                    }

                } else {
                    mav.addObject("message", "Wrong solution. The task is not verified!");
                }

            } catch (DuplicateKeyException e) {
                mav.addObject("message", "Task with title: " + task.getTitle() + " already exist!");
            } catch (AppValidationException e) {
                mav.addObject("message", "Invalid test points!");
            } catch (DuplicateDataException e) {
                mav.addObject("message", e.getMessage());
            } catch (NoSuchTaskException e) {
                req.setAttribute("message", e.getMessage());
            }
        }
        mav.addObject("title", operationType);
        mav.addObject("testData", testData);
        mav.addObject("solution", solution);
        return mav;
    }

    @RequestMapping(value = "/edit-task", method = RequestMethod.POST)
    public ModelAndView editTask(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("task/create-task");
        mav.addObject("title", "Edit task");
        try {
            String id = req.getParameter("id");
            Task task = adminService.findTaskById(new ObjectId(id));
            mav.addObject("task",task);
            mav.addObject("testData", task.getTaskTestDataContainer().toString());
            // need Solution field!!!
        } catch (NoSuchTaskException e) {
            e.printStackTrace();
        }
        return mav;
    }

//    @RequestMapping(value = "/edit-task", method = RequestMethod.POST)
//    public ModelAndView editTask(HttpServletRequest req) {
//        ModelAndView mav = new ModelAndView("task/edit-task");
//        try {
//            String id = req.getParameter("id");
//            Task task = adminService.findTaskById(new ObjectId(id));
//            mav.addObject(task);
//            //TODO
//        } catch (NoSuchTaskException e) {
//            e.printStackTrace();
//        }
//        return mav;
//    }

    @RequestMapping(value = "/update-task", method = RequestMethod.POST)
    public ModelAndView updateTask(@Valid Task task, BindingResult result, HttpServletRequest req, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("task/edit-task");
        if (!result.hasErrors()) {
            String id = req.getParameter("id");
            try {
                String testData = req.getParameter("data_points");
                task.setMethodSignature(CodingBatTaskUtils.getMethodSignature(task.getTemplate()));
                task.setTaskTestDataContainer(CodingBatTaskUtils.getTestDataContainer(testData));
                adminService.update(new ObjectId(id), task);

                mav.setViewName("redirect:/task-menu");
                redirectAttributes.addFlashAttribute("message", "The task has been successfully updated.");
            } catch (AppValidationException e) {
                req.setAttribute("message", "Invalid test points");
                redirectAttributes.addFlashAttribute("id", id);
            } catch (NoSuchTaskException e) {
                req.setAttribute("message", e.getMessage());
            } catch (DuplicateDataException e) {
                req.setAttribute("message", e.getMessage());
            }
        }
        return mav;
    }

    @RequestMapping(value = "/do-task/{title}", method = RequestMethod.GET)
    public ModelAndView doTasks(@PathVariable String title) {
        ModelAndView mav = new ModelAndView("task/do-task");
        return prepareTask(title, mav);
    }

    private ModelAndView prepareTask(String title, ModelAndView mav) {
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String name = userDetails.getUsername();
            User user = userService.findUser(name);
            String template;

            Task task = adminService.findTaskByTitle(title);

            TaskTestResult taskTestResult = user.getSolvedTask(task.getId());
            if (taskTestResult != null) {
                String userCode = taskTestResult.getUserCode();
                template = userCode;
            } else {
                template = task.getTemplate();
            }

            mav.addObject("template", template);
            mav.addObject(task);
            //model.addAttribute(task);

        } catch (NoSuchUserException e) {
            mav.addObject("message", e.getMessage());
            mav.setViewName("main/task-menu");
        } catch (NoSuchTaskException e) {
            mav.addObject("message", e.getMessage());
            mav.setViewName("main/task-menu");
        }
        return mav;
    }

    @RequestMapping(value = "/show-solution/{title}", method = RequestMethod.GET)
    public ModelAndView showSolution(@PathVariable String title) {
        ModelAndView mav = new ModelAndView("task/show-solution");
        return prepareTask(title, mav);
    }

    @RequestMapping(value = "/do-task", method = RequestMethod.POST)
    public ModelAndView doTasksPost(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("task/do-task");
        String title = req.getParameter("title");
        return prepareTask(title, mav);
    }

    @RequestMapping(value = "/check-task", method = RequestMethod.POST)
    public ModelAndView checkTask(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("task/check-task");
        String id = req.getParameter("id");
        ObjectId taskId = new ObjectId(id);

        try {
            Task task = adminService.findTaskById(taskId);
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String name = userDetails.getUsername();
            User user = userService.findUser(name);

            String userCode = req.getParameter("userCode");
            List<ResultTablePart> resultTablePartList = ResultTableUtils.createTable(task);
            TaskTestResult newTaskTestResult = taskRunFacade.runTask(task, userCode);

            // When we got compilation error, userCode = null
            if (newTaskTestResult.getUserCode() == null) {
                mav.setViewName("task/do-task");
                mav.addObject(task);
                mav.addObject("template", userCode);
                mav.addObject("message", newTaskTestResult.getStatus());
                return mav;
            }

            writeResult(user, newTaskTestResult, taskId);

            String email = user.getEmail();
            userService.update(email, user);
            resultTablePartList = ResultTableUtils.getResultTableList(task, newTaskTestResult, resultTablePartList);

            req.setAttribute("resultList", resultTablePartList);
            req.setAttribute("status", newTaskTestResult.getStatus());
        } catch (NoSuchTaskException e) {
            e.printStackTrace();
        } catch (NoSuchUserException e) {
            e.printStackTrace();
        } catch (DuplicateDataException e) {
            e.printStackTrace();
        }
        return mav;
    }

    private void writeResult(User user, TaskTestResult newTaskTestResult, ObjectId taskId) {
        try {
            TaskTestResult oldTaskTestResult = user.getSolvedTask(taskId);

            if (oldTaskTestResult != null) {
                if (oldTaskTestResult.getPassedAll() == false) {
                    user.addSolvedTask(taskId, newTaskTestResult);
                } else if (newTaskTestResult.getPassedAll()) {
                    user.addSolvedTask(taskId, newTaskTestResult);
                }
            } else {
                user.addSolvedTask(taskId, newTaskTestResult);
            }
            String email = user.getEmail();

            userService.update(email, user);
            //todo
        } catch (AppException e) {
            //mav.addObject("message", "Invalid test points!");
        }
    }

    @RequestMapping(value = "/size")
    public ModelAndView sizeTasks() {
        ModelAndView mav = new ModelAndView("task/size-tasks");
        mav.addObject("size", adminService.size());
        return mav;
    }

    @RequestMapping(value = "/delete-form")
    public String deleteForm() {
        return "task/delete-task";
    }

    @RequestMapping(value = "/delete-task")
    public ModelAndView deleteTask(HttpServletRequest reg, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
        String title = reg.getParameter("title");
        try {
            adminService.deleteByTitle(title);
            redirectAttributes.addFlashAttribute("message", "The task has been successfully removed.");
            mav.setViewName("redirect:/task-menu");
        } catch (NoSuchTaskException e) {
            mav.addObject("message", e.getMessage());
            mav.setViewName("task/delete-task");
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
