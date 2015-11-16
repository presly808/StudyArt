package ua.artcode.experemental;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.artcode.model.ImplementedTask;
import ua.artcode.model.codingbat.CodingBatTask;

public class InitAppContext {


    public static void main(String[] args) {
        ApplicationContext springContext =
                new ClassPathXmlApplicationContext("app-context.xml");

        ImplementedTask res = springContext.getBean(ImplementedTask.class);

        CodingBatTask task1 = (CodingBatTask) springContext.getBean("myTask1");
        CodingBatTask task12 = springContext.getBean("myTask1",CodingBatTask.class);

        System.out.println(res);

    }
}
