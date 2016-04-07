package ua.artcode.dao;

import com.mongodb.DuplicateKeyException;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import ua.artcode.exception.DuplicateDataException;
import ua.artcode.exception.NoSuchLessonException;
import ua.artcode.model.common.Lesson;
import ua.artcode.model.common.Task;

import java.util.List;

/**
 * Created by Razer on 08.02.16.
 */
public class LessonDaoMongoImpl implements LessonDao {

    private Datastore datastore;
    private static final Logger LOG = Logger.getLogger(LessonDaoMongoImpl.class);

    public LessonDaoMongoImpl(Datastore datastore) {
        this.datastore = datastore;
        datastore.ensureIndexes(Lesson.class);
        LOG.debug("LessonDaoMongoImpl instance has been created.");
    }


    @Override
    public void add(Lesson lesson) throws DuplicateKeyException {
        datastore.save(lesson);
        LOG.info("The new lesson has been added to DB.");
    }

    @Override
    public void addTask(String title, Task task) throws NoSuchLessonException, DuplicateDataException {
        Lesson lesson = find(title);
        List<Task> taskList = lesson.getTasks();
        taskList.add(task);
        lesson.setTasks(taskList);
        update(lesson.getId(), lesson);
    }

    @Override
    public void update(ObjectId id, Lesson lesson) throws NoSuchLessonException, DuplicateDataException {
        Lesson oldLesson = find(id);
        try {
            delete(id);
            add(lesson);
        } catch (DuplicateKeyException e) {
            add(oldLesson);
            throw new DuplicateDataException("Lesson with title: "+lesson.getTitle()+" is already exist");
        }
        LOG.info("The lesson has been updated.");
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
    public Lesson find(ObjectId id) throws NoSuchLessonException {
        Lesson lesson = datastore.find(Lesson.class, "id", id).get();
        if (lesson == null) {
            LOG.warn("The lesson has been not founded. By id: " + id);
            throw new NoSuchLessonException("The lesson has been not founded.");
        }
        return lesson;
    }

    @Override
    public Lesson find(String title) throws NoSuchLessonException {
        Lesson lesson = datastore.find(Lesson.class, "title", title).get();
        if (lesson == null) {
            LOG.debug("The lesson has been not founded. By title: " + title);
            throw new NoSuchLessonException("There is no lesson with title: " + title);
            //TODO
            //throw new NoSuchLessonException(ResourceBundle.getBundle("lesson.not.found").toString());
        }
        return lesson;
    }

    @Override
    public boolean delete(ObjectId id) throws NoSuchLessonException {
        Query<Lesson> query = datastore.createQuery(Lesson.class);
        query.field("id").equal(id);
        Lesson lesson = datastore.findAndDelete(query);
        if (lesson == null) {
            LOG.warn("The lesson has not been deleted.");
            throw new NoSuchLessonException("The lesson is not found.");
        }
        LOG.info("The lesson has been deleted.");
        return true;
    }

    @Override
    public boolean delete(String title) throws NoSuchLessonException {
        Query<Lesson> query = datastore.createQuery(Lesson.class);
        query.field("title").equal(title);
        Lesson lesson = datastore.findAndDelete(query);
        if (lesson == null) {
            LOG.debug("The lesson has not been deleted.");
            throw new NoSuchLessonException("There is no lesson with title: " + title);
        }
        LOG.info("The lesson has been deleted. Title: " + title);
        return true;
    }

    @Override
    public int size() {
        return (int) datastore.getDB().getCollection("Lesson").count();
    }

    @Override
    public List<Lesson> getAll() {
        return datastore.find(Lesson.class).asList();
    }

}
