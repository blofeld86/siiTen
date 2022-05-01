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
                    driver = getChromeDriverAndProperties();
                    break;
                case FIREFOX:
                    driver = getFirefoxDriverAndProperties();
                    break;
                case IE:
                    driver = getIEPDriverAndProperties();
                    break;
                default:
                    driver = getEdgeDriverAndProperties();
            }
        }catch (NullPointerException e){
            logger.error("Please select the browser correctly");
        }
        return driver;
    }

    public static Browser getBrowserFromYaml(){
        return Browser.valueOf(getProperties().getBrowser());
    }

    private static WebDriver getChromeDriverAndProperties(){
        logger.info("Successfully chosen chrome browser");
        ChromeOptions chromeOptions = new ChromeOptions();
        WebDriverManager.chromedriver().setup();
        chromeOptions.addArguments("start-maximized");
        return new ChromeDriver(chromeOptions);
    }

    private static WebDriver getFirefoxDriverAndProperties(){
        logger.info("Successfully chosen firefox browser");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        WebDriverManager.firefoxdriver().setup();
        firefoxOptions.addArguments("start-maximized");
        return new FirefoxDriver(firefoxOptions);
    }

    private static WebDriver getIEPDriverAndProperties(){
        logger.info("Successfully chosen internet explorer browser");
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
        WebDriverManager.iedriver().setup();
        return new InternetExplorerDriver(internetExplorerOptions);
    }

    private static WebDriver getEdgeDriverAndProperties(){
        logger.info("Successfully chosen edge browser");
        EdgeOptions edgeOptions = new EdgeOptions();
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver(edgeOptions);
    }



}
