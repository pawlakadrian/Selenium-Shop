package tests;

import helpers.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.LoginPage;
import pages.MenuPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LoginTest extends TestBase {
    Logger logger = LoggerFactory.getLogger(LoginTest.class);

    @Test
    @DisplayName("Failed login test")
    public void failedLogin() {
        MenuPage menuPage = new MenuPage(getDriver());

        menuPage.goToLoginPage();

        LoginPage loginPage = new LoginPage(getDriver());

        loginPage
                .setEmail("wrong@wp.pl")
                .setPassword("passbad999")
                .signIn();

        String validationMsg = "Authentication failed.";
        assertThat(validationMsg, equalTo(loginPage.loginFailMsg()));
        logger.info("Test Login failed finished.");
    }

    @Test
    @DisplayName("Login successful test.")
    public void successLogin() {
        MenuPage menuPage = new MenuPage(getDriver());

        menuPage
                .goToLoginPage();

        new LoginPage(getDriver())
                .setEmail("randommail@gmail.com")
                .setPassword("trudnehasł@997")
                .signIn();

        assertThat(menuPage.verifyUserIsLogged(), equalTo("Test User"));
        logger.info("Test Login successful - verify user is logged.");

        menuPage
                .logout();
        logger.info("Logout user");

        assertThat(menuPage.verifyUserIsLoggedOut(), equalTo("Sign in"));
        logger.info("Check user is logged out.");

        logger.info("Test Login with successful finished.");

    }
}
