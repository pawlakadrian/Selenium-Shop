package tests;

import helpers.TestBase;
import models.Basket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BasketAndCheckoutTest extends TestBase {
    Logger logger = LoggerFactory.getLogger(SearchPage.class);

    @Test
    @DisplayName("Product successfully added to shopping cart")
    public void addProductToShoppingCart() {
        ListOfThumbnailsProductsPage listOfThumbnailsProductsPage = new ListOfThumbnailsProductsPage(getDriver());
        MenuPage menuPage = new MenuPage(getDriver());
        ProductPage productPage = new ProductPage(getDriver());

        Basket expectedBasket = new Basket();

        for (int i = 0; i < 3; i++) {
            menuPage
//                    .goToRandomCategory();
                    .goToClothes(); //uncomment for debug, comment line above
            logger.info("Go to random category");

            listOfThumbnailsProductsPage
                    .goToRandomProduct();
            logger.info("Choose random product.");

            productPage
                    .chooseQuantity(getRandomInt(4) + 1);

            productPage
                    .addProductToBasket(expectedBasket)
                    .addToCart();

            PopupProductPage popupProductPage = new PopupProductPage(getDriver());
            popupProductPage
                    .goToPopup();
            //todo
            //1. check data on popup

            assertThat(productPage.getProductName(), equalTo(popupProductPage.getName()));
            assertThat(productPage.getProductPrice(), equalTo(popupProductPage.getPrice()));
            System.out.println(expectedBasket);
            System.out.println(expectedBasket.getBasketLine().stream().filter(pn -> pn.getProduct().getName() == productPage.getProductName()));
//            assertThat(expectedBasket.getBasketLine().get(i).getQuantity(), equalTo(popupProductPage.getQuantity()));
//            assertThat(productPage.getProductName(), equalTo(popupProductPage.getItemsInCart()));
//            assertThat(productPage.(), equalTo(popupProductPage.getTotalValue()));


            //2. click on continueshopping
            popupProductPage
                    .continueShopping();

            //3. check cart in menu is updated

        }
//        System.out.println(expectedBasket.toString());

    }
}
