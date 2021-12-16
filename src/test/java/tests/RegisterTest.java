package tests;

import helpers.EmailGenerator;
import helpers.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.CreateAccountPage;
import pages.LoginPage;
import pages.MenuPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class RegisterTest extends TestBase {
    Logger logger = LoggerFactory.getLogger(RegisterTest.class);

    @Test
    @DisplayName("Register Test")
    public void registerTest() {

        MenuPage menuPage = new MenuPage(getDriver())
                .goToLoginPage();

        new LoginPage(getDriver())
                .goToCreateAccount();

        new CreateAccountPage(getDriver())
                .setFirstName("RandomUser")
                .setLastName("User")
                .setEmail(new EmailGenerator().generateEmail())
                .setPassword("randomPass888")
                .setCutomerPrivacy()
                .setPsgdpr()
                .saveNewAccount();

        assertThat(menuPage.verifyUserIsLogged(), equalTo("RandomUser User"));
        logger.info("Test create new user was finished.");
    }
}
