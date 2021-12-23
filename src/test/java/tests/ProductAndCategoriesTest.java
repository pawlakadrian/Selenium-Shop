package tests;

import helpers.TestBase;
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

        List<SingleThumbnailProductPage> filteredArts = new ArrayList<>();
        List<String> filteredArts40x60 = new ArrayList<>(Arrays.asList("THE ADVENTURE POSTER", "THE BEST IS YET POSTER", "TODAY POSTER"));
        List<String> filteredArts60x90 = new ArrayList<>(Arrays.asList("THE ADVENTURE POSTER", "THE BEST IS YET POSTER", "TODAY POSTER"));
        List<String> filteredArts80x120 = new ArrayList<>(Arrays.asList("THE ADVENTURE POSTER", "THE BEST IS YET POSTER", "TODAY POSTER"));



        menuPage
                .goToArt();
        for (int i = 0; i < filterPage.availableFilters().size(); i++) {
            filterPage.availableFilters().get(i).click(); //click on 40x60 filter

            filteredArts.add(listOfThumbnailsProductsPage.getListOfProducts());
            System.out.println(filteredArts40x60);
            System.out.println("filteredArts" + filteredArts);
            assertThat(filteredArts, equalTo(filteredArts40x60));
        }

//
//        filterPage
//                .selectFilterFirst();
//        for (SingleThumbnailProductPage listOfProduct : listOfThumbnailsProductsPage.getListOfProducts()) {
//            logger.info("Searched products by filter: {}", listOfProduct.getTitleOfProduct());
//        }
//        filterPage
//                .clearFilters();
    }
}
