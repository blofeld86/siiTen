package Wait;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class WaitForTheElement {

    private Logger logger = LoggerFactory.getLogger("WaitForTheElement.class");

    private WebDriverWait getWait(WebDriver driver){
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.of(60, ChronoUnit.SECONDS));
        return webDriverWait;
    }

    public void untilElementIsVisible(WebElement element, WebDriver driver){
        getWait(driver).until(ExpectedConditions.visibilityOf(element));
    }

    public void untilElementIsClickable(WebElement element, WebDriver driver){
        getWait(driver).until(ExpectedConditions.visibilityOf(element));
    }

    public void untilAlertIsPresent(WebDriver driver){
        getWait(driver).until(ExpectedConditions.alertIsPresent());
    }
}
