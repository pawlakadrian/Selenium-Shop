package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(css=".products.row .product:nth-child(1)")
    private WebElement product;

    public MainPage selectProduct() {
        product.click();
        return this;
    }

    public MainPage(WebDriver driver) {
        super(driver);
    }
}
