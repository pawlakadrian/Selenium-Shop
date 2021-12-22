package tests;

import helpers.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CategoryPage;
import pages.MenuPage;
import pages.SearchPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ProductAndCategoriesTest extends TestBase {
    Logger logger = LoggerFactory.getLogger(SearchPage.class);

    @Test
    @DisplayName("Test of categories")
    public void searchRandomProduct() {
        MenuPage menuPage = new MenuPage(getDriver());
        CategoryPage categoryPage = new CategoryPage(getDriver());

        for (WebElement category : menuPage.getCategories()) {
            category.click();
            logger.info("click on category {}", category);
            categoryPage.getTitle();
            logger.info("get title from category {}", categoryPage.getTitle());
            assertThat(category.getText(), equalTo(categoryPage.getTitle()));
            logger.info("assert that clicked category is equal to category on page");
        }
    }
}
