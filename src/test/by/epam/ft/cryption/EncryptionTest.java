package test.by.epam.ft.cryption;

import by.epam.ft.cryption.Encryption;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class EncryptionTest {
    private String expectedResult;
    private String sourceString;
    private String actualResult;

    @BeforeTest
    public void beforeTest(){
        sourceString = "Its test for encryption";
        expectedResult = "SXRzIHRlc3QgZm9yIGVuY3J5cHRpb24=";
    }

    @Test
    public void validateParamsTest() {
        actualResult = Encryption.encrypt(sourceString);
        Assert.assertEquals(actualResult, expectedResult);
    }
}
