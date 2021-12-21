package pages.automationPractice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.internal.EventFiringMouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;
import pages.WebListener;

public class MenuPage extends BasePage {
    Logger logger = LoggerFactory.getLogger(MenuPage.class);

    private EventFiringMouse eventFiringMouse;
    private WebListener webListener;

    @FindBy(css = ".sf-menu > li:first-child a")
    private WebElement menuOptionWomen;

    @FindBy(css = ".submenu-container li li:nth-child(2) a")
    private WebElement menuOptionBlouses;

    public MenuPage mouseClickOnBlouses() {
        logger.info("Click on: {}", menuOptionBlouses);
        clickObject(menuOptionBlouses);
        return this;
    }

    public MenuPage mouseHoverWomen() {
        logger.info("Mouse hover clothes link");
        mouseHover(menuOptionWomen);
        return this;
    }

    private void mouseHover(WebElement webElement) {
        eventFiringMouse = new EventFiringMouse(driver, webListener);
        Locatable item = (Locatable) webElement;
        Coordinates coordinates = item.getCoordinates();
        eventFiringMouse.mouseMove(coordinates);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public MenuPage(WebDriver driver) {
        super(driver);
    }
}
