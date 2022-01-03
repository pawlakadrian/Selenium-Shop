package pages;

import models.Basket;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ConfirmationPage extends BasePage{

    @FindBy(css="#content-hook_order_confirmation .card-title")
    private WebElement confirmationMsg;

    @FindBy(css = ".order-line")
    List<WebElement> listOrderOnConfirmation;

    @FindBy(css = "#order-details li:nth-child(3)")
    private WebElement shippingMethod;

    @FindBy(css = "#order-details li:nth-child(2)")
    private WebElement paymentMethod;

    @FindBy(css = "#order-details li:nth-child(1)")
    private WebElement referenceNumber;

    @FindBy(css = "#content-hook_payment_return > div > div > div > dl > dd:nth-child(2)")
    private WebElement totalAmount;

    public String totalAmount() {
        jsScrollIntoViewElement(this.totalAmount);
        return this.totalAmount.getText();
    }

    public String getShippingMethod() {
        return shippingMethod.getText();
    }

    public String getPaymentMethod() {
        return paymentMethod.getText();
    }

    public Basket getCurrentOrderListInConfirmation() {
        Basket basket = new Basket();
        for (ConfirmationOrderLinePage confirmationOrderLinePage : getListOfProductsInConfirmation()) {
            basket.addBasketLine(confirmationOrderLinePage.createBasketLine());
        }
        return basket;
    }

    public List<ConfirmationOrderLinePage> getListOfProductsInConfirmation() {
        List<ConfirmationOrderLinePage> products = new ArrayList<>();

        for (WebElement product : listOrderOnConfirmation) {
            products.add(new ConfirmationOrderLinePage(product, driver));
        }

        return products;
    }

    public String confirmationMsg() {
//        waitForWebElementToBeVisible(confirmationMsg);
        return confirmationMsg.getText();
    }

    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public String getReferenceNumber() {
        String trim[] = referenceNumber.getText().split(": ");
        String refNumber = trim[trim.length -1];
        return refNumber;
    }
}
