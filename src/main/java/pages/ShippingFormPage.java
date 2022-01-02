package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShippingFormPage extends BasePage{

    @FindBy(css = ".delivery-option .carrier-name")
    private List<WebElement> shippingMethod;

    @FindBy(css = ".continue[name='confirmDeliveryOption']")
    private WebElement continueBtn;

    public ShippingFormPage(WebDriver driver) {
        super(driver);
    }

    public WebElement choseShippingMethod() {
        WebElement chosenShippingMethod;
        chosenShippingMethod = getRandomElement(shippingMethod);
        clickObject(chosenShippingMethod);
        return chosenShippingMethod;
    }

    public ShippingFormPage continueForm() {
        clickObject(continueBtn);
        return this;
    }
}
