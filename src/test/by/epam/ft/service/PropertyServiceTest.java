package test.by.epam.ft.service;

import by.epam.ft.service.PropertyService;
import org.testng.annotations.Test;

public class PropertyServiceTest {

    @Test
    public void constructorTest() {
        String path = "/D:/programming/Tomcat 9.0/webapps/FinalTaskProject/target/classes/config.properties";

        assert PropertyService.getPath().equals(path);
    }
}
