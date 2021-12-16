package configuration.yaml;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserEnvironment {
    private WebDriver driver;

    private YamlReader yamlReader;

    public WebDriver getDriver() {
        ChromeOptions optionsChrome = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        optionsChrome.addArguments("start-maximized");
        driver = new ChromeDriver(optionsChrome);
        yamlReader = new YamlReader();
        driver.get(System.getProperty("title"));
        this.driver = driver;
        return this.driver;
    }
}
