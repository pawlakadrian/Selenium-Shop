package configuration.yaml;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tests.LoginTest;

public class BrowserEnvironment {
    private WebDriver driver;
    Logger logger = LoggerFactory.getLogger(BrowserEnvironment.class);

    private YamlReader yamlReader;

    private String getBrowser() {
        yamlReader = new YamlReader();
        logger.info("Running browser: {} ", yamlReader.getYamlConfig().getEnvironmentModel().choseActiveEnvironment().getBrowser());
        return yamlReader.getYamlConfig().getEnvironmentModel().choseActiveEnvironment().getBrowser();
    }

    public WebDriver getDriver() {

        switch (getBrowser()) {
            case "chrome":
                ChromeOptions optionsChrome = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                optionsChrome.addArguments("start-maximized");
                driver = new ChromeDriver(optionsChrome);
                yamlReader = new YamlReader();
                String url = yamlReader.getYamlConfig().getEnvironmentModel().choseActiveEnvironment().getUrl();
                driver.get(url);
            break;

            case "firefox":
                FirefoxOptions optionsFirefox = new FirefoxOptions();
                WebDriverManager.firefoxdriver().setup();
                optionsFirefox.addArguments("start-maximized");
                driver = new FirefoxDriver(optionsFirefox);
                yamlReader = new YamlReader();
                String url1 = yamlReader.getYamlConfig().getEnvironmentModel().choseActiveEnvironment().getUrl();
                driver.get(url1);
            break;
        }
        return this.driver;
    }
}
