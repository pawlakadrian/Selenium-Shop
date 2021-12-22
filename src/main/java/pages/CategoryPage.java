package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CategoryPage extends BasePage{

    @FindBy (css = ".block-category h1")
    private WebElement title;

    public WebElement getTitle() {
        waitUntilElementIsVisible(this.title);
        return this.title;
    }

    public CategoryPage(WebDriver driver) {
        super(driver);
    }
}
