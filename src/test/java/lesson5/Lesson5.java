package lesson5;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Lesson5 {
    WebDriver driver;

    @BeforeTest
    public void beforeTest(){
        System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\src\\test\\resources\\driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }
    @AfterTest
    public void afterTest(){
        driver.quit();
    }

    @Test(dataProvider = "data")
    public void calculateLaminate(String roomWidthVal, String roomHeightVal, String lamWidthVal, String lamHeightVal, String peacesInPackVal, String priceForSqMVal, List<String> expectedData) {
        driver.get("https://masterskayapola.ru/kalkulyator/laminata.html");
        //Web Element
        WebElement roomWidth = driver.findElement(By.name("calc_roomwidth"));
        WebElement roomHeight = driver.findElement(By.name("calc_roomheight"));
        WebElement lamWidth = driver.findElement(By.name("calc_lamwidth"));
        WebElement lamHeight = driver.findElement(By.name("calc_lamheight"));
        WebElement peacesInPack = driver.findElement(By.name("calc_inpack"));
        WebElement priceForSqM = driver.findElement(By.name("calc_price"));
        WebElement countBtn = driver.findElement(By.xpath("//input[@value='Рассчитать']"));
        //Actions
        roomWidth.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        roomWidth.sendKeys(roomWidthVal);
        roomHeight.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        roomHeight.sendKeys(roomHeightVal);
        lamWidth.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        lamWidth.sendKeys(lamWidthVal);
        lamHeight.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        lamHeight.sendKeys(lamHeightVal);
        peacesInPack.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        peacesInPack.sendKeys(peacesInPackVal);
        priceForSqM.sendKeys(Keys.CONTROL + "a", Keys.DELETE);
        priceForSqM.sendKeys(priceForSqMVal);
        countBtn.click();

        List<WebElement> results = driver.findElements(By.xpath("//div[contains(@class, 'whiteback')]/div"));
        List<String> actualData = new ArrayList<String>() {{
            results.forEach((element) -> add(element.getText()));
        }};
        Assert.assertEquals(actualData, expectedData);
    }

    @DataProvider(name = "data")
    private Object[][] getData() {
        return new Object[][]{
                {"3", "5", "1000", "200", "20", "500", new ArrayList<String>() {{
                    add("РЕЗУЛЬТАТ РАСЧЕТА:");
                    add("Площадь укладки: 14.84 м2.");
                    add("Кол-во панелей: 76 шт.");
                    add("Кол-во упаковок: 4 шт.");
                    add("Стоимость: 8000 руб.");
                    add("Остатки: 4 шт.");
                    add("Отрезки: 11 шт.");
                }}},
                {"10", "10", "500", "1000", "5", "1200", new ArrayList<String>() {{
                    add("РЕЗУЛЬТАТ РАСЧЕТА:");
                    add("Площадь укладки: 99.60 м2.");
                    add("Кол-во панелей: 201 шт.");
                    add("Кол-во упаковок: 41 шт.");
                    add("Стоимость: 123000 руб.");
                    add("Остатки: 4 шт.");
                    add("Отрезки: 7 шт.");
                }}},
                {"8", "3", "699", "345", "16", "1199", new ArrayList<String>() {{
                    add("РЕЗУЛЬТАТ РАСЧЕТА:");
                    add("Площадь укладки: 23.78 м2.");
                    add("Кол-во панелей: 104 шт.");
                    add("Кол-во упаковок: 7 шт.");
                    add("Стоимость: 32384 руб.");
                    add("Остатки: 8 шт.");
                    add("Отрезки: 5 шт.");
                }}}
        };
    }
}
