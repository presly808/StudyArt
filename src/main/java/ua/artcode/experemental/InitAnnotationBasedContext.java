package ua.artcode.experemental;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by serhii on 11.11.15.
 */
public class InitAnnotationBasedContext {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("ua.artcode");
    }
}
