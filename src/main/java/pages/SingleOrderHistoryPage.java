package pages;

import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.math.BigDecimal;


public class SingleOrderHistoryPage extends BasePage{
    public SingleOrderHistoryPage(WebElement singleOrderHistoryPage, WebDriver driver) {
        super(driver);
        PageFactory.initElements(
                new DefaultElementLocatorFactory(singleOrderHistoryPage), this);
    }

    @FindBy (css = "th")
    private WebElement orderNumber;

    @FindBy (css = "td:nth-child(2)")
    private WebElement date;

    @FindBy (css = "td:nth-child(3)")
    private WebElement totalPrice;

    @FindBy (css = "td:nth-child(4)")
    private WebElement payment;

    @FindBy (css = "td:nth-child(5)")
    private WebElement status;

    @FindBy (css = "td:nth-child(7)>a")
    private WebElement details;

    public SingleOrderHistoryPage goToDetails() {
        retryingFindClick(details);
        return this;
    }

    public String orderNumber() {
        return this.orderNumber.getText();
    }

    public String date() {
        return this.date.getText();
    }

    public BigDecimal totalPrice() {
        return getPriceAsBigDecimal(totalPrice);
    }

    public String payment() {
        return this.payment.getText();
    }

    public String status() {
        return this.status.getText();
    }
}
