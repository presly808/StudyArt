package ua.artcode.dao;

import com.mongodb.DuplicateKeyException;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchLessonException;
import ua.artcode.model.common.Lesson;
import ua.artcode.model.codingbat.Task;

import java.util.List;

/**
 * Created by Razer on 08.02.16.
 */
public class LessonDaoMongoImpl implements LessonDao {

    private Datastore datastore;

    public LessonDaoMongoImpl(Datastore datastore) {
        this.datastore = datastore;
        datastore.ensureIndexes();
    }

    @Override
    public void add(Lesson lesson) throws DuplicateKeyException {
        datastore.save(lesson);
    }

    @Override
    public void update(ObjectId id, Lesson lesson) throws NoSuchLessonException, AppException {
        delete(id);
        add(lesson);
    }

    @Override
    public List<Lesson> getAll() {
        return datastore.find(Lesson.class).asList();
    }

    @Override
    public void addTask(String title, Task task) throws NoSuchLessonException, AppException {
        Lesson lesson = find(title);
        List<Task> taskList = lesson.getTasks();
        taskList.add(task);
        lesson.setTasks(taskList);
        update(lesson.getId(), lesson);
    }

    @Override
    public boolean delete(String title) throws NoSuchLessonException {
        Query<Lesson> query = datastore.createQuery(Lesson.class);
        query.field("title").equal(title);
        Lesson lesson = datastore.findAndDelete(query);
        if (lesson == null) {
            throw new NoSuchLessonException("There is no lesson with title: "+title);
        }
        return true;
    }

    @Override
    public boolean delete(ObjectId id) throws NoSuchLessonException {
        Query<Lesson> query = datastore.createQuery(Lesson.class);
        query.field("id").equal(id);
        Lesson lesson=datastore.findAndDelete(query);
        if (lesson == null) {
            throw new NoSuchLessonException("No lesson");
        }
        return true;
    }

    @Override
    public int size() {
        return (int) datastore.getDB().getCollection("Lesson").count();
    }

    @Override
    public boolean isExist(String title) {
        Lesson existLesson = datastore.find(Lesson.class).field("title").equal(title).get();
        if (existLesson == null) {
            return false;
        }
        return true;
    }

    @Override
    public Lesson find(String title) throws NoSuchLessonException {
        Lesson lesson = datastore.find(Lesson.class, "title", title).get();
        if (lesson == null) {
            throw new NoSuchLessonException("There is no lesson with title: " + title + "!");
        }
        return lesson;
    }

    @Override
    public Lesson find(ObjectId id) throws NoSuchLessonException {
        Lesson lesson = datastore.find(Lesson.class, "id", id).get();
        if (lesson == null) {
            throw new NoSuchLessonException("There is no lesson with id: " + id + "!");
        }
        return lesson;
    }
}
