package test.java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Lesson6 {
    WebDriver driver = null;

    @BeforeTest
    public void preconditions() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    public void logIn() {
        //WebElements
        WebElement username = driver.findElement(By.xpath("//input[@id='user-name']"));
        WebElement password = driver.findElement(By.xpath("//input[contains(@name,'password')]"));
        WebElement logInButton = driver.findElement(By.id("login-button"));

        //Actions
        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        logInButton.click();
    }

    @Test(dependsOnMethods = "logIn")
    public void checkTheCart() {
        //WebElements
        WebElement product = driver.findElement(By.xpath("//*[contains(text(),'Sauce Labs Bike Light')]"));
        WebElement price = driver.findElement(By.xpath("//div[text()='Sauce Labs Bike Light']/../../..//div[@class='inventory_item_price']"));
        WebElement addToCart = driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-bike-light' and @id='add-to-cart-sauce-labs-bike-light']"));
        WebElement shoppingCart = driver.findElement(By.cssSelector("#shopping_cart_container"));

        //expected results List
        List<String> expectedResult = new ArrayList<>() {{
            add(price.getText());
        }};

        //Actions
        addToCart.click();
        shoppingCart.click();

        //WebElements
        WebElement itemPrice = driver.findElement(By.className("inventory_item_price"));
        product = driver.findElement(By.xpath("//*[contains(text(),'Sauce Labs Bike Light')]"));
        //Results
        List<String> actualResult = new ArrayList<String>() {{
            add(itemPrice.getText());
        }};
        Assert.assertTrue(product.isDisplayed());
        Assert.assertEquals(expectedResult, actualResult);
    }

    @AfterTest
    public void postconditions() {
        driver.close();
        driver.quit();
    }
}