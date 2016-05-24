package ua.artcode.controller;

import com.mongodb.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import ua.artcode.model.common.Course;
import ua.artcode.model.common.Lesson;
import ua.artcode.model.common.Task;
import ua.artcode.model.common.User;
import ua.artcode.service.UserService;
import ua.artcode.utils.pagination.Paginator;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {


    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/menu")
    public String loadMenu() {
        return "main/menu";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView mav = new ModelAndView("403");
        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            mav.addObject("username", userDetail.getUsername());
        }
        return mav;

    }

    @RequestMapping(value = "/login")
    public ModelAndView login(@RequestParam(value = "error", required = false) String error) {
        ModelAndView mav = new ModelAndView();//todo forward to main menu
        if (error != null) {
            mav.addObject("message", "Invalid username or password!");
        }
        return mav;
    }

    @RequestMapping(value = "/registration-form")
    public ModelAndView registrationForm() {
        ModelAndView mav=new ModelAndView("main/registration");
        mav.addObject("user", new User());
        return mav;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registration(@Valid User user, BindingResult result, RedirectAttributes redirectAttributes)  {
        ModelAndView mav = new ModelAndView("main/registration");
        if (!result.hasErrors()) {
            try {
                userService.register(user);
                redirectAttributes.addFlashAttribute("message", messageSource.getMessage("registration.successful", null, LocaleContextHolder.getLocale()));
                mav.setViewName("redirect:/login");
            } catch (DuplicateKeyException e) {
                mav.addObject("message", "User already exist!");
            }
        }
        return mav;
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
        ModelAndView mav = new ModelAndView("main/lesson-menu");
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
    public ModelAndView loadUserMenu(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("main/user-menu");
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(req);
        if (map != null) {
            mav.addObject("message", map.get("message"));
        }
        return mav;
    }

    @RequestMapping("/search")
    public String generalSearch(@RequestParam(value = "length", defaultValue = "50") int length,
                                @RequestParam(value = "offset",defaultValue = "0") int offset,
                                @RequestParam("key") String keyWord,
                                @RequestParam("type") String searchType, Model model){
        // use pagination

        long usersCount = userService.searchUsersCount(keyWord);
        long tasksCount = userService.searchTasksCount(keyWord);
        long coursesCount = userService.searchCoursesCount(keyWord);
        long lessonsCount = userService.searchLessonsCount(keyWord);

        long pagingTotal = 0;

        if("user".equals(searchType)){
            List<User> users = userService.search(keyWord, offset, length);
            model.addAttribute("foundUsers", users);
            pagingTotal = usersCount;
        } else if("task".equals(searchType)) {
            List<Task> tasks = userService.searchTasks(keyWord, offset, length);
            model.addAttribute("foundTasks", tasks);
            pagingTotal = tasksCount;
        } else if("course".equals(searchType)) {
            List<Course> tasks = userService.searchCourses(keyWord, offset, length);
            model.addAttribute("foundCourses", tasks);
            pagingTotal = coursesCount;
        } else if("lesson".equals(searchType)) {
            List<Lesson> tasks = userService.searchLessons(keyWord, offset, length);
            model.addAttribute("foundLessons", tasks);
            pagingTotal = lessonsCount;
        }

        model.addAttribute("searchType", searchType);
        model.addAttribute("searchWord", keyWord);
        model.addAttribute("foundUserSize", usersCount);
        model.addAttribute("foundTaskSize", tasksCount);
        model.addAttribute("foundCourseSize", coursesCount);
        model.addAttribute("foundLessonSize", lessonsCount);

        model.addAttribute("pagingLinks", Paginator.getPaginationElements(offset, length, pagingTotal));

        // add usersCount found size
        return "main/search";
    }

    @RequestMapping("/user-get")
    public String userGet(@RequestParam("key") String keyWord, Model model){
        // use pagination
        List<User> list = userService.search(keyWord, 0, 50);
        long amount = userService.searchUsersCount(keyWord);
        model.addAttribute("foundUsers", list);
        model.addAttribute("searchWord", keyWord);
        model.addAttribute("amountFoundUser", amount);
        // add amount found size
        return "main/search";
    }

    @RequestMapping(value = "/service-menu")
    public ModelAndView loadServiceMenu(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("main/service-menu");
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(req);
        if (map != null) {
            mav.addObject("message", map.get("message"));
        }
        return mav;
    }
}



