package baseobjects;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

import static baseobjects.DriverManager.closeDriver;
import static baseobjects.DriverManager.getDriver;

public class BaseTest {
    protected WebDriver driver;
    protected ITestContext context;
    protected String currentDirectoryPath;
    protected String browserType;

    @BeforeTest
    public void precondition(ITestContext context) {
        this.context = context;
        this.browserType = context.getSuite().getParameter("browser") == null ? "CHROME" : context.getSuite().getParameter("browser");
        this.driver = getDriver(browserType);
        currentDirectoryPath = System.getProperty("user.dir");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    protected <T> T pageInit(Class<T> page) {
        return get(page, this.driver);
    }

    protected <T> T get(Class<T> page, WebDriver driver) {
        T instance = null;
        try {
            instance = page.getDeclaredConstructor(WebDriver.class).newInstance(driver);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return instance;
    }

    @AfterTest
    public void postcondition() {
        closeDriver(browserType);
    }
}
