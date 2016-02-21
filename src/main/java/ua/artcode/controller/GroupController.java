package ua.artcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchGroupException;
import ua.artcode.model.common.User;
import ua.artcode.model.common.UserGroup;
import ua.artcode.service.AdminService;
import ua.artcode.service.TeacherService;
import ua.artcode.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Razer on 15.02.16.
 */
@Controller
@RequestMapping(value = "/group-menu")
public class GroupController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/add-group")
    public ModelAndView addGroup() {
        return new ModelAndView("add-group-form");
    }

    @RequestMapping(value = "/add-user-form")
    public ModelAndView addUserForm() {
        return new ModelAndView("add-user-form");
    }

    @RequestMapping(value = "/add-users", method = RequestMethod.POST)
    public ModelAndView addUser(HttpServletRequest req, RedirectAttributes redirectAttributes) throws NoSuchGroupException, AppException {
        ModelAndView mav = new ModelAndView();
        List<User> userList = userService.getAllUsers();
        String name = req.getParameter("name");
        UserGroup userGroup = teacherService.findUserGroupByName(name);
        List<User> list = userGroup.getStudents();
        for (User user : userList) {
            if (req.getParameter(user.getName()) != null) {
                list.add(user);
            }
        }
        teacherService.updateGroup(userGroup);
        redirectAttributes.addFlashAttribute("message", "Group has been successfully create.");
        mav.setViewName("redirect:/group-menu");
        return mav;

    }

    @RequestMapping(value = "/show-groups")
    public ModelAndView showGroups() throws AppException {
        ModelAndView mav = new ModelAndView("list-groups");
        mav.addObject("groups", teacherService.getAllGroups());
        return mav;
    }

    @RequestMapping(value = "/show-group/{name}")
    public ModelAndView showLesson(@PathVariable String name) throws NoSuchGroupException {
        ModelAndView mav = new ModelAndView("show-group");
        UserGroup userGroup = teacherService.findUserGroupByName(name);
        mav.addObject("group", userGroup);
        mav.addObject("users", userGroup.getStudents());
        return mav;
    }

    @RequestMapping(value = "/delete-group-form")
    public ModelAndView deleteForm() {
        return new ModelAndView("delete-group-form");
    }

    @RequestMapping(value = "/delete-group", method = RequestMethod.POST)
    public ModelAndView deleteGroup(HttpServletRequest req, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
        String name = req.getParameter("groupName");
        try {
            teacherService.deleteGroup(name);
            redirectAttributes.addFlashAttribute("message", "Group has been successfully deleted.");
            mav.setViewName("redirect:/group-menu");
        } catch (NoSuchGroupException e) {
            mav.addObject("message", "There is no group with name: " + name);
            mav.setViewName("delete-group-form");
        }
        return mav;
    }

    @RequestMapping(value = "/create-group", method = RequestMethod.POST)
    public ModelAndView createGroup(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        ModelAndView mav = new ModelAndView("setup-groups");
        String name = req.getParameter("group_name");
        String description = req.getParameter("group_description");
        mav.addObject("name", name);
        mav.addObject("users", userService.getAllUsers());
        teacherService.addGroup(new UserGroup(name, description));
        return mav;
    }
}

