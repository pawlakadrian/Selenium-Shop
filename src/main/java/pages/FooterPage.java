package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FooterPage extends BasePage{

    @FindBy (css="#link-product-page-prices-drop-1")
    private WebElement priceDropBtn;

    @FindBy (css="li>[title='Orders']")
    private WebElement ordersHistory;

    public FooterPage goToOnSale() {
        jsScrollIntoViewElement(priceDropBtn);
        clickObject(priceDropBtn);
        return this;
    }

    public FooterPage(WebDriver driver) {
        super(driver);
    }

    public FooterPage goToOrderHistory() {
        jsScrollIntoViewElement(ordersHistory);
        clickObject(ordersHistory);
        return this;
    }
}
