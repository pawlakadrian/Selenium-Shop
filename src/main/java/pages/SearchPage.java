package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchPage extends BasePage{
    Logger logger = LoggerFactory.getLogger(SearchPage.class);

    public SearchPage(WebDriver driver) {
        super(driver);
    }
}
