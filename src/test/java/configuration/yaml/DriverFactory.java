package configuration.yaml;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLOutput;

public class DriverFactory {
    private WebDriver driver;
    Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    private YamlReader yamlReader;

    public WebDriver getDriver(Browser browser) {

        switch (browser.toString()) {
            case "CHROME":
                ChromeOptions optionsChrome = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                optionsChrome.addArguments("start-maximized");
                driver = new ChromeDriver(optionsChrome);
                yamlReader = new YamlReader();
                String url = System.getProperty("url");
                driver.get(url);
            break;

            case "FIREFOX":
                FirefoxOptions optionsFirefox = new FirefoxOptions();
                WebDriverManager.firefoxdriver().setup();
                optionsFirefox.addArguments("start-maximized");
                driver = new FirefoxDriver(optionsFirefox);
                yamlReader = new YamlReader();
                String url1 = System.getProperty("url");
                driver.get(url1);
            break;
        }
        return this.driver;
    }
}
