package ua.artcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
import org.springframework.web.servlet.support.RequestContextUtils;
import ua.artcode.exception.AppException;
import ua.artcode.model.common.User;
import ua.artcode.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;


/**
 * Created by Razer on 23.01.16.
 */

@Controller
public class MainController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;


    @RequestMapping(value = "/menu")
    public ModelAndView loadMenu() {
        return new ModelAndView("main/menu");
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
    public ModelAndView login(@RequestParam(value = "error", required = false) String error) {
        ModelAndView mav = new ModelAndView();
        if (error != null) {
            mav.addObject("message", "Invalid username or password!");
        }
        return mav;
    }

    @RequestMapping(value = "/registration-form")
    public String registrationForm(Model model) {
        model.addAttribute("user", new User());
        return "main/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@Valid User user, BindingResult result, Model model) throws ServletException, IOException, AppException {
        if (result.hasErrors()) {
            return "main/registration";
        }
        model.addAttribute("message", messageSource.getMessage("label.registration.successful", null, LocaleContextHolder.getLocale()));
        userService.register(user);
        return "login";
    }

    @RequestMapping(value = "/task-menu")
    public ModelAndView taskMenu(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("main/task-menu");
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(req);
        if (map != null) {
            mav.addObject("message", map.get("message"));
        }
        return mav;
    }

    @RequestMapping(value = "/course-menu")
    public ModelAndView loadCourseMenu(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("main/course-menu");
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(req);
        if (map != null) {
            mav.addObject("message", map.get("message"));
        }
        return mav;
    }


    @RequestMapping(value = "/lesson-menu")
    public ModelAndView loadLessonMenu(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("main/course-menu");
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(req);
        if (map != null) {
            mav.addObject("message", map.get("message"));
        }
        return mav;
    }

    @RequestMapping(value = "/group-menu")
    public ModelAndView loadGroupMenu(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("main/group-menu");
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(req);
        if (map != null) {
            mav.addObject("message", map.get("message"));
        }
        return mav;
    }



    @RequestMapping(value = "/user-menu")
    public ModelAndView loadUserMenu() {
        return new ModelAndView("main/user-menu");
    }
}



