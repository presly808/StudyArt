package ua.artcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchGroupException;
import ua.artcode.model.common.UserGroup;
import ua.artcode.service.AdminService;
import ua.artcode.service.TeacherService;
import ua.artcode.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public ModelAndView addGroup(){
        return new ModelAndView("add-group-form");
    }

    @RequestMapping(value = "/show-groups")
    public ModelAndView showGroups(){
        return null;
    }

    @RequestMapping(value = "/add-users")
    public ModelAndView addUsers(HttpServletRequest req, HttpServletResponse resp) throws AppException, NoSuchGroupException {
     return null;
    }

    @RequestMapping(value = "/delete-group")
    public ModelAndView deleteGroup(){
        return null;
    }

    @RequestMapping(value = "/create-group")
    public ModelAndView createGroup(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        ModelAndView mav=new ModelAndView("setup-groups");
        String name = req.getParameter("group_name");
        String description=req.getParameter("group_description");
        mav.addObject("name", name);
        mav.addObject("users",userService.getAllUser());
        teacherService.addGroup(new UserGroup(name,description));
        return mav;
    }
}

