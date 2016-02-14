package ua.artcode.dao;

import ua.artcode.exception.NoSuchLessonException;
import ua.artcode.model.Lesson;
import ua.artcode.model.codingbat.CodingBatTask;

import java.util.List;

/**
 * Created by Razer on 08.02.16.
 */
public interface LessonDao {

    Lesson addLesson(Lesson lesson);

    List<Lesson> getAll();

    void addTask(String title, CodingBatTask codingBatTask) throws NoSuchLessonException;

    boolean delete(String title) throws NoSuchLessonException;

    int size();

    void updateLesson(Lesson lesson) throws NoSuchLessonException;

    Lesson findByTitle(String title) throws NoSuchLessonException;
}
