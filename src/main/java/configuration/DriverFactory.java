package configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static configuration.YamlReader.*;

public class DriverFactory {

    private static Logger logger = LoggerFactory.getLogger("DriverFactory.class");

    public static WebDriver getDriver(Browser browser){
        WebDriver driver = null;
        try{
            switch (browser){
                case CHROME:
                    logger.info("Successfully chosen chrome browser");
                    ChromeOptions chromeOptions = new ChromeOptions();
                    WebDriverManager.chromedriver().setup();
                    chromeOptions.addArguments("start-maximized");
                    return driver = new ChromeDriver(chromeOptions);
                case FIREFOX:
                    logger.info("Successfully chosen firefox browser");
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    WebDriverManager.firefoxdriver().setup();
                    firefoxOptions.addArguments("start-maximized");
                    return driver = new FirefoxDriver(firefoxOptions);
                case IE:
                    logger.info("Successfully chosen internet explorer browser");
                    InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
                    WebDriverManager.iedriver().setup();
                    return driver = new InternetExplorerDriver(internetExplorerOptions);
                default:
                    logger.info("Successfully chosen edge browser");
                    EdgeOptions edgeOptions = new EdgeOptions();
                    WebDriverManager.edgedriver().setup();
                    return driver = new EdgeDriver(edgeOptions);
            }
        }catch (NullPointerException e){
            logger.error("Please select the browser correctly");
        }
        return driver;
    }

    public static Browser getBrowserFromYaml(){
        return Browser.valueOf(getProperties().getBrowser());
    }




}
