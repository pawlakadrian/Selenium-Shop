package helpers;

import configuration.yaml.BrowserEnvironment;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {
    private static BrowserEnvironment browserEnvironment;
    private static Logger logger = LoggerFactory.getLogger(BaseTest.class);

    protected WebDriver driver;

    static ChromeOptions options = new ChromeOptions();

    @BeforeAll
    static void setDriver() {
        browserEnvironment = new BrowserEnvironment();
        WebDriverManager.chromedriver().setup();
        logger.debug("Webdriver initialized");
    }

    @BeforeEach
    void setup() {
        driver = browserEnvironment.getDriver();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory",  System.getProperty("user.dir")+ File.separator + "src" + File.separator + "download");
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logger.debug("Set properties to chrome driver");
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
