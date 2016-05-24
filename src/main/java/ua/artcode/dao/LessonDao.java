package ua.artcode.dao;

import com.mongodb.DuplicateKeyException;
import org.bson.types.ObjectId;
import ua.artcode.exception.DuplicateDataException;
import ua.artcode.exception.NoSuchLessonException;
import ua.artcode.model.common.Lesson;
import ua.artcode.model.common.Task;

import java.util.List;


public interface LessonDao {

    void add(Lesson lesson) throws DuplicateKeyException;

    void update(ObjectId id,Lesson lesson) throws NoSuchLessonException, DuplicateDataException;

    List<Lesson> getAll();

    void addTask(String title, Task codingBatTask) throws NoSuchLessonException, DuplicateDataException;

    boolean delete(String title) throws NoSuchLessonException;

    boolean delete(ObjectId id) throws NoSuchLessonException;

    boolean isExist(String title);

    int size();

    Lesson find(String title) throws NoSuchLessonException;

    Lesson find(ObjectId id) throws NoSuchLessonException;

    List<Lesson> search(String keyWord, int offset, int length);

    long searchCount(String keyWord);
}
