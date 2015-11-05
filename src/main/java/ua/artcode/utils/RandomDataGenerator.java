package ua.artcode.utils;

import java.util.UUID;

/**
 * Created by serhii on 05.11.15.
 */
public class RandomDataGenerator {



    /**
     *
     * @return some random value ex "name randomNum"
     *
     * */
    public static String generateNameWith(String name, int numRange){
        return name + (int)(Math.random() * numRange);
    }

    public static String generateRandomId(){
        return UUID.randomUUID().toString();
    }
}
