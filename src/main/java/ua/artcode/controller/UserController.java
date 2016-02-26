package ua.artcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.artcode.exception.NoSuchUserException;
import ua.artcode.model.common.User;
import ua.artcode.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Razer on 21.02.16.
 */
@Controller
@RequestMapping(value = "/user-menu")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add-admin")
    public ModelAndView addAdmin() {
        return new ModelAndView("create-admin-form");
    }

    @RequestMapping(value = "/show-users")
    public ModelAndView showUsers() {
        ModelAndView mav = new ModelAndView("list-users");
        mav.addObject("users", userService.getAllUsers());
        return mav;
    }

    @RequestMapping(value = "/show-user/{email:.+}")
    public ModelAndView showUser(@PathVariable String email) throws  NoSuchUserException {
        ModelAndView mav = new ModelAndView("show-user");
        //TODO exception try catch
        User user= userService.findUser(email);
        mav.addObject("user", user);
        return mav;
    }

    @RequestMapping(value = "/delete-user")
    public ModelAndView deleteUserForm() {
        return new ModelAndView("delete-user");
    }

    @RequestMapping(value = "/delete")
    public ModelAndView delete(HttpServletRequest req,RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
        String email = req.getParameter("userEmail");
        try {
            userService.delete(email);
            redirectAttributes.addFlashAttribute("message", "User has been successfully deleted.");
            mav.setViewName("redirect:/user-menu");
        } catch (NoSuchUserException e) {
            mav.addObject("message", "There is no user with email: " + email);
            mav.setViewName("delete-user");
        }
        return mav;
    }

}
