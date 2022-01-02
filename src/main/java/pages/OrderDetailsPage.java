package pages;

import models.Basket;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsPage extends BasePage{

    @FindBy(css = "#order-products tbody tr")
    List<WebElement> productsListOnOrderDetails;

    @FindBy(css = "#delivery-address address")
    private WebElement deliveryAddress;

    @FindBy(css = "#invoice-address address")
    private WebElement invoiceAddress;

    public String getDeliveryAddress() {
        return deliveryAddress.getText();
    }

    public String getInvoiceAddress() {
        return invoiceAddress.getText();
    }

    public List<OrderDetailsLinePage> getListOfProductsInOrderDetails() {
        List<OrderDetailsLinePage> products = new ArrayList<>();

        for (WebElement product : productsListOnOrderDetails) {
            products.add(new OrderDetailsLinePage(product, driver));
        }

        return products;
    }

    public Basket getCurrentBasket() {
        Basket basket = new Basket();
        for (OrderDetailsLinePage shoppingLineCartPage : getListOfProductsInOrderDetails()) {
            basket.addBasketLine(shoppingLineCartPage.createBasketLine());
        }
        System.out.println("basket " + basket);
        return basket;
    }


    public OrderDetailsPage(WebDriver driver) {
        super(driver);
    }
}
