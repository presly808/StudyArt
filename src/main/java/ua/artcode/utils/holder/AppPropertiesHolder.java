package ua.artcode.utils.holder;

import java.io.IOException;
import java.util.Properties;

public class AppPropertiesHolder {

    private final static Properties properties = initProperties();
    private final static String PROPERTY_FILE_PATH = "/app.properties";

    private synchronized static Properties initProperties() {
        Properties prop= new Properties();
        try {
            prop.load(AppPropertiesHolder.class.getResourceAsStream(PROPERTY_FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static String getProperty(String name){
        return properties.getProperty(name);
    }


}
