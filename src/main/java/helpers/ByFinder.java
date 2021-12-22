package helpers;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;

import java.lang.reflect.Proxy;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static helpers.ByFromString.getByFromString;


public class ByFinder {

    private static final String BY = "by";
    private static final String H = "h";
    private static final String LOCATOR = "locator";
    private static final String UNDERLYING_ELEMENT = "underlyingElement";
    private static final String FOUND_BY = "foundBy";

    public By getByFromWebElement(WebElement element) {
        try {
            //Przypadek dla DefaultElementLocator
            if (element instanceof DefaultElementLocator) {

                // W klasie DefaultElementLocator mamy bezpośredni dostęp do pola By
                return (By) FieldUtils.readField(element, BY, true);

                //Przypadek dla klasy Proxy
            } else if (element instanceof Proxy) {

                // Najpierw pobieramy obiekty pośredni
                Object proxyOrigin = getField(element, H);

                Object locator = FieldUtils.readField(proxyOrigin, LOCATOR, true);

                // A dopiero później możemy dostać się do klasy obiektu By
                return (By) FieldUtils.readField(locator, BY, true);

                // Przypadek dla RemoteWebElement
            } else {
                Object underlyingElement = getField(element, UNDERLYING_ELEMENT);
                String foundByString;
                try {
                    foundByString = getFoundBy(underlyingElement);
                } catch (IllegalArgumentException e) {
                    Object secondUnderLyingElement = getField(underlyingElement, UNDERLYING_ELEMENT);
                    foundByString = getFoundBy(secondUnderLyingElement);
                }
                //Pattern is from RemoteWebElement class
                //"[%s] -> %s: %s"
                String foundByPattern = "(?<=\\-> ).*";

                Pattern pattern = Pattern.compile(foundByPattern);
                Matcher matcher = pattern.matcher(foundByString);
                int locatorDefinitionIndex = 0;
                String locatorDefinition = matcher.group(locatorDefinitionIndex);
                return getByFromString(locatorDefinition);
            }

        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Failed to get locator from WebElement, due to: ", e);
        }
    }

    private Object getField(Object element, String fieldName) throws IllegalAccessException {
        return FieldUtils.readField(element, fieldName, true);
    }

    private String getFoundBy(Object element) throws IllegalAccessException {
        return (String) FieldUtils.readField(element, FOUND_BY, true);
    }

}