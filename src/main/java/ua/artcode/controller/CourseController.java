package ua.artcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchCourseException;
import ua.artcode.model.Course;
import ua.artcode.model.Lesson;
import ua.artcode.service.TeacherService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


/**
 * Created by Razer on 07.02.16.
 */
@Controller
@RequestMapping(value = "/course-menu")
public class CourseController {

    @Autowired
    private TeacherService teacherService;

    @RequestMapping(value = "/add-course")
    public String addCourse(Model model) {
        model.addAttribute("course", new Course());
        return "create-course-form";
    }

    //TODO exception
    @RequestMapping(value = "/create-course", method = RequestMethod.POST)
    public ModelAndView createCourse(@Valid Course course, BindingResult result) throws AppException {

        ModelAndView mav = new ModelAndView();

        if (result.hasErrors()) {
            mav.setViewName("create-course-form");
        } else {
            teacherService.addCourse(course);
            List lessonsList = teacherService.getAllLessons();

            if (lessonsList.size() > 0) {
                mav.setViewName("setup-lessons");
                mav.addObject("title", course.getTitle());
                mav.addObject("lessons", course.getLessonList());
            } else {
                mav.setViewName("course-menu");
                mav.addObject("message", "The course has been successfully created.");
            }
        }
        return mav;
    }

    @RequestMapping(value = "/add-lesson")
    public ModelAndView addLesson(RedirectAttributes redirectAttributes, HttpServletRequest req, ModelAndView mav) throws NoSuchCourseException {
        List<Lesson> lessons = teacherService.getAllLessons();
        Course course = teacherService.findCourseByTitle(req.getParameter("title"));
        List<Lesson> list = course.getLessonList();
        for (Lesson lesson : lessons) {
            if (req.getParameter(lesson.getTitle()) != null) {
                list.add(lesson);
            }
        }
        course.setLessonList(list);
        try {
            teacherService.updateCourse(course);
        } catch (AppException e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("message", "Course has been successfully created.");
        mav.setViewName("redirect:/course-menu");
        return mav;
    }

    @RequestMapping(value = "/show-courses")
    public ModelAndView showCourse() {
        ModelAndView mav = new ModelAndView("list-courses");
        mav.addObject("courses", teacherService.getAllCourses());
        return mav;
    }

    @RequestMapping(value = "/find-course")
    public ModelAndView loadFindCourse(){
        return new ModelAndView("find-course");
    }

    @RequestMapping(value = "/edit-course")
    public ModelAndView editCourse(){
        return null;
    }

    @RequestMapping(value = "/show-course/{title}")
    public ModelAndView showCourse(@PathVariable String title) throws NoSuchCourseException {
        ModelAndView mav = new ModelAndView("show-course");
        Course course = teacherService.findCourseByTitle(title);
        mav.addObject("course", course);
        mav.addObject("lessons", course.getLessonList());
        return mav;
    }

    @RequestMapping(value = "/show-course")
    public ModelAndView showCourse(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        String title=req.getParameter("title");
        try {
            Course course = teacherService.findCourseByTitle(title);
            mav.setViewName("show-course");
            mav.addObject("course", course);
            mav.addObject("lessons", course.getLessonList());
        } catch (NoSuchCourseException e) {
            mav.setViewName("find-course");
            mav.addObject("error", e.getMessage());
        }
        return mav;
    }

    @RequestMapping(value = "/delete-course")
    public ModelAndView loadDeleteLesson() {
        return new ModelAndView("delete-course-form");
    }

    @RequestMapping(value = "/delete")
    public ModelAndView deleteCourse(HttpServletRequest req, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView();
        String courseTitle = req.getParameter("courseTitle");
        try {
            teacherService.deleteCourse(courseTitle);
            redirectAttributes.addFlashAttribute("message", "The course has been successfully deleted.");
            mav.setViewName("redirect:/course-menu");
        } catch (NoSuchCourseException e) {
            mav.addObject("message", "There is no course with title: " + courseTitle);
            mav.setViewName("delete-course");
        }
        return mav;
    }
}
