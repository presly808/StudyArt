package ua.artcode.dao;

import org.mongodb.morphia.Datastore;
import ua.artcode.exception.NoSuchLessonException;
import ua.artcode.model.Lesson;
import ua.artcode.model.codingbat.CodingBatTask;

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
    public Lesson addLesson(Lesson lesson) {
        datastore.save(lesson);
        return lesson;
    }

    @Override
    //TODO think about ref
    public void updateLesson(Lesson lesson) throws NoSuchLessonException {
          delete(lesson.getTitle());
          addLesson(lesson);
    }

    @Override
    public List<Lesson> getAll() {
        return datastore.find(Lesson.class).asList();
    }

    @Override
    public void addTask(String title, CodingBatTask codingBatTask) throws NoSuchLessonException {
        Lesson lesson=findByTitle(title);
        List<CodingBatTask> taskList=lesson.getTasks();
        taskList.add(codingBatTask);
        updateLesson(lesson);
    }

    @Override
    public boolean delete(String title) throws NoSuchLessonException {
        Lesson lesson = findByTitle(title);
        if (lesson!= null) {
            datastore.delete(Lesson.class,lesson.getId());
            return true;
        } else
            return false;
    }

    @Override
    public int size() {
        return (int) datastore.getDB().getCollection("Lesson").count();
    }

    @Override
    public Lesson findByTitle(String title) throws NoSuchLessonException {
       Lesson lesson=datastore.find(Lesson.class,"title",title).get();
        if(lesson==null){
            throw new NoSuchLessonException("There is no lesson with title: "+title+"!");
        }
        return lesson;
    }



}
