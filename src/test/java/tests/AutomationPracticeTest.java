package tests;

import helpers.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.automationPractice.MenuPage;
import pages.automationPractice.ModalProductPage;
import pages.automationPractice.SearchPage;

public class AutomationPracticeTest extends TestBase {

    @Test
    @DisplayName("Test mouse hover on automation practice")
    public void testAutomationPractice() {
        MenuPage menuPage = new MenuPage(getDriver());
        SearchPage searchPage = new SearchPage(getDriver());
        ModalProductPage modalProductPage = new ModalProductPage(getDriver());

        menuPage
                .mouseHoverWomen()
                .mouseClickOnBlouses();
        searchPage
                .mouseHoverWomen()
                .clickOnQuickView();
        modalProductPage
                .switchToIframe()
                .mouseHoverFirstPick()
                .mouseHoverSecondPick()
                .mouseHoverThirdPick();
    }
}