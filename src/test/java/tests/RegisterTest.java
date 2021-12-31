package tests;

import helpers.EmailGenerator;
import helpers.TestBase;
import models.User;
import models.UserBuilder;
import models.UserFactory;
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
        UserFactory newRandomUser = new UserFactory();
        User user = newRandomUser.getRandomUser();

        String userFirstName = user.getFirstName();
        String userLastName = user.getLastName();

        MenuPage menuPage = new MenuPage(getDriver())
                .goToLoginPage();

        new LoginPage(getDriver())
                .goToCreateAccount();

        new CreateAccountPage(getDriver())

                .setFirstName(userFirstName)
                .setLastName(userLastName)
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .setCutomerPrivacy()
                .setPsgdpr()
                .saveNewAccount();

        assertThat(menuPage.verifyUserIsLogged(), equalTo(userFirstName + " " + userLastName));
        logger.info("User first name: {} and last name: {}", userFirstName, userLastName);
        logger.info("Test create new user was finished.");
    }
}