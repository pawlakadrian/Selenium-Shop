package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProductPage extends BasePage{

    @FindBy(css = ".discount-percentage")
    private WebElement discountLabel;

    @FindBy(css = ".regular-price")
    private WebElement regularPrice;

    @FindBy(css = ".current-price [itemprop='price']")
    private WebElement discountPrice;

    public Boolean isRegularPrice(){
        return regularPrice.isDisplayed();
    }

    public Boolean isDiscountPrice(){
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
        BigDecimal regularPriceConvert = new BigDecimal(regularPrice.getText().replace("zł", "")).setScale(2, RoundingMode.HALF_UP);
        BigDecimal discountPriceConvert = new BigDecimal(discountPrice.getText().replace("zł", "")).setScale(2, RoundingMode.HALF_UP);
        BigDecimal amountOfDiscount = new BigDecimal(String.valueOf(regularPriceConvert.multiply(decimalDiscountPercent)));
        BigDecimal calcPriceWithDiscount = regularPriceConvert.subtract(amountOfDiscount);
        calcPriceWithDiscount = calcPriceWithDiscount.setScale(2, RoundingMode.HALF_UP);
        return calcPriceWithDiscount.compareTo(discountPriceConvert) == 0;
    }
}
