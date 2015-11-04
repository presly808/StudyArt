package ua.artcode.db;


import com.sun.org.apache.bcel.internal.util.ClassPath;
import ua.artcode.model.AccountType;
import ua.artcode.model.UserAccount;
import ua.artcode.utils.ClassPathResourceLoader;
import ua.artcode.utils.Serializator;

import java.io.File;

public class InitUserProcessor {

    private UserAccountContainer userContainer;
    private Serializator<UserAccountContainer> serializator;

    // TODO dont use absolute path, use relative, add resource to classpath then get via getResource method
    final File userDbFile;
    //private String path = "/home/serhii/dev/tempVitalik/StudyArt/cache/app_db.txt";


    public InitUserProcessor(Serializator<UserAccountContainer> serializator) {
        this.serializator = serializator;
        userDbFile = ClassPathResourceLoader.getFile("/user_db.txt");

    }

    private UserAccountContainer initData(){
        userContainer = new UserAccountContainer();
        userContainer.addUser(new UserAccount("Usename1", "password1", "email@test.com"));
        userContainer.addUser(new UserAccount("Usename2", "password2", "email2@test.com", AccountType.JIDAI));
        return userContainer;
    }

    public UserAccountContainer getContainer() {
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

