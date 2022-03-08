package pageobject;

import baseobjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutSecondStepPage extends BasePage {
    public CheckoutSecondStepPage(WebDriver driver) {
        super(driver);
    }

    private By header = By.xpath("//span[text()='Checkout: Overview']");
    private By cancelButton = By.id("cancel");
    private By finishButton = By.id("finish");

    public CheckoutSecondStepPage assertExistenceOfAProduct(String productName) {
        driver.findElement(By.xpath("//*[text()='" + productName + "']")).isDisplayed();
        return this;
    }

    public CheckoutSecondStepPage finishPurchase() {
        driver.findElement(header).isDisplayed();
        driver.findElement(finishButton).click();
        return this;
    }
}
