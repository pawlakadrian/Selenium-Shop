package helpers;

import configuration.yaml.Browser;
import configuration.yaml.DriverFactory;
import configuration.yaml.YamlReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class TestBase {
    private static DriverFactory driverFactory;
    private static Logger logger = LoggerFactory.getLogger(TestBase.class);
    private static YamlReader yamlReader;
    private static Browser browser;

    protected WebDriver driver;

    static ChromeOptions options = new ChromeOptions();

    @BeforeAll
    static void setDriver() {
        yamlReader = new YamlReader();
        browser = Browser.valueOf(yamlReader.getYamlConfig().getEnvironmentModel().choseActiveEnvironment().getBrowser());
        driverFactory = new DriverFactory();
        WebDriverManager.chromedriver().setup();
        logger.debug("Webdriver initialized");
    }

    @BeforeEach
    void setup() {
        driver = driverFactory.getDriver(browser);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        logger.debug("Browser closed properly");
    }

    public void takeScreenshot(String pathname) throws IOException {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src, new File(pathname));
    }

    protected static String resolveTestResourcePath(String filename) {
        File file = new File("src/test/resources/" + filename);
        return file.getAbsolutePath();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
