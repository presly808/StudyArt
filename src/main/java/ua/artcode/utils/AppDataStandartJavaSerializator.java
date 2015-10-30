package ua.artcode.utils;


import java.io.*;

public class AppDataStandartJavaSerializator<V> implements Serializator<V> {


    @Override
    public void save(String path, V object) {
        // try with resources
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(object);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public V load(String path) {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            return (V) ois.readObject(); // BC -> class ->object
        } catch (IOException e) {        // reflection
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
