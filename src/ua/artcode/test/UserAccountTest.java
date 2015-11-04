package ua.artcode.test;

import ua.artcode.dao.SimpleUserDaoImpl;
import ua.artcode.dao.SimpleUserDao;
import ua.artcode.db.CodingBatTaskContainer;
import ua.artcode.db.InitDataProcessor;
import ua.artcode.db.InitUserProcessor;
import ua.artcode.db.UserAccountContainer;
import ua.artcode.exception.AppException;
import ua.artcode.model.CodingBatTask;
import ua.artcode.service.SimpleAuthenticationService;
import ua.artcode.service.SimpleAuthenticationServiceImpl;
import ua.artcode.utils.AppDataStandartJavaSerializator;
import ua.artcode.utils.Serializator;

import java.util.List;
import java.util.Set;

public class UserAccountTest {
    public static void main(String[] args) {
        Serializator<UserAccountContainer> serializator = new AppDataStandartJavaSerializator<>();
        InitUserProcessor userProcessor = new InitUserProcessor(serializator);
        UserAccountContainer userAccountContainer = userProcessor.getContainer();

        SimpleAuthenticationService authenticationService =
                new SimpleAuthenticationServiceImpl(new SimpleUserDaoImpl(userAccountContainer));


        Set<String> userList = authenticationService.getAllUser();
        userList.stream().forEach(System.out::println);
        // java 8, streams, lambda -> (reference to method)


        try {
            System.out.println(authenticationService.registrate("Username3", "pass", "blabal@com"));
        } catch (AppException e) {
            e.printStackTrace();
        }

        Set<String> userList2 = authenticationService.getAllUser();
        userList2.stream().forEach(System.out::println);

        try {
            System.out.println(authenticationService.registrate("Username3", "pass", "blabal@com"));
        } catch (AppException e) {
            System.out.println("catch Exception");
            e.printStackTrace();
        }

        Set<String> userList3 = authenticationService.getAllUser();
        userList3.stream().forEach(System.out::println);


    }
}
