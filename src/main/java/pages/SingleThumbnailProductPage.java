package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public class SingleThumbnailProductPage {

    public SingleThumbnailProductPage(WebElement singleProductThumbnail) {
        PageFactory.initElements(
                new DefaultElementLocatorFactory(singleProductThumbnail), this);
    }

    @FindBy(css = ".product-title")
    private WebElement title;

    public String getTitleOfProduct() {
        return this.title.getText();
    }
}
