package pages;

import helpers.ByFinder;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class BasePage {
    private Logger logger = LoggerFactory.getLogger(BasePage.class);

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
            element.findElement(By.xpath("//label[contains(text(), '" + elementToSelect + "')]")).click();
        }
    }

    public void jsScrollIntoViewElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void clickObject(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (TimeoutException e) {
            e.printStackTrace();
            logger.error("Element not found");
        }
        logger.info("Click on webelement {}", element);
    }

    public void sendKeysObject(WebElement webElement, String message) {
        waitForWebElementToBeVisible(webElement);
        webElement.clear();
        webElement.sendKeys(message);
        logger.info("Typed message {}", webElement.getText());
    }

    private void waitForWebElementToBeVisible(WebElement webElement) {
        logger.info("Start waiting for Webelement to be visible- Timeout set to 10 seconds");
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public void waitUntilElementIsVisible(WebElement element) {
        By byFromWebElement = new ByFinder().getByFromWebElement(element);
        wait.until(ExpectedConditions.visibilityOfElementLocated(byFromWebElement));
    }
}
