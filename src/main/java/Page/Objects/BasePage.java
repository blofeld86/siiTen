package Page.Objects;

import models.UserFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.helpers.WebElementHandler;

import java.util.Random;

public class BasePage  extends WebElementHandler {

    protected WebDriver driver;
    protected JavascriptExecutor jse;
    protected UserFactory userFactory;
    protected Actions actions;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    protected Logger log() { return logger;}

    public BasePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        jse = ((JavascriptExecutor)driver);
        userFactory = new UserFactory();
        actions = new Actions(driver);
        PageFactory.initElements(driver,this);
    }

    public void shouldHandleSelectByIndex(int i,WebElement element){
        Select select = new Select(element);
        select.selectByIndex(i);
        logger.info("Successfully selected the element with index number: "+i);
    }

    public void shouldHandleMultipleSelect(int i, WebElement element){
        Random random = new Random();
        Select select = new Select(element);
        for (int x =0;x<i; i++){select.selectByIndex(i);}
        logger.info("Successfully selected the random elements");
    }

    public void shouldUploadFile(String path, WebElement element){
        element.sendKeys(path);
        logger.info("Successfully uploaded file from path "+ path);
    }

    public void moveSlider(WebElement element, int expectedValue, int actualValue) {
        int result = actualValue - expectedValue;
        if (expectedValue < actualValue) {
            actions.clickAndHold(element).perform();
            for (int i = 0; i < result; i++) {
                actions.moveByOffset(-10, 0).perform();
            }
            actions.release().perform();
        } else if (expectedValue > actualValue) {
            actions.clickAndHold(element).perform();
            for (int i = 0; i < result; i++) {
                actions.moveByOffset(10, 0).perform();
            }
            actions.release().perform();
        }
    }

    public void actionsClick(WebElement element){actions.click(element).perform();}
    public void actionsDoubleClick(WebElement element){ actions.doubleClick(element).perform();}
    public void actionsContextClick(WebElement element){actions.contextClick(element).perform();}
    public void actionsMoveToElement(WebElement one, WebElement two){
        actions.clickAndHold(one).moveToElement(two).perform();
    }
    public void actionsMoveByOffSet(WebElement element, int x, int y){
        actions.clickAndHold(element).moveByOffset(x,y).release().perform();
    }
    public void actionsDragAndDrop(WebElement one, WebElement two){
        actions.dragAndDrop(one,two).perform();
    }
    public void actionsSendKeys(WebElement element, String keys){
        actions.sendKeys(element,keys).perform();
    }
}