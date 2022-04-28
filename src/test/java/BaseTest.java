import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static configuration.YamlReader.*;
import static configuration.DriverFactory.*;

public class BaseTest {

    private static Logger logger = LoggerFactory.getLogger("BaseTest.class");
    protected WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        setPropertiesFromYAMLEnvironment();
    }

    @BeforeEach
    void setUp() {
        driver = getDriver(getBrowserFromYaml());
        driver.get(System.getProperty("appUrl"));
        logger.info("Driver initiated properly");
    }

    @AfterEach
    void tearDown() {
        driver.manage().deleteAllCookies();
        driver.quit();
        logger.info("Driver closed properly");
    }
}
