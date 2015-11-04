package ua.artcode.db;


import ua.artcode.model.AccountType;
import ua.artcode.model.UserAccount;
import ua.artcode.utils.Serializator;

import java.io.File;

public class InitUserProcessor {

    private UserAccountContainer userContainer;
    private Serializator<UserAccountContainer> serializator;

    // TODO dont use absolute path, use relative, add resource to classpath then get via getResource method
    final File f = new File(InitDataProcessor.class.getProtectionDomain().getCodeSource().getLocation().getPath());
    String study_art_dir = absoluteParentPath(f.getPath(), 3);
    String default_db = "/cache/user_db.txt";
    private String path = study_art_dir.concat(default_db);
    //private String path = "/home/serhii/dev/tempVitalik/StudyArt/cache/app_db.txt";


    public InitUserProcessor(Serializator<UserAccountContainer> serializator) {
        this.serializator = serializator;
    }

    private UserAccountContainer initData(){
        userContainer = new UserAccountContainer();
        userContainer.addUser(new UserAccount("Usename1", "password1", "email@test.com"));
        userContainer.addUser(new UserAccount("Usename2", "password2", "email2@test.com", AccountType.JIDAI));
        return userContainer;
    }

    public UserAccountContainer getContainer() {
        if(userContainer == null){
            if(new File(path).exists()){
                userContainer = serializator.load(path);
            } else {
                userContainer = initData();
                serializator.save(path, userContainer);
            }
        }

        return userContainer;
    }

    public static String absoluteParentPath(String path, int levelUp) {
        if (levelUp-- > 0 && levelUp < 5) {
            String parentDir = new File(path).getParent();
            return absoluteParentPath(parentDir, levelUp);
        }
        return path;
    }
}

