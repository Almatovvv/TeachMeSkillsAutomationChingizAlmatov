package pageobject;

import baseobjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    private By header = By.xpath("//span[text()='Your Cart']");
    private By continueShoppingButton = By.cssSelector("#continue-shopping");
    private By checkoutButton = By.id("checkout");

    public CartPage goToCheckout() {
        driver.findElement(header).isDisplayed();
        driver.findElement(checkoutButton).click();
        return this;
    }
}
