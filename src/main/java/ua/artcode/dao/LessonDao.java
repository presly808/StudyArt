package ua.artcode.dao;

import ua.artcode.exception.NoSuchLessonException;
import ua.artcode.model.Lesson;

import java.util.List;

/**
 * Created by Razer on 08.02.16.
 */
public interface LessonDao {

    Lesson addLesson(Lesson lesson);

    List<Lesson> showAll();

    boolean delete(String name) throws NoSuchLessonException;

    int size();

    Lesson findByName(String name) throws NoSuchLessonException;
}
