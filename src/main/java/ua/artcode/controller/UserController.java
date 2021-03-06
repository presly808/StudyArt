package ua.artcode.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.artcode.exception.NoSuchTaskException;
import ua.artcode.exception.NoSuchUserException;
import ua.artcode.model.common.Course;
import ua.artcode.model.common.Task;
import ua.artcode.model.common.UserType;
import ua.artcode.model.taskComponent.TaskTestResult;
import ua.artcode.model.common.User;
import ua.artcode.service.AdminService;
import ua.artcode.service.TeacherService;
import ua.artcode.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Razer on 21.02.16.
 */
@Controller
@RequestMapping(value = "/user-menu")
public class UserController {

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/show-users")
    public ModelAndView showUsers() {
        ModelAndView mav = new ModelAndView("user/list-users");
        mav.addObject("users", userService.getAllUsers());
        return mav;
    }

    /*@RequestMapping(value = "/show-user/{name}")
    public ModelAndView showUser(@PathVariable String name) {
        ModelAndView mav = new ModelAndView("user/show-user");
        try {
            User user = userService.findUser(name);
            String role = String.valueOf(user.getUserType());

            switch (role) {
                case "ROLE_TEACHER":
                    role = "Teacher";
                    break;
                case "ROLE_STUDENT":
                    role = "Student";
                    break;
                default:
                    role = "Admin";
                    break;
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
            if (result.size() != 0) {
                mav.addObject("result", result);
            }
            mav.addObject("role", role);
        } catch (NoSuchUserException e) {
            mav.addObject("message", e.getMessage());
            mav.setViewName("user/list-users");
        } catch (NoSuchTaskException e) {
            e.printStackTrace();
        }
        return mav;
    }*/

    // todo refactor logic, simplify
    @RequestMapping(value = "/show-user/{name}")
    public ModelAndView showUserPage(@PathVariable String name, Principal principal) {

        ModelAndView mav = new ModelAndView();

        try {
            User currentUser = userService.findUser(principal.getName());
            User targetUser = userService.findUser(name);

            String targetUserRole = targetUser.getUserType().toFormattedString();
            boolean isPageOwner = principal.getName().equals(targetUser.getName());

            mav.addObject("role", targetUserRole);
            mav.addObject("isOwner", isPageOwner);
            mav.addObject("user", targetUser);

            if(targetUser.getUserType() == UserType.ROLE_TEACHER){
                List<Course> courseList = teacherService.getOwnerCourses(targetUser);

                mav.addObject("courses", courseList);
                mav.setViewName("main/teacher-info");

            } else if(targetUser.getUserType() == UserType.ROLE_STUDENT) {
                mav.setViewName("main/user-info");

                targetUser.getSubscribedCourses().stream()
                        .forEach((course) -> userService.addUserCourseStatInformation(targetUser, course));
            }

            /*//todo may be long time processing O(N^3), find some solution
            if(currentUser.equals(targetUser)){ // see stat if you are owner
                targetUser.getSubscribedCourses().stream()
                        .forEach((course) -> userService.addUserCourseStatInformation(targetUser, course));
            } else if(!(currentUser.getUserType() == UserType.ROLE_STUDENT)){ // see statistics of other if you are teacher
                targetUser.getSubscribedCourses().stream()
                        .forEach((course) -> userService.addUserCourseStatInformation(targetUser, course));
            }*/

        } catch (NoSuchUserException e) {
            mav.addObject("message", e.getMessage());
            mav.setViewName("user/list-users");
        }
        return mav;
    }

    @RequestMapping(value = "/delete-form")
    public ModelAndView loadDeleteUser() {
        return new ModelAndView("user/delete-user");
    }

    @RequestMapping(value = "/delete-user")
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
