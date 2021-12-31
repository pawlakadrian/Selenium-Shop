package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShippingFormPage extends BasePage{

    @FindBy(css = ".delivery-option")
    private List<WebElement> shippingMethod;

    @FindBy(css = ".continue[name='confirmDeliveryOption']")
    private WebElement continueBtn;

    public ShippingFormPage(WebDriver driver) {
        super(driver);
    }

    public ShippingFormPage chooseShippingMethod() {
        clickObject(getRandomElement(shippingMethod));
        return this;
    }

    public ShippingFormPage continueForm() {
        clickObject(continueBtn);
        return this;
    }
}
