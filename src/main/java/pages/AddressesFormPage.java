package pages;

import com.github.javafaker.Faker;
import com.github.javafaker.service.RandomService;
import models.OrderDetails;
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

    OrderDetails orderDetails = new OrderDetails();

    public OrderDetails getOrderDetails() {
        return orderDetails;
    }

    public AddressesFormPage setCountry() {
        Select country = new Select(selectCountry);
        country.selectByVisibleText("Poland");
        orderDetails.setCountry("Poland");
        return this;
    }

    public AddressesFormPage setAddress() {
        String address = faker.letterify("???????");
        addressInput.sendKeys(address);
        orderDetails.setAddress(address);
        return this;
    }

    public AddressesFormPage confirmAddresses() {
//        waitForWebElementToBeVisible(continueBtn);
        clickObject(continueBtn);
        return this;
    }

    public AddressesFormPage setPostcode() {
        String postCode = faker.numerify("##-###");
        postcodeInput.sendKeys(postCode);
        orderDetails.setZipCode(postCode);
        return this;
    }

    public AddressesFormPage setCity() {
        String city = faker.letterify("???????");
        cityInput.sendKeys(city);
        orderDetails.setCity(city);
        return this;
    }

    public AddressesFormPage(WebDriver driver) {
        super(driver);
    }
}
