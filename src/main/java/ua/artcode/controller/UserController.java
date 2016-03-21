package ua.artcode.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.exception.NoSuchUserException;
import ua.artcode.model.codingbat.Task;
import ua.artcode.model.codingbat.TaskTestResult;
import ua.artcode.model.common.User;
import ua.artcode.service.AdminService;
import ua.artcode.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Razer on 21.02.16.
 */
@Controller
@RequestMapping(value = "/user-menu")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/show-users")
    public ModelAndView showUsers() {
        //TODO  user role,message if no passed tasks
        ModelAndView mav = new ModelAndView("user/list-users");
        mav.addObject("users", userService.getAllUsers());
        return mav;
    }

    @RequestMapping(value = "/show-user/{name}")
    public ModelAndView showUser(@PathVariable String name) {
        ModelAndView mav = new ModelAndView();
        try {
            User user = userService.findUser(name);
            String role = String.valueOf(user.getUserType());
            if (role.equals("ROLE_TEACHER")) {
                role = "Teacher";
            } else if (role.equals("ROLE_STUDENT")) {
                role = "Student";
            } else {
                role = "Admin";
            }
            mav.addObject("user", user);
            Map<String, String> result = new HashMap<>();
            Map<ObjectId, TaskTestResult> statistic = user.getSolvedTaskContainer();
            for (Map.Entry<ObjectId, TaskTestResult> entry : statistic.entrySet()) {
                ObjectId key = entry.getKey();
                Task task = adminService.findTaskById(key);
                TaskTestResult value = entry.getValue();
                if (value.getPassedAll()) {
                    result.put(task.getTitle(), "passed");
                }
            }
            mav.addObject("result", result);
            mav.addObject("role", role);
            mav.setViewName("user/show-user");
        } catch (NoSuchUserException e) {
            mav.addObject("message", e.getMessage());
            mav.setViewName("user/list-users");
        } catch (NoSuchTaskException e) {
            e.printStackTrace();
        }
        return mav;
    }

    @RequestMapping(value = "/delete-user")
    public ModelAndView loadDeleteUser() {
        return new ModelAndView("user/delete-user");
    }

    @RequestMapping(value = "/delete")
    public ModelAndView delete(HttpServletRequest req, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
        String email = req.getParameter("userEmail");
        try {
            userService.delete(email);
            redirectAttributes.addFlashAttribute("message", "User has been successfully deleted.");
            mav.setViewName("redirect:/user-menu");
        } catch (NoSuchUserException e) {
            mav.addObject("message", "There is no user with email: " + email);
            mav.setViewName("user/delete-user");
        }
        return mav;
    }

}
