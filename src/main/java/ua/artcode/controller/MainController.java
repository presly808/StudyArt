package ua.artcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ua.artcode.model.common.User;
import ua.artcode.service.UserServiceImpl;

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

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/menu")
    public ModelAndView menu() {
        return new ModelAndView("menu");
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {
        ModelAndView model = new ModelAndView();
        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
        }

        model.setViewName("403");
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
    public ModelAndView registration(@Valid User user, HttpServletRequest req, HttpServletResponse resp, BindingResult result) throws ServletException, IOException {
        ModelAndView mav = new ModelAndView();
        if (result.hasErrors()) {
            mav.setViewName("test");
        } else {
            mav.setViewName("menu");
        }
//        String userName = req.getParameter("userName");
//        String password = req.getParameter("password");
//        String email = req.getParameter("email");
        User z = user;
        //UserType userType = req.getParameter("role") != null ? UserType.ROLE_TEACHER : UserType.ROLE_USER;
//        try {
//            //userService.register(userName, password, email, userType);
//            //TODO without .jsp
//            mav.setViewName("redirect:/index.jsp");
//            //resp.sendRedirect("/index.jsp");
//        } catch (AppException e) {
//            req.setAttribute("error", e.getMessage());
//            mav.setViewName("registration-form");
//        }
        return mav;
    }

    @RequestMapping(value = "/registration-form")
    public ModelAndView registrationForm() {
        return new ModelAndView("registration-form");
    }

    @RequestMapping(value = "/xyz")
    public String xyz(Model model) {
        model.addAttribute("user", new User());
        return "test";
    }

    @RequestMapping(value = "/my-menu")
    public ModelAndView tasksMenu() {
        return new ModelAndView("my-menu");
    }

    @RequestMapping(value = "/course-menu")
    public ModelAndView courseMenu(){
        return new ModelAndView("course-menu");
    }
}



