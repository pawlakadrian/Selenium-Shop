package tests;

import helpers.EmailGenerator;
import helpers.TestBase;
import lombok.extern.slf4j.Slf4j;
import models.User;
import models.UserFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CreateAccountPage;
import pages.LoginPage;
import pages.MenuPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Slf4j
public class RegisterTest extends TestBase {

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
        log.info("User first name: {} and last name: {}", userFirstName, userLastName);
        log.info("Test create new user was finished.");
    }
}