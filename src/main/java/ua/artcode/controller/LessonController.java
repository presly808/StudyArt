package ua.artcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchLessonException;
import ua.artcode.model.Lesson;
import ua.artcode.model.codingbat.CodingBatTask;
import ua.artcode.service.AdminService;
import ua.artcode.service.TeacherService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        return new ModelAndView("add-lesson-form");
    }

    @RequestMapping(value = "/create-lesson")
    public ModelAndView createLesson(HttpServletRequest req, HttpServletResponse resp) throws AppException, NoSuchLessonException {
        ModelAndView mav = new ModelAndView("setup-tasks");
        String title = req.getParameter("lesson_title");
        String description = req.getParameter("lesson_description");
        Lesson lesson= new Lesson(title, description);
        mav.addObject("title", title);
        mav.addObject("tasks",adminService.getAll());
        teacherService.addLesson(lesson);
        return mav;
    }

    @RequestMapping(value ="/add-task")
    public ModelAndView addTask(HttpServletRequest req, HttpServletResponse resp) throws AppException, NoSuchLessonException {ModelAndView mav = new ModelAndView("setup-tasks");
        List<CodingBatTask> tasks = adminService.getAll();
        Lesson lesson=teacherService.findByTitleLesson(req.getParameter("title"));
        List<CodingBatTask> list=lesson.getTasks();
        for (CodingBatTask task : tasks) {
            if (req.getParameter(task.getTitle())!=null){
                list.add(task);
            }
        }
        lesson.setTasks(list);
        teacherService.updateLesson(lesson);
        return new ModelAndView("lesson-menu");
    }

    @RequestMapping(value = "/show-lessons")
    public ModelAndView showLessons() {
        ModelAndView mav=new ModelAndView("list-lessons");
        mav.addObject("lessons",teacherService.getAllLessons());
        return mav;
    }

    @RequestMapping(value = "/show-lesson/{title}")
    public ModelAndView showLesson(@PathVariable String title) throws NoSuchLessonException {
        ModelAndView mav=new ModelAndView("show-lesson");
        Lesson lesson=teacherService.findByTitleLesson(title);
        mav.addObject("lesson",lesson);
        mav.addObject("tasks",lesson.getTasks());
        return mav;
    }

    @RequestMapping(value = "/delete-lesson-form")
    public ModelAndView deleteLessonForm() {
        return new ModelAndView("delete-lesson-form");
    }

    @RequestMapping(value ="/delete")
    public ModelAndView deleteLesson(HttpServletRequest req, HttpServletResponse resp) throws NoSuchLessonException {
        teacherService.deleteLesson(req.getParameter("lessonTitle"));
        return null;
    }

}
