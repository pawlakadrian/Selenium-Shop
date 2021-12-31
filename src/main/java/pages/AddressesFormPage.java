package pages;

import com.github.javafaker.Faker;
import com.github.javafaker.service.RandomService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.Locale;

public class AddressesFormPage extends BasePage{

    @FindBy(css = "input[name='address1']")
    private WebElement addressInput;

    @FindBy(css = "input[name='city']")
    private WebElement cityInput;

    @FindBy(css = "input[name='postcode']")
    private WebElement postcodeInput;

    @FindBy(css = "select[name='id_country']")
    private WebElement selectCountry;

    @FindBy(css = ".continue[name='confirm-addresses']")
    private WebElement continueBtn;

    Faker faker = new Faker(
            new Locale("pl-PL"), new RandomService()
    );

    public AddressesFormPage setCountry() {
        Select country = new Select(selectCountry);
        country.selectByVisibleText("Poland");
        return this;
    }

    public AddressesFormPage setAddress() {
        addressInput.sendKeys(faker.letterify("???????"));
        return this;
    }

    public AddressesFormPage confirmAddresses() {
        clickObject(continueBtn);
        return this;
    }

    public AddressesFormPage setPostcode() {
        postcodeInput.sendKeys(faker.numerify("??-???"));
        return this;
    }

    public AddressesFormPage setCity() {
        cityInput.sendKeys(faker.letterify("???????"));
        return this;
    }

    public AddressesFormPage(WebDriver driver) {
        super(driver);
    }
}
