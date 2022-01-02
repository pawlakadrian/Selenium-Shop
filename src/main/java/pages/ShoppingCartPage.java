package pages;

import models.Basket;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartPage extends BasePage{

    @FindBy(css = ".cart-item")
    List<WebElement> productsListOnCart;

    @FindBy(css = ".cart-total .value")
    private WebElement totalValueShoppingCart;

    @FindBy(css = ".product-line-info a.label")
    private WebElement getName;

    @FindBy(css = ".checkout a")
    private WebElement goToProceed;

    public ShoppingCartPage goToProceed() {
        clickObject(goToProceed);
        return this;
    }

    public BigDecimal getTotalValueOfShoppingCart() {
        BigDecimal totalValue = new BigDecimal(totalValueShoppingCart.getText().replace("$", ""));
        return totalValue;
    }

    public List<ShoppingLineCartPage> getListOfProductsInCart() {
        List<ShoppingLineCartPage> products = new ArrayList<>();

        for (WebElement product : productsListOnCart) {
            products.add(new ShoppingLineCartPage(product, driver));
        }

        return products;
    }

    public Basket getCurrentBasket() {
        Basket basket = new Basket();
        for (ShoppingLineCartPage shoppingLineCartPage : getListOfProductsInCart()) {
            basket.addBasketLine(shoppingLineCartPage.createBasketLine());
        }
        return basket;
    }

    public ShoppingCartPage getProductsFromCart() {
        System.out.println("productsListOnCart " + productsListOnCart);
        return this;
    }

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }
}
