package test.by.epam.ft.action;

import by.epam.ft.action.ParamsValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ParamsValidatorTest {
    private String expectedResult;
    private String sourceString;
    private String actualResult;

    @BeforeTest
    public void beforeTest(){
        sourceString = "<script>alert('hello')</script>";
        expectedResult = " script alert('hello') /script ";
    }

    @Test
    public void validateParamsTest() {
        actualResult = ParamsValidator.validateParams(sourceString);
        Assert.assertEquals(actualResult, expectedResult);
    }

}
