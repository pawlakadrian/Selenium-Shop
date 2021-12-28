package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;

public class PopupProductPage extends BasePage{

    @FindBy(css = ".product-name")
    private WebElement productName;

    @FindBy(css = "p.product-price")
    private WebElement productPrice;

    @FindBy(css = "span.product-quantity strong")
    private WebElement productQuantity;

    @FindBy(css = "p.cart-products-count")
    private WebElement thereAreXItems;

    @FindBy(css = ".product-total .value")
    private WebElement totalValue;

    @FindBy(css = ".btn-secondary")
    private WebElement continueShoppingBtn;

    @FindBy(css = "#blockcart-modal")
    private WebElement popup;

    public PopupProductPage continueShopping() {
        clickObject(continueShoppingBtn);
        return this;
    }

    public BigDecimal getTotalValue() {
        BigDecimal regularPriceConvert = new BigDecimal(totalValue.getText().replace("zł", "")).setScale(2);
        return regularPriceConvert;
    }

    public String getItemsInCart() {
        return thereAreXItems.getText();
    }

    public int getQuantity() {
        return Integer.parseInt(productQuantity.getText());
    }

    public BigDecimal getPrice() {
        BigDecimal regularPriceConvert = new BigDecimal(productPrice.getText().replace("zł", "")).setScale(2);
        return regularPriceConvert;
    }

    public String getName() {
        return productName.getText();
    }

    public PopupProductPage(WebDriver driver) {
        super(driver);
    }

    public void goToPopup() {
        waitForWebElementToBeVisible(popup);
        driver.switchTo().activeElement();
    }
}
