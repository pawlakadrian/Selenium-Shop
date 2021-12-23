package tests;

import helpers.TestBase;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProductAndCategoriesTest extends TestBase {
    Logger logger = LoggerFactory.getLogger(SearchPage.class);

    @Test
    @DisplayName("Test of categories")
    public void searchRandomProduct() {
        MenuPage menuPage = new MenuPage(getDriver());
        CategoryPage categoryPage = new CategoryPage(getDriver());

        for (int i = 0; i < menuPage.getCategories().size(); i++){
            menuPage.getCategories().get(i).click();
            logger.info("click on category {}", menuPage.getCategories().get(i).getText());
            menuPage.waitUntilElementIsVisible(categoryPage.getTitle());
            categoryPage.getTitle();
            logger.info("get title from category {}", categoryPage.getTitle().getText());
            assertThat(menuPage.getCategories().get(i).getText(), equalTo(categoryPage.getTitle().getText()));
            logger.info("assert that clicked category is equal to category on page");
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
            logger.info("Searched products by filter: {}", listOfProduct.getTitleOfProduct());
            listOfProductWeDisplay.add(listOfProduct.getTitleOfProduct());
        }
        assertThat(listOfProductWeDisplay, equalTo(listOfProductWeExpected));
        filterPage
                .clearFilters();
    }
}
