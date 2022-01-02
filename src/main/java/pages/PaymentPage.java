package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentPage extends BasePage {

    @FindBy(css = "#payment-option-2")
    private WebElement payByBankWire;

    @FindBy(css = ".custom-checkbox")
    private WebElement agreeTherms;

    @FindBy(css = "#payment-confirmation .btn-primary")
    private WebElement placeOrderBtn;

    public PaymentPage placeOrder() {
        clickObject(placeOrderBtn);
        return this;
    }

    public PaymentPage selectTherms() {
        clickObject(agreeTherms);
        return this;
    }

    public PaymentPage selectPaymentBankWire() {
        payByBankWire.click();
        return this;
    }

    public PaymentPage(WebDriver driver) {
        super(driver);
    }
}
