package pageobject;

import baseobjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    private By header = By.xpath("//span[text()='Products']");
    private By sortProductsByField = By.className("product_sort_container");
    private By cartButton = By.className("shopping_cart_link");
    private By burgerMenuButton = By.id("react-burger-menu-btn");


    public HomePage addProductToCart(String productName) {
        driver.findElement(header).isDisplayed();
        driver.findElement(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//*[text()='Add to cart']")).click();
        return this;
    }

    public HomePage deleteProductFromCart(String productName) {
        driver.findElement(header).isDisplayed();
        driver.findElement(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//*[text()='Remove']")).click();
        return this;
    }

    public HomePage assertHeaderExist() {
        Assert.assertTrue(driver.findElement(header).isDisplayed());
        return this;
    }

    public HomePage assertProductAddedToCart(String productName) {
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//*[text()='Remove']")).isDisplayed());
        return this;
    }

    public HomePage assertProductDeletedFromCart(String productName) {
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='" + productName + "']/ancestor::div[@class='inventory_item']//*[text()='Add to cart']")).isDisplayed());
        return this;
    }

    public HomePage openCart() {
        driver.findElement(cartButton).click();
        return this;
    }

}
