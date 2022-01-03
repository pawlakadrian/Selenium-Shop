package tests;

import helpers.TestBase;
import models.OrderDetails;
import models.User;
import models.UserFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import pages.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class WaitCompareTest extends TestBase {

    @Test
    @DisplayName("FluentWait")
    @RepeatedTest(30)
    public void fluentWait() {
        MainPage mainPage = new MainPage(getDriver());
        ProductPage productPage = new ProductPage(getDriver());
        PopupProductPage popupProductPage = new PopupProductPage(getDriver());
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(getDriver());
        AddressesFormPage addressesFormPage = new AddressesFormPage(getDriver());
        ShippingFormPage shippingFormPage = new ShippingFormPage(getDriver());
        PaymentPage paymentPage = new PaymentPage(getDriver());
        ConfirmationPage confirmationPage = new ConfirmationPage(getDriver());

        mainPage
                .selectProduct();
        productPage
                .addToCart();
        popupProductPage.goToPopup();
        popupProductPage.proceedToCheckout();
        shoppingCartPage.goToProceed();

        UserFactory newRandomUser = new UserFactory();
        User user = newRandomUser.getRandomUser();
        String userFirstName = user.getFirstName();
        String userLastName = user.getLastName();

        new CreateAccountPage(getDriver())

                .setFirstName(userFirstName)
                .setLastName(userLastName)
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .setCutomerPrivacy()
                .setPsgdpr()
                .continueNewAccount();

        addressesFormPage
                .setAddress()
                .setCity()
                .setPostcode()
                .setCountry()
                .confirmAddresses();
        shippingFormPage.continueForm();
        paymentPage
                .selectPaymentBankWire()
                .selectTherms()
                .placeOrder();

        assertThat(confirmationPage.confirmationMsg().substring(1), equalTo("YOUR ORDER IS CONFIRMED"));
        assertThat(confirmationPage.totalAmount(), equalTo("$19.12"));
    }
}