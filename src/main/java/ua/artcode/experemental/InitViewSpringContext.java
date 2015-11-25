package ua.artcode.experemental;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.artcode.view.View;

/**
 * Created by serhii on 11.11.15.
 */
public class InitViewSpringContext {


    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("app-context.xml");
        View view = app.getBean(View.class);

    }
}
