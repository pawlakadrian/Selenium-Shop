package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage extends BasePage {
    Logger logger = LoggerFactory.getLogger(LoginPage.class);

    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(id = "submit-login")
    private WebElement signInButton;

    @FindBy(css = ".no-account a")
    private WebElement createAccountButton;

    @FindBy(css = ".alert")
    private WebElement alert;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage setEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    public LoginPage setPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    public LoginPage signIn() {
        signInButton.click();
        return this;
    }

    public LoginPage goToCreateAccount() {
        createAccountButton.click();
        return this;
    }

    public String loginFailMsg() {
        String alert = this.alert.getText();
        logger.info("Message when login is failed: {}", alert);
        return alert;
    }
}
