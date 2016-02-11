package ua.artcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.artcode.process.TaskRunFacade;
import ua.artcode.service.AdminService;
import ua.artcode.service.UserServiceImpl;

/**
 * Created by Razer on 07.02.16.
 */
@Controller
@RequestMapping(value = "/course-menu")
public class CourseController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private TaskRunFacade taskRunFacade;

    @RequestMapping(value = "/create")
    public ModelAndView createCourse(){
        return new ModelAndView("course/create-course");
    }
    @RequestMapping(value = "/show")
    public ModelAndView showCourse(){
        return new ModelAndView("course/create-course");
    }
    @RequestMapping(value = "/delete")
    public ModelAndView deleteCourse(){
        return new ModelAndView("course/create-course");
    }
}
