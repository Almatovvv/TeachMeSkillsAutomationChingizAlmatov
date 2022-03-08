package baseobjects;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;

import java.util.Locale;

public class DriverManager {
    private static WebDriver driver = null;

    public static WebDriver getDriver(String drivers) {
        if (driver == null) {
            driver = WebDriverManager.getInstance(DriverManagerType.valueOf(drivers.toUpperCase(Locale.ROOT))).create();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver(String drivers) {
        WebDriverManager.getInstance(DriverManagerType.valueOf(drivers.toUpperCase(Locale.ROOT))).quit();
    }
}
