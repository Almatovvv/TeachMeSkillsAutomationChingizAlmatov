package lesson8;

import baseobjects.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Lesson8 extends BaseTest {

    @Test
    void Lesson8 (){
        driver.get(currentDirectoryPath + "\\src\\test\\java\\lesson8\\localPage.html");
        List<WebElement> list = driver.findElements(By.xpath("//td[1]"));
        for (WebElement el:list) {
            System.out.println(el.getText());
        }
        driver.findElement(By.id("bpl")).sendKeys("Java");
        driver.findElement(By.cssSelector("#oc_java")).click();
        driver.findElement(By.xpath("//button[text()='Answer']")).click();
        WebElement dropdownElement = driver.findElement(By.xpath("//select[@id='pl']"));
        Select select = new Select(dropdownElement);
        select.selectByVisibleText("The best OOP language");
        driver.findElement(By.xpath("//button[text()='Off we go!']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//h2[text()='Hello world!']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//h3[contains(text(),'with different programming languages ')]")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.xpath("//p")).getText(), "Little survey to find the best programming language");
        driver.findElement(By.tagName("a")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://en.wikipedia.org/wiki/%22Hello,_World!%22_program");
    }
}
