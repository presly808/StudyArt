package ua.artcode.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Razer on 01.12.15.
 */
public class SpringContext {

    private static ApplicationContext context = null;

    public static ApplicationContext getContext() {
        if (context == null) {
            context = new ClassPathXmlApplicationContext("classpath:app-context.xml");
        }
        return context;
    }
}
