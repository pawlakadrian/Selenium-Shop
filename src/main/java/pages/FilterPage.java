package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class FilterPage extends BasePage {

    @FindBy(css = "ul[id^='facet_'] > li:nth-child(1) > label[for^='facet'] > .custom-checkbox")
    private WebElement filter40x60;

    @FindBy(css = ".btn-tertiary")
    private WebElement clearFilters;

    @FindBy(css = ".active-filter-title")
    private WebElement activeFilters;

    public FilterPage clearFilters() {
        waitUntilElementIsVisible(clearFilters);
        clickObject(clearFilters);
        return this;
    }

    public FilterPage selectFilterFirst() {
        filter40x60.click();
        waitUntilElementIsVisible(activeFilters);
        return this;
    }

    public FilterPage(WebDriver driver) {
        super(driver);
    }
}
