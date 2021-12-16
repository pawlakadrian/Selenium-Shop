package configuration.yaml;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserEnvironment {
    private WebDriver driver;

    private YamlReader yamlReader;

    private String getBrowser() {
        yamlReader = new YamlReader();
        System.out.println(yamlReader.getYamlConfig().getEnvironmentModel().choseActiveEnvironment().getBrowser());
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
