package tests;

import helpers.TestBase;
import models.Basket;
import org.assertj.core.api.SoftAssertions;
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
                    .goToRandomCategory();
//                    .goToClothes(); //uncomment for debug, comment line above
            logger.info("Go to random category");

            listOfThumbnailsProductsPage
                    .goToRandomProduct();
            logger.info("Choose random product.");

            int randomQuantity = getRandomInt(4) + 1;

            productPage
                    .chooseQuantity(randomQuantity);

            productPage
                    .addProductToBasket(expectedBasket, randomQuantity)
                    .addToCart();

            PopupProductPage popupProductPage = new PopupProductPage(getDriver());
            popupProductPage
                    .goToPopup();

            assertThat(productPage.getProductName(), equalTo(popupProductPage.getName()));
            assertThat(productPage.getProductPrice(), equalTo(popupProductPage.getPrice()));
            assertThat(productPage.getLastElementAddedToBasket(expectedBasket, popupProductPage.getName()).get(0).getQuantity(), equalTo(popupProductPage.getQuantity()));

            assertThat(productPage.getAllElementsInCart(expectedBasket), equalTo(popupProductPage.getItemsInCart()));

            popupProductPage
                    .continueShopping();
        }
//        System.out.println(expectedBasket.toString());
    }

    @Test
    @DisplayName("Basket Test")
    public void basket() {
        ListOfThumbnailsProductsPage listOfThumbnailsProductsPage = new ListOfThumbnailsProductsPage(getDriver());
        MenuPage menuPage = new MenuPage(getDriver());
        ProductPage productPage = new ProductPage(getDriver());
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(getDriver());
        SoftAssertions soft = new SoftAssertions();

        Basket expectedBasket = new Basket();

        for (int i = 0; i < 5; i++) {
            menuPage
                    .goToRandomCategory();
//                    .goToClothes(); //uncomment for debug, comment line above
            logger.info("Go to random category");

            listOfThumbnailsProductsPage
                    .goToRandomProduct();
            logger.info("Choose random product.");

            int randomQuantity = getRandomInt(4) + 1;

            productPage
                    .chooseQuantity(randomQuantity);

            productPage
                    .addProductToBasket(expectedBasket, randomQuantity)
                    .addToCart();

            PopupProductPage popupProductPage = new PopupProductPage(getDriver());
            popupProductPage.goToPopup();
            popupProductPage.continueShopping();
        }

        menuPage.goToCartPage();

        soft.assertThat(expectedBasket).usingRecursiveComparison().isEqualTo(shoppingCartPage.getCurrentBasket());
        soft.assertThat(expectedBasket.getTotalOrderPrice()).isEqualTo(shoppingCartPage.getTotalValueOfShoppingCart());

        shoppingCartPage.getListOfProductsInCart().get(0).setQuantity(5);
        expectedBasket.getBasketLine().get(0).setQuantity(5);
        soft.assertThat(expectedBasket.getTotalOrderPrice()).isEqualTo(shoppingCartPage.getTotalValueOfShoppingCart());

        shoppingCartPage.getListOfProductsInCart().get(0).increaseProductInShoppingCart();
        expectedBasket.getBasketLine().get(0).setQuantity(6);

        soft.assertThat(expectedBasket.getTotalOrderPrice()).isEqualTo(shoppingCartPage.getTotalValueOfShoppingCart());

        shoppingCartPage.getListOfProductsInCart().get(0).decreaseProductInShoppingCart();
        expectedBasket.getBasketLine().get(0).setQuantity(5);

        soft.assertThat(expectedBasket.getTotalOrderPrice()).isEqualTo(shoppingCartPage.getTotalValueOfShoppingCart());

        int index = 0;
        for (ShoppingLineCartPage shoppingLineCartPage : shoppingCartPage.getListOfProductsInCart()) {
            shoppingCartPage.getListOfProductsInCart().get(index).removeProductFromShoppingCart();

            expectedBasket.getBasketLine().remove(index);
            soft.assertThat(expectedBasket.getTotalOrderPrice()).isEqualTo(shoppingCartPage.getTotalValueOfShoppingCart());
        }
    }
}
