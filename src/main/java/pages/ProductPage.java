package pages;

import models.Basket;
import models.BasketLine;
import models.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

public class ProductPage extends BasePage {

    @FindBy(css = ".discount-percentage")
    private WebElement discountLabel;

    @FindBy(css = ".regular-price")
    private WebElement regularPrice;

    @FindBy(css = ".current-price [itemprop='price']")
    private WebElement discountPrice;

    @FindBy(css = ".add-to-cart")
    private WebElement addToCart;

    @FindBy(css = "#quantity_wanted")
    private WebElement quantityInput;

    @FindBy(css = "h1[itemprop='name']")
    private WebElement productName;

    @FindBy(css = "span[itemprop='price']")
    private WebElement productPrice;

    @FindBy(css = "#quantity_wanted")
    private WebElement quantityInputFilled;

//    @FindBy(css = ".modal-content")
//    private WebElement popup;

    public int getQuantity() {
        return Integer.parseInt(quantityInputFilled.getAttribute("value"));
    }

    public BigDecimal getProductPrice() {
        return new BigDecimal(productPrice.getAttribute("content"));
    }

    public String getProductName() {
        return productName.getText();
    }

    public ProductPage chooseQuantity(int quantity) {
        waitUntilElementIsVisible(quantityInput);
        quantityInput.clear();
        quantityInput.sendKeys(Integer.toString(quantity));
        return this;
    }

    public ProductPage addToCart() {
        clickObject(addToCart);
        return this;
    }

    public Boolean isRegularPrice() {
        return regularPrice.isDisplayed();
    }

    public Boolean isDiscountPrice() {
        return discountPrice.isDisplayed();
    }

    public Boolean isDisplayDiscountLabel() {
        return discountLabel.isDisplayed();
    }

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public Boolean checkPriceWithDiscount() {
        double discountPercent = 0.2;
        BigDecimal decimalDiscountPercent = new BigDecimal(Double.toString(discountPercent));
        BigDecimal regularPriceConvert = new BigDecimal(regularPrice.getText().replace("zł", "")).setScale(2);
        BigDecimal discountPriceConvert = new BigDecimal(discountPrice.getText().replace("zł", "")).setScale(2);
        BigDecimal amountOfDiscount = new BigDecimal(String.valueOf(regularPriceConvert.multiply(decimalDiscountPercent)));
        BigDecimal calcPriceWithDiscount = regularPriceConvert.subtract(amountOfDiscount);
        calcPriceWithDiscount = calcPriceWithDiscount.setScale(2);
        return calcPriceWithDiscount.compareTo(discountPriceConvert) == 0;
    }

    public ProductPage addProductToBasket(Basket expectedBasket, int quantity) {
        Product product = new Product();
        product.setName(getProductName());
        product.setPrice(getProductPrice());
        BasketLine newBasketLine = new BasketLine(product, quantity);

        expectedBasket.addBasketLine(newBasketLine);
        newBasketLine.updateTotalOrderBasket();
        return this;
    }

    public List<BasketLine> getLastElementAddedToBasket(Basket expectedBasket, String productName) {
        List<BasketLine> filteredProduct = expectedBasket.getBasketLine()
                .stream()
                .filter(pn -> pn.getProduct().getName().contains(productName))
                .collect(Collectors.toList());
        return filteredProduct;
    }

    public int getAllElementsInCart(Basket expectedBasket) {
        int allItemsInCart = 0;
        for (BasketLine basketLine : expectedBasket.getBasketLine()) {
            allItemsInCart += basketLine.getQuantity();
        }
        return allItemsInCart;
    }
}
