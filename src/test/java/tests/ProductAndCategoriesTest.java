package tests;

import helpers.TestBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
public class ProductAndCategoriesTest extends TestBase {

    @Test
    @DisplayName("Test of categories")
    public void searchRandomProduct() {
        MenuPage menuPage = new MenuPage(getDriver());
        CategoryPage categoryPage = new CategoryPage(getDriver());

        for (int i = 0; i < menuPage.getCategories().size(); i++){
            menuPage.getCategories().get(i).click();
            log.info("click on category {}", menuPage.getCategories().get(i).getText());
            menuPage.waitUntilElementIsVisible(categoryPage.getTitle());
            categoryPage.getTitle();
            log.info("get title from category {}", categoryPage.getTitle().getText());
            assertThat(menuPage.getCategories().get(i).getText(), equalTo(categoryPage.getTitle().getText()));
            log.info("assert that clicked category is equal to category on page");
        }
    }

    @Test
    @DisplayName("Test of filters")
    public void searchFilters() {
        MenuPage menuPage = new MenuPage(getDriver());
        FilterPage filterPage = new FilterPage(getDriver());
        ListOfThumbnailsProductsPage listOfThumbnailsProductsPage = new ListOfThumbnailsProductsPage(getDriver());
        List<String> listOfProductWeExpected = new ArrayList<>();
        listOfProductWeExpected.add("THE ADVENTURE POSTER");
        listOfProductWeExpected.add("THE BEST IS YET POSTER");
        listOfProductWeExpected.add("TODAY POSTER");

        List<String> listOfProductWeDisplay = new ArrayList<>();

        menuPage
                .goToArt();
        filterPage
                .selectFilterFirst();
        for (SingleThumbnailProductPage listOfProduct : listOfThumbnailsProductsPage.getListOfProducts()) {
            log.info("Searched products by filter: {}", listOfProduct.getTitleOfProduct());
            listOfProductWeDisplay.add(listOfProduct.getTitleOfProduct());
        }
        assertThat(listOfProductWeDisplay, equalTo(listOfProductWeExpected));
        filterPage
                .clearFilters();
    }

    @Test
    @DisplayName("Prices drop test")
    public void priceDropTests() {
        FooterPage footerPage = new FooterPage(getDriver());
        OnSalePage onSalePage = new OnSalePage(getDriver());
        ListOfThumbnailsProductsPage listOfThumbnailsProductsPage = new ListOfThumbnailsProductsPage(getDriver());
        ProductPage productPage = new ProductPage(getDriver());

        footerPage
                .goToOnSale();
        onSalePage
                .getTitle();
        assertThat(onSalePage.getTitle(), equalTo("ON SALE"));

        for (SingleThumbnailProductPage listOfProduct : listOfThumbnailsProductsPage.getListOfProducts()) {
            log.info("List of products with discount: {}", listOfProduct.getTitleOfProduct());
            assertThat(listOfProduct.isDiscount(), equalTo(true));
            assertThat(listOfProduct.isRegularPrice(), equalTo(true));
            assertThat(listOfProduct.isDiscountPrice(), equalTo(true));
            assertThat(listOfProduct.checkPriceWithDiscount(), equalTo(true));
        }
        log.info("Assertion checked all item has a discount sign, regular and discount price.");

        listOfThumbnailsProductsPage
                .goToRandomProduct();
        productPage
                .isDisplayDiscountLabel();
        assertThat(productPage.isDisplayDiscountLabel(), equalTo(true));
        log.info("Assertion checked label discount on product page.");

        assertThat(productPage.isDiscountPrice(), equalTo(true));
        assertThat(productPage.isRegularPrice(), equalTo(true));
        log.info("Assertion checked two prices (discount + regular) on product page.");

        assertThat(productPage.checkPriceWithDiscount(), equalTo(true));
        log.info("Assertion checked actual price is 20% lower than regular");
    }
}
