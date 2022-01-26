package pages;

import models.Basket;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class OrderDetailsPage extends BasePage {

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
        return productsListOnOrderDetails
                .stream()
                .map(product -> new OrderDetailsLinePage(product, driver))
                .collect(Collectors.toList());
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
