package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.charset.Charset;
import java.time.Duration;
import java.util.*;

public class BasePage {
    public BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        actions = new Actions(driver);
        rnd = new Random();
    }

    public WebDriver driver;
    public WebDriverWait wait;
    public Actions actions;
    public Random rnd;

    public WebElement getRandomElement(List<WebElement> elements) {
        return elements.get(rnd.nextInt(elements.size()));
    }

    public int getRandomInt(List<WebElement> elements) {
        return rnd.nextInt(elements.size());
    }

    public void getOptionWithString(List<WebElement> elements, String elementToSelect) {
        for (WebElement element : elements) {
            element.findElement(By.xpath("//label[contains(text(), '"+elementToSelect+"')]")).click();
        }
    }
}
