package lesson4;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Lesson4 {
    WebDriver driver;

    @BeforeTest
    public void beforeTest(){
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\src\\test\\resources\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
//        System.setProperty()
    }
    @AfterTest
    public void afterTest(){
        driver.quit();
    }

    @Test()
    public void helloWorld(){
        driver.get("https://www.google.by/");
        driver.findElement(By.name("q")).sendKeys("Привет, мир");
        driver.findElement(By.name("btnK")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Привет, Мир!']")).isDisplayed());
    }

    @Test
    public void searchTest(){
        driver.get("https://www.google.by/");
        driver.findElement(By.name("q")).sendKeys("*//*" + Keys.ENTER);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='res']//p")).getText(), "Your search - *//* - did not match any documents.");
    }
}
