package configuration.yaml;

import pages.WebListener;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverFactory {
    private WebDriver webdriver;
    private EventFiringWebDriver driver;
    private WebListener webListener;
    Logger logger = LoggerFactory.getLogger(DriverFactory.class);

    private YamlReader yamlReader;

    public WebDriver getDriver(Browser webbrowser) {

        switch (webbrowser.toString()) {
            case "CHROME":
                ChromeOptions optionsChrome = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                optionsChrome.addArguments("start-maximized");
                webdriver = new ChromeDriver(optionsChrome);
                driver = new EventFiringWebDriver(webdriver);//listener
                webListener = new WebListener();
                driver.register(webListener);
                yamlReader = new YamlReader();
                String url = System.getProperty("url");
                driver.get(url);
            break;

            case "FIREFOX":
                FirefoxOptions optionsFirefox = new FirefoxOptions();
                WebDriverManager.firefoxdriver().setup();
                optionsFirefox.addArguments("start-maximized");
                webdriver = new FirefoxDriver(optionsFirefox);
                yamlReader = new YamlReader();
                String url1 = System.getProperty("url");
                webdriver.get(url1);
            break;
        }
        return this.webdriver;
    }
}
