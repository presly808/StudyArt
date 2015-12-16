package ua.artcode.db;


import ua.artcode.model.common.AccountType;
import ua.artcode.model.common.User;
import ua.artcode.utils.ClassPathResourceLoader;
import ua.artcode.utils.serialization.Serializator;

import java.io.File;

public class InitUserProcessor {

    private UserContainer userContainer;
    private Serializator<UserContainer> serializator;

    // TODO dont use absolute path, use relative, add resource to classpath then get via getResource method
    final File userDbFile;
    //private String path = "/home/serhii/dev/tempVitalik/StudyArt/cache/app_db.txt";


    public InitUserProcessor(Serializator<UserContainer> serializator) {
        this.serializator = serializator;
        userDbFile = ClassPathResourceLoader.getFile("/user_db.txt");

    }

    private UserContainer initData(){
        userContainer = new UserContainer();
        userContainer.addUser(new User("Usename1", "password1", "email@test.com"));
        userContainer.addUser(new User("Usename2", "password2", "email2@test.com", AccountType.JIDAI));
        return userContainer;
    }

    public UserContainer getContainer() {
        if(userContainer == null){
            if(userDbFile.exists()){
                userContainer = serializator.load(userDbFile.getPath());
            } else {
                userContainer = initData();
                serializator.save(userDbFile.getPath(), userContainer);
            }
        }

        return userContainer;
    }

}

