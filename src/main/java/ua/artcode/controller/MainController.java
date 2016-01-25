package ua.artcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ua.artcode.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Razer on 23.01.16.
 */
@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response) {
//        ModelAndView mav = new ModelAndView();
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        try {
//            if (userService.authenticate(email, password)) {
//                mav.setViewName("menu");
//            }
//        } catch (AppException e) {
//            request.setAttribute("error", e.getMessage());
//            mav.setViewName("redirect:/index.htm");
//        }
        return "menu";
    }
}
