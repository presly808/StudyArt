package ua.artcode.dao;

import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchLessonException;
import ua.artcode.model.Lesson;
import ua.artcode.model.codingbat.CodingBatTask;

import java.util.List;

/**
 * Created by Razer on 08.02.16.
 */
public interface LessonDao {

    Lesson addLesson(Lesson lesson) throws AppException;

    List<Lesson> getAll();

    void addTask(String title, CodingBatTask codingBatTask) throws NoSuchLessonException, AppException;

    boolean delete(String title) throws NoSuchLessonException;

    boolean isExist(String title);

    int size();

    void updateLesson(Lesson lesson) throws NoSuchLessonException, AppException;

    Lesson findByTitle(String title) throws NoSuchLessonException;
}
