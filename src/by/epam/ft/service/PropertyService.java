package by.epam.ft.service;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertyService {

    private static final Logger logger = Logger.getLogger(PropertyService.class);

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
            logger.error("File " + path + " not found", e);
        } catch (IOException e) {
            logger.error("Cannot load input stream", e);
        }
    }

    public static String getPath() {
        return path;
    }

    public static Properties getProperties() {
        return properties;
    }
}
