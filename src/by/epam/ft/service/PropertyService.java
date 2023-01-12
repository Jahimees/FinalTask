package by.epam.ft.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyService {


    private static String path;
    private static Properties properties;

    private PropertyService() {}

    static {
        path = PropertyService.class
                .getClassLoader()
                .getResource("config.properties").getPath()
                .replaceAll("%20", " ");
        try {
            InputStream inputStream = new FileInputStream(path);
            properties = new Properties();
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    public static String getPath() {
        return path;
    }

    public static Properties getProperties() {
        return properties;
    }
}
