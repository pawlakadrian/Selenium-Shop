package pages;

import models.BasketLine;
import models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.math.BigDecimal;

public class ConfirmationOrderLinePage extends BasePage{

    public ConfirmationOrderLinePage(WebElement confirmationOrderLine, WebDriver driver) {
        super(driver);
        PageFactory.initElements(
                new DefaultElementLocatorFactory(confirmationOrderLine), this);
    }

    @FindBy(css = ".order-line>div:nth-child(2) span")
    private WebElement title;

    @FindBy(css = ".qty .row > div:nth-child(2)")
    private WebElement quantity;

    @FindBy(css = ".qty .row > div:nth-child(1)")
    private WebElement price;

    public String title() {
        return this.title.getText();
    }

    public BigDecimal price() {
        return getPriceAsBigDecimal(this.price);
    }

    public int quantity() {
        return Integer.parseInt(quantity.getText());
    }

    public BasketLine createBasketLine() {
        Product product = new Product(title(), price());
        return new BasketLine(product, quantity());
    }
}
