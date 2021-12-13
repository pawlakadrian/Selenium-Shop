package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenuPage extends BasePage{

    @FindBy(id = "_desktop_user_info")
    private WebElement signIn;

    @FindBy(css = ".account .hidden-sm-down")
    private WebElement verifyLoggedUserName;

    @FindBy(css = ".user-info .hidden-sm-down")
    private WebElement verifyUserIsLoggedOut;

    @FindBy(css = ".logout")
    private WebElement logout;

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
}
