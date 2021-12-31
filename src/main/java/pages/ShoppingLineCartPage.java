package pages;

import models.BasketLine;
import models.Product;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.math.BigDecimal;

public class ShoppingLineCartPage extends BasePage{

    public ShoppingLineCartPage(WebElement shoppingLineCartPage, WebDriver driver) {
        super(driver);
        PageFactory.initElements(
                new DefaultElementLocatorFactory(shoppingLineCartPage), this);
    }

    @FindBy(css = ".product-line-info a")
    private WebElement productName;

    @FindBy(css = ".current-price")
    private WebElement productPrice;

    @FindBy(css = ".js-cart-line-product-quantity")
    private WebElement productQuantity;

    @FindBy(css = ".remove-from-cart i")
    private WebElement removeItemIcon;

    @FindBy(css = ".js-increase-product-quantity")
    private WebElement arrowIncreaseProductQuantity;

    @FindBy(css = ".js-decrease-product-quantity")
    private WebElement arrowDecreaseProductQuantity;

    public String getProductName() {
        return productName.getText();
    }

    public BigDecimal getProductPrice() {
        return getPriceAsBigDecimal(productPrice);
    }

    public int getProductQuantity() {
        return Integer.parseInt(productQuantity.getAttribute("value"));
    }

    public ShoppingLineCartPage removeProductFromShoppingCart() {
        retryingFindClick(removeItemIcon);
        wait.until(x-> elementIsNotVisible(removeItemIcon).equals(true));
        return this;
    }

    public ShoppingLineCartPage increaseProductInShoppingCart() {
        clickObject(arrowIncreaseProductQuantity);
        return this;
    }

    public ShoppingLineCartPage decreaseProductInShoppingCart() {
        clickObject(arrowDecreaseProductQuantity);
        return this;
    }

    public ShoppingLineCartPage setQuantity(int quantity) {
        productQuantity.sendKeys(Keys.chord(Keys.CONTROL,"a",Keys.DELETE));
        productQuantity.sendKeys(Integer.toString(quantity));
        return this;
    }

    public BasketLine createBasketLine() {
        Product product = new Product(getProductName(), getProductPrice());
        return new BasketLine(product, getProductQuantity());
    }
}
