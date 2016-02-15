package ua.artcode.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.artcode.model.common.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;


/**
 * Created by Razer on 23.01.16.
 */

@Controller
public class MainController {

    @RequestMapping(value = "/menu")
    public ModelAndView menu() {
        return new ModelAndView("menu");
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
        }

        model.setViewName("main/403");
        return model;

    }

    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam(value = "error", required = false) String error, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        if (error != null) {
            mav.addObject("error", "Invalid username or password!");
        }
        return mav;
    }

    @RequestMapping(value = "/registration")
    public ModelAndView registration(@Valid @ModelAttribute("user")User user, HttpServletRequest req, HttpServletResponse resp, BindingResult result) throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        if (result.hasErrors()) {
            mav.setViewName("test2");
        } else {
            mav.setViewName("login");
        }
//        String userName = req.getParameter("userName");
//        String password = req.getParameter("password");
//        String email = req.getParameter("email");
//
        //UserType userType = req.getParameter("role") != null ? UserType.ROLE_TEACHER : UserType.ROLE_USER;
//        try {
//            //userService.register(userName, password, email, userType);
//            mav.setViewName("redirect:/index.jsp");
//            //resp.sendRedirect("/index.jsp");
//        } catch (AppException e) {
//            req.setAttribute("error", e.getMessage());
//            mav.setViewName("registration-form");
//        }
        return mav;
    }

    @RequestMapping(value = "/registration-form")
    public ModelAndView registrationForm(Model model) {
        model.addAttribute("user",new User());
        return new ModelAndView("/registration-form");
    }

    @RequestMapping(value = "/task-menu")
    public ModelAndView tasksMenu() {
        return new ModelAndView("task-menu");
    }

    @RequestMapping(value = "/course-menu")
    public ModelAndView courseMenu(){
        return new ModelAndView("course-menu");
    }

    @RequestMapping(value = "/lesson-menu")
    public ModelAndView lessonMenu(){
        return new ModelAndView("lesson-menu");
    }

    @RequestMapping(value = "/group-menu")
    public ModelAndView groupMenu(){
        return new ModelAndView("group-menu");
    }

    @RequestMapping(value = "/user-menu")
    public ModelAndView userMenu(){
        return new ModelAndView("user-menu");
    }
}



