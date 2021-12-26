package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage{

    @FindBy(css = ".discount-percentage")
    private WebElement discountLabel;

    public Boolean isDisplayDiscountLabel() {
        return discountLabel.isDisplayed();
    }

    public ProductPage(WebDriver driver) {
        super(driver);
    }
}
