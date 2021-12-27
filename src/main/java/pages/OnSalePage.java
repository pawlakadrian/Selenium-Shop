package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OnSalePage extends BasePage{

    @FindBy (id="js-product-list-header")
    private WebElement title;

    public String getTitle() {
        waitUntilElementIsVisible(this.title);
        return this.title.getText();
    }

    public OnSalePage(WebDriver driver) {
        super(driver);
    }
}
