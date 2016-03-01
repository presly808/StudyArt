package ua.artcode.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchLessonException;
import ua.artcode.model.Lesson;
import ua.artcode.model.codingbat.Task;
import ua.artcode.service.AdminService;
import ua.artcode.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Razer on 13.02.16.
 */
@Controller
@RequestMapping(value = "/lesson-menu")
public class LessonController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/add-lesson")
    public String addLesson(Model model) {
        model.addAttribute("lesson", new Lesson());
        return "lesson/create-lesson";
    }

    @RequestMapping(value = "/create-lesson", method = RequestMethod.POST)
    public ModelAndView createLesson(@Valid Lesson lesson, BindingResult result, RedirectAttributes redirectAttributes) throws NoSuchLessonException, AppException {
        ModelAndView mav = new ModelAndView("lesson/create-lesson");
        if (!result.hasErrors()) {
            try {
                teacherService.addLesson(lesson);
                redirectAttributes.addFlashAttribute("title", lesson.getTitle());
                redirectAttributes.addFlashAttribute("tasks", adminService.getAll());
                mav.setViewName("redirect:/lesson-menu/setup-tasks");
            } catch (AppException e) {
                mav.addObject("message", e.getMessage());
                mav.setViewName("lesson/create-lesson");
            }
        }
        return mav;
    }

    @RequestMapping(value = "/setup-tasks")
    public ModelAndView setupTasks(HttpServletRequest req, RedirectAttributes attributes) {
        ModelAndView mav = new ModelAndView("lesson/setup-tasks");
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(req);
        if (map != null) {
            mav.addObject("title",map.get("title"));
            mav.addObject("tasks",map.get("tasks"));
        }else {
            attributes.addFlashAttribute("message","Lesson created successful");
            mav.setViewName("redirect:/lesson-menu");
        }
        return mav;
    }

    @RequestMapping(value = "/add-task")
    public ModelAndView addTask(RedirectAttributes redirectAttributes, HttpServletRequest req) throws AppException, ServletException, IOException {
        ModelAndView mav = new ModelAndView("lesson/setup-tasks");
        List<Task> tasks = adminService.getAll();
        try {
            String title = req.getParameter("title");
            Lesson lesson = teacherService.findLessonByTitle(title);
            List<Task> list = lesson.getTasks();
            for (Task task : tasks) {
                if (req.getParameter(task.getTitle()) != null) {
                    list.add(task);
                }
            }
            lesson.setTasks(list);
            teacherService.updateLesson(lesson);
            redirectAttributes.addFlashAttribute("message", "The lesson has been successfully created.");
            mav.setViewName("redirect:/lesson-menu");
        } catch (NoSuchLessonException e) {
            mav.setViewName("");
            mav.addObject("message", e.getMessage());
        }
        return mav;
    }

    @RequestMapping(value = "/show-lessons")
    public ModelAndView showLessons() {
        ModelAndView mav = new ModelAndView("lesson/list-lessons");
        mav.addObject("lessons", teacherService.getAllLessons());
        return mav;
    }

    @RequestMapping(value = "/edit-lesson", method = RequestMethod.POST)
    public ModelAndView editLesson(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("lesson/edit-lesson");
        String id = req.getParameter("id");
        try {
            Lesson lesson = teacherService.findLessonById(new ObjectId(id));
            mav.addObject("lesson", lesson);
        } catch (NoSuchLessonException e) {
            mav.setViewName("lesson/list-lessons");
            mav.addObject("message", e.getMessage());
        }
        return mav;
    }

    @RequestMapping(value = "/find-lesson")
    public ModelAndView findLesson() {
        return new ModelAndView("lesson/find-lesson");
    }

    @RequestMapping(value = "/show-lesson/{title}")
    public ModelAndView showLesson(@PathVariable String title) {
        ModelAndView mav = new ModelAndView("lesson/show-lesson");
        try {
            Lesson lesson = teacherService.findLessonByTitle(title);
            mav.addObject(lesson);
            mav.addObject("tasks", lesson.getTasks());
        } catch (NoSuchLessonException e) {
            mav.addObject("message", e.getMessage());
            mav.setViewName("lesson/list-lessons");
        }
        return mav;
    }

    @RequestMapping(value = "/show-lesson", method = RequestMethod.POST)
    public ModelAndView showLessonPost(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        String title = req.getParameter("title");
        try {
            Lesson lesson = teacherService.findLessonByTitle(title);
            mav.setViewName("lesson/show-lesson");
            mav.addObject("lesson", lesson);
            mav.addObject("tasks", lesson.getTasks());
        } catch (NoSuchLessonException e) {
            mav.setViewName("lesson/find-lesson");
            mav.addObject("error", e.getMessage());
        }
        return mav;
    }

    @RequestMapping(value = "/delete-lesson")
    public ModelAndView deleteLessonForm() {
        return new ModelAndView("lesson/delete-lesson");
    }

    @RequestMapping(value = "/delete")
    public ModelAndView deleteLesson(HttpServletRequest req, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
        String lessonTitle = req.getParameter("lessonTitle");
        try {
            teacherService.deleteLesson(lessonTitle);
            redirectAttributes.addFlashAttribute("message", "The lesson has been successfully deleted.");
            mav.setViewName("redirect:/lesson-menu");
        } catch (NoSuchLessonException e) {
            mav.addObject("message", "There is no lesson with title: " + lessonTitle);
            mav.setViewName("lesson/delete-lesson");
        }
        return mav;
    }

}
