package pageobject;

import baseobjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CheckoutCompletePage extends BasePage {
    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    private By header = By.xpath("//span[text()='Checkout: Complete!']");
    private By backToHomeButton = By.id("back-to-products");

    public void assertTheEndOfPurchase() {
        Assert.assertTrue(driver.findElement(header).isDisplayed());
    }
}
