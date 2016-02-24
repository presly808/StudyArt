package ua.artcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchLessonException;
import ua.artcode.model.Lesson;
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.service.AdminService;
import ua.artcode.service.TeacherService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

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
    public ModelAndView addLesson() {
        return new ModelAndView("create-lesson-form");
    }

    @RequestMapping(value = "/create-lesson")
    public ModelAndView createLesson(HttpServletRequest req, ModelAndView mav) throws AppException, NoSuchLessonException {
        mav.setViewName("setup-tasks");
        String title = req.getParameter("lesson_title");
        String description = req.getParameter("lesson_description");
        mav.addObject("title", title);
        mav.addObject("tasks", adminService.getAll());
        teacherService.addLesson(new Lesson(title, description));
        return mav;
    }

    @RequestMapping(value = "/add-task")
    public ModelAndView addTask(RedirectAttributes redirectAttributes, HttpServletRequest req) throws AppException, NoSuchLessonException, ServletException, IOException {
        ModelAndView mav = new ModelAndView("setup-tasks");
        List<CodingBatTask> tasks = adminService.getAll();
        Lesson lesson = teacherService.findLessonByTitle(req.getParameter("title"));
        List<CodingBatTask> list = lesson.getTasks();
        for (CodingBatTask task : tasks) {
            if (req.getParameter(task.getTitle()) != null) {
                list.add(task);
            }
        }
        lesson.setTasks(list);
        teacherService.updateLesson(lesson);
        redirectAttributes.addFlashAttribute("message", "The lesson has been successfully created.");
        mav.setViewName("redirect:/lesson-menu");
        return mav;
    }

    @RequestMapping(value = "/show-lessons")
    public ModelAndView showLessons() {
        ModelAndView mav = new ModelAndView("list-lessons");
        mav.addObject("lessons", teacherService.getAllLessons());
        return mav;
    }

    @RequestMapping(value = "/edit-lesson", method = RequestMethod.POST)
    public ModelAndView editLesson(HttpServletRequest req) throws NoSuchLessonException {
        ModelAndView mav = new ModelAndView("edit-lesson-form");
        String title = req.getParameter("title");
        Lesson lesson = teacherService.findLessonByTitle(title);
        mav.addObject("lesson", lesson);
        return mav;
    }

    @RequestMapping(value = "/find-lesson")
    public ModelAndView findLesson() {
        return new ModelAndView("find-lesson");
    }

    @RequestMapping(value = "/show-lesson/{title}")
    public ModelAndView showLesson(@PathVariable String title) throws NoSuchLessonException {
        ModelAndView mav = new ModelAndView("show-lesson");
        Lesson lesson = teacherService.findLessonByTitle(title);
        mav.addObject("lesson", lesson);
        mav.addObject("tasks", lesson.getTasks());
        return mav;
    }

    @RequestMapping(value = "/delete-lesson-form")
    public ModelAndView deleteLessonForm() {
        return new ModelAndView("delete-lesson-form");
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
            mav.setViewName("delete-lesson-form");
        }
        return mav;
    }

}
