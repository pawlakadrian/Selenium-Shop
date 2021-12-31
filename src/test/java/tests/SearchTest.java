package tests;

import helpers.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.ListOfThumbnailsProductsPage;
import pages.MenuPage;
import pages.SearchPage;
import pages.SingleThumbnailProductPage;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class SearchTest extends TestBase {
    Logger logger = LoggerFactory.getLogger(SearchPage.class);

    @Test
    @DisplayName("Search random product")
    public void searchRandomProduct() {
        ListOfThumbnailsProductsPage listOfThumbnailsProductsPage = new ListOfThumbnailsProductsPage(driver);

        List<SingleThumbnailProductPage> listOfProducts = listOfThumbnailsProductsPage.getListOfProducts();

        String randomProductName = listOfProducts.get(getRandomInt(listOfProducts.size())).getTitleOfProduct();

        new MenuPage(driver)
                .sendKeyToInputAndSearch(randomProductName);

        ListOfThumbnailsProductsPage listOfThumbnailsSearchPage = new ListOfThumbnailsProductsPage(driver);

        List<SingleThumbnailProductPage> listOfProductsOnSearchPage = listOfThumbnailsSearchPage.getListOfProducts();
        String productNameOnSearchPage = listOfProductsOnSearchPage.get(0).getTitleOfProduct();

        assertThat(randomProductName, equalTo(productNameOnSearchPage));
        logger.info("Search random product finished.");
    }

    @Test
    @DisplayName("Test random product from dropdown")
    public void searchRandomProductFromDropdown() {
        ListOfThumbnailsProductsPage listOfThumbnailsProductsPage = new ListOfThumbnailsProductsPage(driver);

        List<SingleThumbnailProductPage> listOfProducts = listOfThumbnailsProductsPage.getListOfProducts();

        String randomProductName = listOfProducts.get(getRandomInt(listOfProducts.size())).getTitleOfProduct();

        MenuPage menuPage = new MenuPage(driver);
        menuPage
                .sendKeyToInput(randomProductName)
                .getHint();

        assertThat(randomProductName, equalTo(menuPage.getHint()));
        logger.info("Search random product finished.");
    }
}