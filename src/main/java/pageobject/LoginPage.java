package pageobject;

import baseobjects.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private By logo = By.className("login_logo");
    private By userNameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By loginError = By.xpath("//h3[contains(text(), 'Username and password do not match')]");


    public LoginPage open(String url) {
        super.open(url);
        return this;
    }

    public LoginPage login(String username, String password) {
        driver.findElement(logo).isDisplayed();
        driver.findElement(userNameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
        return this;
    }

    public LoginPage assertLoginError() {
        Assert.assertTrue(driver.findElement(loginError).isDisplayed());
        return this;
    }

}
