package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SingleThumbnailProductPage {

    public SingleThumbnailProductPage(WebElement singleProductThumbnail) {
        PageFactory.initElements(
                new DefaultElementLocatorFactory(singleProductThumbnail), this);
    }

    @FindBy(css = ".product-flag.discount")
    private WebElement discountOnPicture;

    @FindBy(css = ".product-title")
    private WebElement title;

    @FindBy(css = ".regular-price")
    private WebElement regularPrice;

    @FindBy(css = ".price")
    private WebElement discountPrice;

    public Boolean checkPriceWithDiscount() {
        double discountPercent = 0.2;
        BigDecimal decimalDiscountPercent = new BigDecimal(Double.toString(discountPercent));
        BigDecimal regularPriceConvert = new BigDecimal(regularPrice.getText().replace("$", "")).setScale(2);
        BigDecimal discountPriceConvert = new BigDecimal(discountPrice.getText().replace("$", "")).setScale(2);
        BigDecimal amountOfDiscount = new BigDecimal(String.valueOf(regularPriceConvert.multiply(decimalDiscountPercent)));
        BigDecimal calcPriceWithDiscount = regularPriceConvert.subtract(amountOfDiscount);
        calcPriceWithDiscount = calcPriceWithDiscount.setScale(2);
        return calcPriceWithDiscount.compareTo(discountPriceConvert) == 0;
    }

    public Boolean isRegularPrice(){
        return regularPrice.isDisplayed();
    }

    public Boolean isDiscountPrice(){
        return discountPrice.isDisplayed();
    }

    public Boolean isDiscount() {
        return discountOnPicture.isDisplayed();
    }

    public String getTitleOfProduct() {
        return this.title.getText();
    }
}
