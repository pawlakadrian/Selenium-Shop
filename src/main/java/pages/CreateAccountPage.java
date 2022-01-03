package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

public class CreateAccountPage extends BasePage {
    Logger logger = LoggerFactory.getLogger(CreateAccountPage.class);

    @FindBy(name = "firstname")
    private WebElement firstName;

    @FindBy(name = "lastname")
    private WebElement lastName;

    @FindBy(name = "email")
    private WebElement email;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(name = "customer_privacy")
    private WebElement customerPrivacy;

    @FindBy(name = "psgdpr")
    private WebElement psgdpr;

    @FindBy(css = ".form-control-submit")
    private WebElement save;

    @FindBy(css = ".form-footer button")
    private WebElement continueBtn;

    public CreateAccountPage(WebDriver driver) {
        super(driver);
    }

    public CreateAccountPage setFirstName(String firstName) {
        this.firstName.sendKeys(firstName);
        return this;
    }

    public CreateAccountPage setLastName(String lastName) {
        this.lastName.sendKeys(lastName);
        return this;
    }

    public CreateAccountPage setEmail(String email) {
        this.email.sendKeys(email);
        return this;
    }

    public CreateAccountPage setPassword(String password) {
        this.password.sendKeys(password);
        return this;
    }

    public CreateAccountPage setCutomerPrivacy() {
        customerPrivacy.click();
        return this;
    }

    public CreateAccountPage setPsgdpr() {
        psgdpr.click();
        return this;
    }

    public CreateAccountPage saveNewAccount() {
        save.click();
        return this;
    }

    public CreateAccountPage continueNewAccount() {
        jsScrollIntoViewElement(continueBtn);
        continueBtn.click();
        return this;
    }
}
