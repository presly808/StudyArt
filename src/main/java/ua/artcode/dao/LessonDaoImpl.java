package ua.artcode.dao;

import org.mongodb.morphia.Datastore;
import ua.artcode.exception.NoSuchLessonException;
import ua.artcode.model.Lesson;

import java.util.List;

/**
 * Created by Razer on 08.02.16.
 */
public class LessonDaoImpl implements LessonDao {

    private Datastore datastore;

    public LessonDaoImpl(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public Lesson addLesson(Lesson lesson) {
        datastore.save(lesson);
        return lesson;
    }

    @Override
    public List<Lesson> showAll() {
        return datastore.find(Lesson.class).asList();
    }

    @Override
    public boolean delete(String name) throws NoSuchLessonException {
        Lesson lesson = findByName(name);
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
    public Lesson findByName(String name) throws NoSuchLessonException {
       Lesson lesson=datastore.find(Lesson.class,"name",name).get();
        if(lesson==null){
            throw new NoSuchLessonException("There is no lesson with name: "+name+"!");
        }
        return lesson;
    }

}
