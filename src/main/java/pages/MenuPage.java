package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.internal.EventFiringMouse;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MenuPage extends BasePage {
    Logger logger = LoggerFactory.getLogger(MenuPage.class);

    private EventFiringMouse eventFiringMouse;
    private WebListener webListener;

    @FindBy(id = "_desktop_user_info")
    private WebElement signIn;

    @FindBy(css = ".account .hidden-sm-down")
    private WebElement verifyLoggedUserName;

    @FindBy(css = ".user-info .hidden-sm-down")
    private WebElement verifyUserIsLoggedOut;

    @FindBy(css = ".logout")
    private WebElement logout;

    @FindBy(css = ".ui-autocomplete-input")
    private WebElement input;

    @FindBy(css = "#search_widget .material-icons.search")
    private WebElement searchBtn;

    @FindBy(css = "#category-3 > a")
    WebElement clothes;

    @FindBy(css = "#category-6 > a")
    WebElement accessories;

    @FindBy(css = "#category-9 > a")
    WebElement art;

    @FindBy(css = ".ui-menu-item .product")
    private WebElement searchHint;

    @FindBy(css = "#top-menu > li")
    private List<WebElement> categoryList;

    public MenuPage mouseHoverClothes() {
        logger.info("Mouse hover clothes link");
        mouseHover(clothes);
        return this;
    }

    public MenuPage mouseHoverAccessories() {
        logger.info("Mouse hover accessories link");
        mouseHover(accessories);
        return this;
    }

    public MenuPage mouseHoverArt() {
        logger.info("Mouse hover art link");
        mouseHover(art);
        return this;
    }

    public MenuPage goToArt() {
        logger.info("Go to art category");
        clickObject(art);
        return this;
    }

    private void mouseHover(WebElement webElement) {
        eventFiringMouse = new EventFiringMouse(driver, webListener);
        Locatable item = (Locatable) webElement;
        Coordinates coordinates = item.getCoordinates();
        eventFiringMouse.mouseMove(coordinates);

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public MenuPage(WebDriver driver) {
        super(driver);
    }

    public String verifyUserIsLogged() {
        return verifyLoggedUserName.getText();
    }

    public String verifyUserIsLoggedOut() {
        return verifyUserIsLoggedOut.getText();
    }

    public MenuPage logout() {
        this.logout.click();
        return this;
    }

    public MenuPage goToLoginPage() {
        signIn.click();
        return this;
    }

    public MenuPage sendKeyToInputAndSearch(String name) {
        input.sendKeys(name);
        searchBtn.click();
        return this;
    }

    public MenuPage sendKeyToInput(String name) {
        input.sendKeys(name);
        return this;
    }

    public String getHint() {
        wait.until(ExpectedConditions.visibilityOf(searchHint));
        return searchHint.getText();
    }

    public List<WebElement> getCategories() {
        return categoryList;
    }
}
