package ua.artcode.controller;

import com.mongodb.DuplicateKeyException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchLessonException;
import ua.artcode.model.codingbat.Task;
import ua.artcode.model.common.Lesson;
import ua.artcode.service.AdminService;
import ua.artcode.service.TeacherService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
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

    @RequestMapping(value = "/create-lesson")
    public ModelAndView addLesson() {
        ModelAndView mav = new ModelAndView("lesson/create-lesson");
        mav.addObject("lesson", new Lesson());
        return mav;
    }

    @RequestMapping(value = "/add-lesson", method = RequestMethod.POST)
    public ModelAndView createLesson(@Valid Lesson lesson, BindingResult result, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("lesson/create-lesson");
        if (!result.hasErrors()) {
            try {
                teacherService.addLesson(lesson);
                List<Task> taskList = adminService.getAllTasks();
                if (taskList.size() > 0) {
                    redirectAttributes.addFlashAttribute("title", lesson.getTitle());
                    redirectAttributes.addFlashAttribute("tasks", taskList);
                    mav.setViewName("redirect:/lesson-menu/setup-tasks");
                } else {
                    redirectAttributes.addFlashAttribute("message", "The lesson has been successfully created.");
                    mav.setViewName("redirect:/lesson-menu");
                }
            } catch (AppException e) {
                mav.addObject("message", e.getMessage());
            } catch (DuplicateKeyException e) {
                mav.addObject("message", "Lesson with title: " + lesson.getTitle() + " already exist!");
            }
        }
        return mav;
    }

    @RequestMapping(value = "/setup-tasks")
    public ModelAndView setupTasks(HttpServletRequest req, RedirectAttributes attributes) {
        ModelAndView mav = new ModelAndView("lesson/setup-tasks");
        Map<String, ?> map = RequestContextUtils.getInputFlashMap(req);
        if (map != null) {
            mav.addObject("title047163Lesson", map.get("title"));
            mav.addObject("tasks", map.get("tasks"));
        } else {
            attributes.addFlashAttribute("message", "Lesson created successful");
            mav.setViewName("redirect:/lesson-menu");
        }
        return mav;
    }

    @RequestMapping(value = "/add-task")
    public ModelAndView addTask(RedirectAttributes redirectAttributes, HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("main/lesson-menu");
        try {
            List<Task> tasks = adminService.getAllTasks();
            String title = req.getParameter("title047163Lesson");
            Lesson lesson = teacherService.findLessonByTitle(title);
            List<Task> list = lesson.getTasks();
            for (Task task : tasks) {
                if (req.getParameter(task.getTitle()) != null) {
                    list.add(task);
                }
            }
            lesson.setTasks(list);
            teacherService.updateLesson(lesson.getId(), lesson);
            redirectAttributes.addFlashAttribute("message", "The lesson has been successfully created.");
            mav.setViewName("redirect:/lesson-menu");
        } catch (NoSuchLessonException e) {
            mav.addObject("message", e.getMessage());
        } catch (AppException e) {
            mav.addObject("message", e.getMessage());
        }
        return mav;
    }

    @RequestMapping(value = "/edit-lesson", method = RequestMethod.POST)
    public ModelAndView editLesson(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView("lesson/edit-lesson");
        String id = req.getParameter("id");
        try {
            Lesson lesson = teacherService.findLessonById(new ObjectId(id));
            List<Task> tasksOnLesson = lesson.getTasks();
            List<Task> allTasks = adminService.getAllTasks();
            allTasks.removeAll(tasksOnLesson);

            mav.addObject("lesson", lesson);
            mav.addObject("tasksInLesson", tasksOnLesson);
            mav.addObject("allTasks", allTasks);
        } catch (NoSuchLessonException e) {
            mav.setViewName("lesson/list-lessons");
            mav.addObject("message", e.getMessage());

        } catch (AppException e) {
            mav.addObject("message", e.getMessage());
            mav.setViewName("main/lesson-menu");
        }
        return mav;
    }
    //TODO
    @RequestMapping(value = "/update-lesson")
    public ModelAndView updateLesson(@Valid Lesson lesson, BindingResult result, HttpServletRequest req, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("lesson/edit-lesson");
        List<Task> taskInLesson = new ArrayList<>();
        List<Task> allTasks = null;
        try {
            allTasks = adminService.getAllTasks();
        } catch (AppException e) {
            mav.addObject("message", e.getMessage());
            mav.setViewName("main/lesson-menu");
        }

        for (Task task : allTasks) {
            if (req.getParameter(task.getTitle()) != null) {
                taskInLesson.add(task);
            }
        }
        if (result.hasErrors()) {
            allTasks.removeAll(taskInLesson);

            mav.addObject("tasksInLesson", taskInLesson);
            mav.addObject("allTasks", allTasks);

        } else {
            try {
                lesson.setTasks(taskInLesson);
                teacherService.updateLesson(lesson.getId(), lesson);
                redirectAttributes.addFlashAttribute("message", "The lesson has been successfully updated.");
                mav.setViewName("redirect:/lesson-menu");
            } catch (AppException e) {
                mav.addObject("message", e.getMessage());
                mav.setViewName("main/lesson-menu");
            } catch (NoSuchLessonException e) {
                e.printStackTrace();
            }
        }
        return mav;
    }

    @RequestMapping(value = "/show-lessons")
    public ModelAndView showLessons() {
        ModelAndView mav = new ModelAndView("lesson/list-lessons");
        mav.addObject("lessons", teacherService.getAllLessons());
        return mav;
    }

    @RequestMapping(value = "/find-lesson")
    public String findLesson() {
        return "lesson/find-lesson";
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
        ModelAndView mav = new ModelAndView("lesson/show-lesson");
        try {
            String title = req.getParameter("title");
            Lesson lesson = teacherService.findLessonByTitle(title);
            mav.addObject("lesson", lesson);
            mav.addObject("tasks", lesson.getTasks());
        } catch (NoSuchLessonException e) {
            mav.setViewName("lesson/find-lesson");
            mav.addObject("error", e.getMessage());
        }
        return mav;
    }

    @RequestMapping(value = "/delete-lesson")
    public String deleteLessonForm() {
        return "lesson/delete-lesson";
    }

    @RequestMapping(value = "/delete-form")
    public ModelAndView deleteLesson(HttpServletRequest req, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("redirect:/lesson-menu");
        try {
            String lessonTitle = req.getParameter("lessonTitle");
            teacherService.deleteLessonByTitle(lessonTitle);
            redirectAttributes.addFlashAttribute("message", "The lesson has been successfully deleted.");
        } catch (NoSuchLessonException e) {
            mav.addObject("message", e.getMessage());
            mav.setViewName("lesson/delete-lesson");
        }
        return mav;
    }

}
