package tests;

import helpers.TestBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MenuPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
public class LoginTest extends TestBase {

    @Test
    @DisplayName("Failed login test")
    public void failedLogin() {
        MenuPage menuPage = new MenuPage(getDriver());

        menuPage.goToLoginPage();

        LoginPage loginPage = new LoginPage(getDriver());

        loginPage
                .setEmail(System.getProperty("emailFailed"))
                .setPassword(System.getProperty("passwordFailed"))
                .signIn();

        String validationMsg = "Authentication failed.";
        assertThat(validationMsg, equalTo(loginPage.loginFailMsg()));
        log.info("Test Login failed finished.");
    }

    @Test
    @DisplayName("Login successful test.")
    public void successLogin() {
        MenuPage menuPage = new MenuPage(getDriver());

        menuPage
                .goToLoginPage();

        new LoginPage(getDriver())
                .setEmail(System.getProperty("emailSuccess"))
                .setPassword(System.getProperty("passwordSuccess"))
                .signIn();

        assertThat(menuPage.verifyUserIsLogged(), equalTo("Test User"));
        log.info("Test Login successful - verify user is logged.");

        menuPage
                .logout();
        log.info("Logout user");

        assertThat(menuPage.verifyUserIsLoggedOut(), equalTo("Sign in"));
        log.info("Check user is logged out.");

        log.info("Test Login with successful finished.");

    }

    @Test
    public void mouseHoverTest() {
        new MenuPage(driver)
                .mouseHoverClothes()
                .mouseHoverAccessories()
                .mouseHoverArt();
    }
}
