package helpers;

import org.openqa.selenium.By;

public class ByFromString {

    public static By getByFromString(String locatorToString) {

        //Definicja lokatora
        //css selector: [class='confirm-button confirm-button-element idps-not-found'] span]
        String[] locatorSplit = locatorToString.split(": ");

        if (locatorSplit.length != 2) {
            throw new IllegalStateException(String.format("Locator definition does not had 2 elements for %s locator", locatorToString));
        }
        String locatorType = locatorSplit[0];
        String locatorValue = locatorSplit[1];
        switch (locatorType) {

            case "css selector":
                return By.cssSelector(locatorValue);
            case "id":
                return By.id(locatorValue);
            case "link text":
                return By.linkText(locatorValue);
            case "partial link text":
                return By.partialLinkText(locatorValue);
            case "tag name":
                return By.tagName(locatorValue);
            case "name":
                return By.name(locatorValue);
            case "class name":
                return By.className(locatorValue);
            case "xpath":
                return By.xpath(locatorValue);

            default:
                throw new IllegalStateException("Cannot define locator for WebElement definition: " + locatorToString);
        }
    }

}
