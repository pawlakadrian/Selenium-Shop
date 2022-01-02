package pages;

import models.BasketLine;
import models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.math.BigDecimal;

public class OrderDetailsLinePage extends BasePage {

    public OrderDetailsLinePage(WebElement orderDetailsLinePage, WebDriver driver) {
        super(driver);
        PageFactory.initElements(
                new DefaultElementLocatorFactory(orderDetailsLinePage), this);

    }

    @FindBy(css = "td a")
    private WebElement productName;

    @FindBy(css = "td:nth-child(3)")
    private WebElement productPrice;

    @FindBy(css = "td:nth-child(2)")
    private WebElement productQuantity;

    public String getProductName() {
        return productName.getText();
    }

    public BigDecimal getProductPrice() {
        return getPriceAsBigDecimal(productPrice);
    }

    public int getProductQuantity() {
        return Integer.parseInt(productQuantity.getText());
    }

    public BasketLine createBasketLine() {
        Product product = new Product(getProductName(), getProductPrice());
        return new BasketLine(product, getProductQuantity());
    }
}
