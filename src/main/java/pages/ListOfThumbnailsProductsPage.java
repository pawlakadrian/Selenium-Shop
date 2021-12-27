package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListOfThumbnailsProductsPage {

    public ListOfThumbnailsProductsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".product")
    private List<WebElement> listOfProducts;

    public List<SingleThumbnailProductPage> getListOfProducts() {
        List<SingleThumbnailProductPage> products = new ArrayList<>();

        for (WebElement product : listOfProducts) {
            products.add(new SingleThumbnailProductPage((WebElement) product));
        }
        return products;
    }

    public SingleThumbnailProductPage getRandomProduct() {
        Random rnd = new Random();
        return getListOfProducts().get(rnd.nextInt(getListOfProducts().size()));
    }

    public void goToRandomProduct() {
        Random rnd = new Random();
        listOfProducts.get(rnd.nextInt(getListOfProducts().size())).click();
    }
}
