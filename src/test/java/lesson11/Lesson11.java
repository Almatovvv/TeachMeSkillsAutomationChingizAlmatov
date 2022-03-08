package lesson11;

import baseobjects.BaseTest;
import io.qameta.allure.*;

import org.testng.annotations.Test;

public class Lesson11 extends BaseTest {


    @Test(description = "Test", priority = 1)
    @Description("Test")
    @Step("1. First step")
    @Link("https://instagram.com/")
    @Issue("COVID-19")
    @TmsLink("COVID-19")
    public void checkboxesAllChecked_Test() {
        System.out.println("test1");
    }


    @Test(description = "Test2", priority = 2)
    @Description("Test2")
    @Step("2. Second step")
    @Link("https://instagram.com/")
    @Issue("COVID-19")
    @TmsLink("COVID-19")
    public void checkboxesAllUnchecked_Test() {
        System.out.println("test2");
    }


    @Test(description = "Test3", priority = 3)
    @Description("Test3")
    @Step("3. Third step")
    @Link("https://instagram.com/")
    @Issue("COVID-19")
    @TmsLink("COVID-19")
    public void checkboxesAllOneChecked_Test() {
        System.out.println("test3");
    }
}
