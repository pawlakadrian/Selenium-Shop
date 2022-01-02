package tests;

import helpers.TestBase;
import models.Basket;
import models.OrderDetails;
import models.User;
import models.UserFactory;
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

            int randomQuantity = getRandomInt(5) + 1;

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

            int randomQuantity = getRandomInt(5) + 1;

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

    @Test
    @DisplayName("Checkout")
    public void checkoutTest() {
        ListOfThumbnailsProductsPage listOfThumbnailsProductsPage = new ListOfThumbnailsProductsPage(getDriver());
        MenuPage menuPage = new MenuPage(getDriver());
        ProductPage productPage = new ProductPage(getDriver());
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(getDriver());
        LoginPage loginPage = new LoginPage(getDriver());
        CreateAccountPage createAccountPage = new CreateAccountPage(getDriver());
        UserFactory userFactory = new UserFactory();
        UserFactory newRandomUser = new UserFactory();
        User user = newRandomUser.getRandomUser();
        AddressesFormPage addressesFormPage = new AddressesFormPage(getDriver());
        ShippingFormPage shippingFormPage = new ShippingFormPage(getDriver());
        PaymentPage paymentPage = new PaymentPage(getDriver());
        ConfirmationPage confirmationPage = new ConfirmationPage(getDriver());
        OrderDetails orderDetails = new OrderDetails();
        FooterPage footerPage = new FooterPage(getDriver());
        OrderHistoryPage orderHistoryPage = new OrderHistoryPage(getDriver());
        OrderDetailsPage orderDetailsPage = new OrderDetailsPage(getDriver());

        String userFirstName = user.getFirstName();
        String userLastName = user.getLastName();

        SoftAssertions soft = new SoftAssertions();

        Basket expectedBasket = new Basket();

        menuPage
                .goToLoginPage();
        loginPage
                .goToCreateAccount();
        new CreateAccountPage(getDriver())

                .setFirstName(userFirstName)
                .setLastName(userLastName)
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .setCutomerPrivacy()
                .setPsgdpr()
                .saveNewAccount();

        for (int i = 0; i < 5; i++) {
            menuPage
                    .goToRandomCategory();
//                    .goToClothes(); //uncomment for debug, comment line above
            logger.info("Go to random category");

            listOfThumbnailsProductsPage
                    .goToRandomProduct();
            logger.info("Choose random product.");

            int randomQuantity = getRandomInt(3) + 1;

            productPage
                    .chooseQuantity(randomQuantity);

            productPage
                    .addProductToBasket(expectedBasket, randomQuantity)
                    .addToCart();

            PopupProductPage popupProductPage = new PopupProductPage(getDriver());
            popupProductPage.goToPopup();
            if (i == 4) {
                popupProductPage.proceedToCheckout();
                shoppingCartPage.goToProceed();
            } else {
                popupProductPage.continueShopping();
            }
        }
        addressesFormPage
                .setAddress()
                .setCity()
                .setPostcode()
                .setCountry()
                .confirmAddresses();
        orderDetails.setShippingMethod(shippingFormPage.choseShippingMethod().getText());
        shippingFormPage.continueForm();
        paymentPage
                .selectPaymentBankWire()
                .selectTherms()
                .placeOrder();

        confirmationPage
                .confirmationMsg();

        soft.assertThat(expectedBasket).usingRecursiveComparison().isEqualTo(confirmationPage.getListOfProductsInConfirmation());

        assertThat(true, equalTo(confirmationPage.getShippingMethod().contains(orderDetails.getShippingMethod())));
        assertThat(true, equalTo(confirmationPage.getPaymentMethod().contains("Bank")));
        orderDetails.setOrderReference(confirmationPage.getReferenceNumber());

        footerPage.goToOrderHistory();

        SingleOrderHistoryPage lastOrder = (SingleOrderHistoryPage) orderHistoryPage.getSpecifiedOrder(orderDetails.getOrderReference()).get(0);

        assertThat(lastOrder.date(), equalTo(orderHistoryPage.dateToCompare()));

        assertThat(lastOrder.totalPrice(), equalTo(expectedBasket.getTotalOrderPrice()));

        assertThat(true, equalTo(lastOrder.payment().contains("Bank")));
        assertThat(true, equalTo(lastOrder.status().contains("Awaiting bank wire payment")));

        lastOrder.goToDetails();

        soft.assertThat(expectedBasket).usingRecursiveComparison().isEqualTo(orderDetailsPage.getCurrentBasket());
        assertThat(true, equalTo(orderDetailsPage.getDeliveryAddress().contains(addressesFormPage.getOrderDetails().getAddress())));
        assertThat(true, equalTo(orderDetailsPage.getDeliveryAddress().contains(addressesFormPage.getOrderDetails().getCity())));
        assertThat(true, equalTo(orderDetailsPage.getDeliveryAddress().contains(addressesFormPage.getOrderDetails().getCountry())));
        assertThat(true, equalTo(orderDetailsPage.getDeliveryAddress().contains(addressesFormPage.getOrderDetails().getZipCode())));

        assertThat(true, equalTo(orderDetailsPage.getInvoiceAddress().contains(addressesFormPage.getOrderDetails().getAddress())));
        assertThat(true, equalTo(orderDetailsPage.getInvoiceAddress().contains(addressesFormPage.getOrderDetails().getCity())));
        assertThat(true, equalTo(orderDetailsPage.getInvoiceAddress().contains(addressesFormPage.getOrderDetails().getCountry())));
        assertThat(true, equalTo(orderDetailsPage.getInvoiceAddress().contains(addressesFormPage.getOrderDetails().getZipCode())));
    }
}

