package ua.artcode.dao;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import ua.artcode.exception.AppException;
import ua.artcode.exception.NoSuchLessonException;
import ua.artcode.model.Lesson;
import ua.artcode.model.codingbat.Task;

import java.util.List;

/**
 * Created by Razer on 08.02.16.
 */
public class LessonDaoMongoImpl implements LessonDao {

    private Datastore datastore;

    public LessonDaoMongoImpl(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public Lesson add(Lesson lesson) throws AppException {
        if (!isExist(lesson.getTitle())) {
            datastore.save(lesson);
            return lesson;
        }
        throw new AppException("Lesson with title: "+lesson.getTitle()+" already exist");
    }

    @Override
    public void update(ObjectId id,Lesson lesson) throws NoSuchLessonException, AppException {
        delete(id);
        add(lesson);
    }

    @Override
    public List<Lesson> getAll() {
        return datastore.find(Lesson.class).asList();
    }
    //TODO
    @Override
    public void addTask(String title, Task task) throws NoSuchLessonException, AppException {
        Lesson lesson = find(title);
        List<Task> taskList = lesson.getTasks();
        taskList.add(task);
        lesson.setTasks(taskList);
        update(lesson.getId(),lesson);
    }

    @Override
    public boolean delete(String title) throws NoSuchLessonException {
        Lesson lesson = find(title);
        if (lesson != null) {
            datastore.delete(Lesson.class, lesson.getId());
            return true;
        } else
            return false;
    }

    @Override
    public boolean delete(ObjectId id) throws NoSuchLessonException {
        Lesson lesson = find(id);
        if (lesson != null) {
            datastore.delete(Lesson.class, id);
            return true;
        } else
            return false;
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
