package ua.artcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.artcode.exception.NoSuchCourseException;
import ua.artcode.model.Course;
import ua.artcode.model.Lesson;
import ua.artcode.service.TeacherService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public ModelAndView addCourse() {
        return new ModelAndView("create-course-form");
    }

    @RequestMapping(value = "/create-course",method = RequestMethod.POST)
    public ModelAndView createCourse(HttpServletRequest req, ModelAndView mav) {

        String title = req.getParameter("course_title");
        String description = req.getParameter("course_description");
        Course course = new Course(title, description);
        teacherService.addCourse(course);
        List lessonsList = teacherService.getAllLessons();

        if (lessonsList.size() > 0) {
            mav.setViewName("setup-lessons");
            mav.addObject("title", title);
            mav.addObject("lessons",lessonsList);
        } else {
            mav.setViewName("course-menu");
            mav.addObject("message", "The course has been successfully created.");
        }
        return mav;
    }

    @RequestMapping(value = "/add-lesson")
    public ModelAndView addLesson(HttpServletRequest req, ModelAndView mav) throws NoSuchCourseException {
        List<Lesson> lessons = teacherService.getAllLessons();
        Course course=teacherService.findByTitleCourse(req.getParameter("title"));
        List<Lesson> list=course.getLessonList();
        for (Lesson lesson : lessons) {
            if (req.getParameter(lesson.getTitle())!=null){
                list.add(lesson);
            }
        }
        course.setLessonList(list);
        teacherService.updateCourse(course);
        //ModelAndView mav = new ModelAndView("course-menu");
        mav.setViewName("course-menu");
        mav.addObject("message", "The course has been successfully created.");
        return mav;
    }

    @RequestMapping(value = "/show-courses")
    public ModelAndView showCourse() {
        ModelAndView mav=new ModelAndView("list-courses");
        mav.addObject("courses",teacherService.getAllCourses());
        return mav;
    }

    @RequestMapping(value = "/show-course/{title}")
    public ModelAndView showCourse(@PathVariable String title) throws  NoSuchCourseException {
        ModelAndView mav=new ModelAndView("show-course");
        Course course=teacherService.findByTitleCourse(title);
        mav.addObject("course",course);
        mav.addObject("lessons",course.getLessonList());
        return mav;
    }

    @RequestMapping(value = "/delete-course")
    public ModelAndView deleteLessonForm() {
        return new ModelAndView("delete-course");
    }

    @RequestMapping(value = "/delete")
    public ModelAndView deleteCourse(HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        String courseTitle = req.getParameter("courseTitle");
        try {
            teacherService.deleteCourse(courseTitle);
            mav.addObject("message", "The course has been successfully deleted.");
            mav.setViewName("course-menu");
        } catch (NoSuchCourseException e) {
            mav.addObject("message", "There is no course with title: " + courseTitle);
            mav.setViewName("delete-course");
        }
        return mav;
    }







}
