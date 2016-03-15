package ua.artcode.dao;

import com.mongodb.DuplicateKeyException;
import org.bson.types.ObjectId;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchLessonException;
import ua.artcode.model.common.Lesson;
import ua.artcode.model.codingbat.Task;

import java.util.List;

/**
 * Created by Razer on 08.02.16.
 */
public interface LessonDao {

    void add(Lesson lesson) throws DuplicateKeyException;

    List<Lesson> getAll();

    void addTask(String title, Task codingBatTask) throws NoSuchLessonException, AppException;

    boolean delete(String title) throws NoSuchLessonException;

    boolean delete(ObjectId id) throws NoSuchLessonException;

    boolean isExist(String title);

    int size();

    void update(ObjectId id,Lesson lesson) throws NoSuchLessonException, AppException;

    Lesson find(String title) throws NoSuchLessonException;

    Lesson find(ObjectId id) throws NoSuchLessonException;

}
