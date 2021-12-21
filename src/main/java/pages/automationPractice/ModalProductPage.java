package pages.automationPractice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.internal.EventFiringMouse;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;
import pages.WebListener;

public class ModalProductPage extends BasePage {
    Logger logger = LoggerFactory.getLogger(MenuPage.class);

    private EventFiringMouse eventFiringMouse;
    private WebListener webListener;

    @FindBy(id = "thumb_5")
    private WebElement firstPick;

    @FindBy(id = "thumb_6")
    private WebElement secondPick;

    @FindBy(id = "thumb_7")
    private WebElement thirdPick;

    @FindBy(css = ".fancybox-inner > iframe")
    private WebElement iframe;

    public ModalProductPage mouseHoverFirstPick() {
        logger.info("Mouse hover on women in black on search page");
        jsScrollIntoViewElement(firstPick);
        mouseHover(firstPick);
        return this;
    }

    public ModalProductPage mouseHoverSecondPick() {
        logger.info("Mouse hover on women in black on search page");
        jsScrollIntoViewElement(secondPick);
        mouseHover(secondPick);
        return this;
    }

    public ModalProductPage mouseHoverThirdPick() {
        logger.info("Mouse hover on women in black on search page");
        jsScrollIntoViewElement(thirdPick);
        mouseHover(thirdPick);
        return this;
    }

    private void mouseHover(WebElement webElement) {
        eventFiringMouse = new EventFiringMouse(driver, webListener);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        Locatable item = (Locatable) webElement;
        Coordinates coordinates = item.getCoordinates();
        eventFiringMouse.mouseMove(coordinates);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ModalProductPage(WebDriver driver) {
        super(driver);
    }

    public ModalProductPage switchToIframe() {
        wait.until(ExpectedConditions.visibilityOf(iframe));

        driver.switchTo().frame(iframe);
        return this;
    }
}
