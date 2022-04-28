package test.helpers;

import com.beust.ah.A;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActionsHandler {

    private Logger logger = LoggerFactory.getLogger("ActionsHandler.class");

    private static Actions setActions(WebDriver driver){
        Actions actions = new Actions(driver);
        return actions;
    }

    public void moveByOffSet(WebDriver driver, WebElement element, int x, int y){
        setActions(driver).clickAndHold(element).moveByOffset(x,y).release().perform();
        logger.info("Successfully moved the element by the offset");
    }

    public void moveElementToElement(WebDriver driver, WebElement element1, WebElement element2){
        setActions(driver).clickAndHold(element1).moveToElement(element1).release().perform();
        logger.info("Successfully moved the element {} to the element {}",element1,element2);
    }

    public void moveToElement(WebDriver driver,WebElement element){
        setActions(driver).moveToElement(element).perform();
        logger.info("Successfully moved to the element {}", element.getText());
    }

    public void clickElement(WebDriver driver,WebElement element){
        setActions(driver).moveToElement(element).doubleClick();
        logger.info("Successfully clicked the element {}", element.getText());
    }

    public void clickAndHold(WebDriver driver, WebElement element){
        setActions(driver).clickAndHold(element).perform();
    }

    public void moveArrowLeft(WebDriver driver){
        setActions(driver).sendKeys(Keys.ARROW_LEFT).perform();
    }

    public void moveArrowRight(WebDriver driver){
        setActions(driver).sendKeys(Keys.ARROW_RIGHT).perform();
    }


    public void xyz (WebDriver driver, WebElement el){
        setActions(driver).clickAndHold();
    }





}
