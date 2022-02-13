package lesson6;

import baseobjects.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Lesson6 extends BaseTest {

    @Test
    public void logIn() {
        driver.get("https://www.saucedemo.com/");
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
        product = driver.findElement(By.xpath("//*[contains(text(),'Bike Light')]"));
        //Results
        List<String> actualResult = new ArrayList<String>() {{
            add(itemPrice.getText());
        }};
        Assert.assertTrue(product.isDisplayed());
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test(dependsOnMethods = "logIn")
    public void checkTheCart2() {
        //WebElements
        WebElement product = driver.findElement(By.xpath("//img[@alt='Sauce Labs Backpack']"));
        WebElement price = driver.findElement(By.xpath("//div[text()='29.99']"));
        WebElement addToCart = driver.findElement(By.xpath("//button[contains(@data-test,'labs-backpack')]"));
        WebElement shoppingCart = driver.findElement(By.xpath("//*[contains(text(),'Bike Light')]/ancestor::a"));
        WebElement followingXpath = driver.findElement(By.xpath("//div[@class='inventory_item']/following::div"));
        WebElement cssClassLocator = driver.findElement(By.cssSelector(".inventory_item_img"));
        WebElement cssMultipleClassLocator = driver.findElement(By.cssSelector(".inventory_item .inventory_item_img"));
        WebElement cssMultipleClassLocator2 = driver.findElement(By.cssSelector(".inventory_container.inventory_container"));
        WebElement cssTagNameLocator = driver.findElement(By.cssSelector("Sauce Labs Backpack"));
        WebElement cssTagNameAndClassLocator = driver.findElement(By.cssSelector("div.page_wrapper"));
        WebElement cssAttributeValueLocator = driver.findElement(By.cssSelector("[id=header_container]"));
        WebElement cssAttributeValueContainsLocator = driver.findElement(By.cssSelector("[class~=inventory_item_label]"));
        WebElement cssAttributeValueLocator2 = driver.findElement(By.cssSelector("[id|=header_container]"));
        WebElement cssAttributeValueStartsWithLocator = driver.findElement(By.cssSelector("[class^=pric]"));
        WebElement cssAttributeValueEndsWithLocator = driver.findElement(By.cssSelector("[class$=bar]"));

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
}