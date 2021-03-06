package ua.artcode.controller;

import com.mongodb.DuplicateKeyException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import ua.artcode.exception.AppException;
import ua.artcode.exception.DuplicateDataException;
import ua.artcode.exception.NoSuchGroupException;
import ua.artcode.model.common.User;
import ua.artcode.model.common.UserGroup;
import ua.artcode.service.TeacherService;
import ua.artcode.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Razer on 15.02.16.
 */
@Controller
@RequestMapping(value = "/group-menu")
public class GroupController {

    @Qualifier("userServiceImpl")
    @Autowired
    private UserService userService;

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/create-group")
    public ModelAndView createGroup() {
        ModelAndView mav = new ModelAndView("group/create-group");
        mav.addObject("userGroup", new UserGroup());
        return mav;
    }

    @RequestMapping(value = "/add-group", method = RequestMethod.POST)
    public ModelAndView addGroup(@Valid UserGroup userGroup, BindingResult result, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("group/create-group");
        if (!result.hasErrors()) {
            try {
                teacherService.addGroup(userGroup);
                redirectAttributes.addFlashAttribute("name", userGroup.getName());
                redirectAttributes.addFlashAttribute("users", userService.getAllUsers());
                mav.setViewName("redirect:/group-menu/setup-users");
            } catch (DuplicateKeyException e) {
                mav.addObject("message", "Group with name: " + userGroup.getName() + " is exist");
            }
        }
        return mav;
    }

    @RequestMapping(value = "/setup-users")
    public ModelAndView setupUsers(RedirectAttributes attributes, HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("group/setup-users");
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(req);
        if (map != null) {
            mav.addObject("name", map.get("name"));
            mav.addObject("users", map.get("users"));
        } else {
            attributes.addFlashAttribute("message", "Group created successful");
            mav.setViewName("redirect:/group-menu");
        }
        return mav;
    }

    @RequestMapping(value = "/add-user-form")
    public String loadAddUserForm() {
        return "user/create-user";
    }

    @RequestMapping(value = "/add-users", method = RequestMethod.POST)
    public ModelAndView addUser(HttpServletRequest req, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("main/group-menu");
        try {
            List<User> userList = userService.getAllUsers();
            String name = req.getParameter("name");
            UserGroup userGroup = teacherService.findUserGroupByName(name);
            List<User> list = userGroup.getStudents();
            for (User user : userList) {
                if (req.getParameter(user.getName()) != null) {
                    list.add(user);
                }
            }
            teacherService.updateGroup(userGroup.getId(), userGroup);
        } catch (DuplicateDataException e) {
            mav.addObject("message", e.getMessage());
        } catch (NoSuchGroupException e) {
            mav.addObject("message", e.getMessage());
        }
        redirectAttributes.addFlashAttribute("message", "Group has been successfully create.");
        mav.setViewName("redirect:/group-menu");
        return mav;
    }

    @RequestMapping(value = "/show-groups")
    public ModelAndView showGroups() throws AppException {
        ModelAndView mav = new ModelAndView("group/list-groups");
        mav.addObject("groups", teacherService.getAllGroups());
        return mav;
    }

    @RequestMapping(value = "/edit-group", method = RequestMethod.POST)
    public ModelAndView editGroup(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("group/edit-group");
        try {
            String id = req.getParameter("id");
            UserGroup userGroup = teacherService.findUserGroupById(new ObjectId(id));
            List<User> usersInGroup = userGroup.getStudents();

            List<User> allUsers = userService.getAllUsers();
            allUsers.removeAll(usersInGroup);

            mav.addObject("userGroup", userGroup);
            mav.addObject("usersInGroup", usersInGroup);
            mav.addObject("allUsers", allUsers);
        } catch (NoSuchGroupException e) {
            mav.addObject("message", e.getMessage());
            mav.setViewName("main/group-menu");
        }
        return mav;
    }

    @RequestMapping(value = "/update-group")
    public ModelAndView updateGroup(@Valid UserGroup userGroup, BindingResult result, HttpServletRequest req, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("main/group-menu");
        List<User> userInGroup = new ArrayList<>();

        List<User> allUsers = userService.getAllUsers();

        for (User user : allUsers) {
            if (req.getParameter(user.getEmail()) != null) {
                userInGroup.add(user);
            }
        }

        if (result.hasErrors()) {
            allUsers.removeAll(userInGroup);
            mav.addObject("userInGroup", userInGroup);
            mav.addObject("allUsers", allUsers);
            mav.setViewName("group/edit-group");

        } else {
            try {
                userGroup.setStudents(userInGroup);
                teacherService.updateGroup(userGroup.getId(), userGroup);
                redirectAttributes.addFlashAttribute("message", "The group has been successfully updated.");
                mav.setViewName("redirect:/group-menu");
            } catch (DuplicateDataException e) {
                mav.addObject("message", e.getMessage());

            } catch (NoSuchGroupException e) {
                mav.addObject("message", e.getMessage());
            }
        }
        return mav;
    }

    @RequestMapping(value = "/find-group")
    public String loadFindGroup() {
        return "group/find-group";
    }

    @RequestMapping(value = "/show-group", method = RequestMethod.POST)
    public ModelAndView showGroupPost(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("group/show-group");
        try {
            String name = req.getParameter("name");
            UserGroup userGroup = teacherService.findUserGroupByName(name);
            mav.addObject("group", userGroup);
            mav.addObject("users", userGroup.getStudents());

        } catch (NoSuchGroupException e) {
            mav.setViewName("group/find-group");
            mav.addObject("message", e.getMessage());
        }
        return mav;
    }

    @RequestMapping(value = "/show-group/{name}")
    public ModelAndView showGroup(@PathVariable String name, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("group/show-group");
        try {
            UserGroup userGroup = teacherService.findUserGroupByName(name);
            mav.addObject("group", userGroup);
            mav.addObject("users", userGroup.getStudents());
        } catch (NoSuchGroupException e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            mav.setViewName("redirect:group-menu/show-groups");
        }
        return mav;
    }

    @RequestMapping(value = "/delete-form")
    public String deleteForm() {
        return "group/delete-group";
    }

    @RequestMapping(value = "/delete-group", method = RequestMethod.POST)
    public ModelAndView deleteGroup(HttpServletRequest req, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("redirect:/group-menu");
        String name = req.getParameter("groupName");
        try {
            teacherService.deleteGroup(name);
            redirectAttributes.addFlashAttribute("message", "Group has been successfully deleted.");
        } catch (NoSuchGroupException e) {
            mav.addObject("message", "There is no group with name: " + name);
            mav.setViewName("group/delete-group");
        }
        return mav;
    }

}

