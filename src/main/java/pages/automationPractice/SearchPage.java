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

public class SearchPage extends BasePage {
    Logger logger = LoggerFactory.getLogger(MenuPage.class);

    private EventFiringMouse eventFiringMouse;
    private WebListener webListener;

    @FindBy (css = ".product-image-container .replace-2x")
    private WebElement womenInBlack;

    @FindBy (css = ".product-image-container .quick-view")
    private WebElement quickView;

    public SearchPage mouseHoverWomen() {
        logger.info("Mouse hover on women in black on search page");
        jsScrollIntoViewElement(womenInBlack);
        mouseHover(womenInBlack);
        return this;
    }

    public SearchPage clickOnQuickView() {
        logger.info("Click on thumbnail quick view");
        clickObject(quickView);
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

    public SearchPage(WebDriver driver) {
        super(driver);
    }
}
