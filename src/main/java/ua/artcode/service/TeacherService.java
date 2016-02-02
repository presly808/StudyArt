package ua.artcode.service;

/**
 * Created by Razer on 02.02.16.
 */
public interface TeacherService {

    void createLesson();

    void createCourse();

    String showCourse();

    String showLessons();

    boolean deleteLesson();
}
