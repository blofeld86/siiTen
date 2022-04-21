package TestHelpers;

import Wait.WaitForTheElement;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.Set;

public class WebElementHandler extends WaitForTheElement {

    private Logger logger = LoggerFactory.getLogger("WebElementHandler.class");
    private ActionsHandler actionsHandler = new ActionsHandler();

    public void shouldClickElement(WebElement element, WebDriver driver){
        untilElementIsClickable(element, driver);
        element.click();
    }

    public void shouldFillInput(String text,WebElement element,WebDriver driver){
        untilElementIsClickable(element,driver);
        element.clear();
        element.sendKeys(text);
        logger.info("Successfully filled the input by the value {}",text);
    }

    public void shouldHandleSelectByIndex(int i,WebElement element, WebDriver driver){
        untilElementIsClickable(element,driver);
        Select select = new Select(element);
        select.selectByIndex(i);
        logger.info("Successfully selected the element with index number: "+i);
    }

    public void shouldHandleSelectByValue(String value, WebElement element, WebDriver driver){
        untilElementIsClickable(element,driver);
        Select select = new Select(element);
        select.selectByValue(value);
        logger.info("Successfully selected the element with the visible text: "+ value);
    }

    public void shouldHandleMultipleSelect(int i, WebElement element, WebDriver driver){
        untilElementIsClickable(element, driver);
        Random random = new Random();
        Select select = new Select(element);
        for (int x =0;x<i; i++){
            select.selectByIndex(i);
        }
        logger.info("Successfully selected the random elements");
    }

    public void shouldUploadFile(String path, WebElement element){
        element.sendKeys(path);
        logger.info("Successfully uploaded file from path "+ path);
    }

    public WebElement getRandomWebElementFromList(List<WebElement> list, WebDriver driver){
        untilElementIsClickable(list.get(0),driver);
        Random random = new Random();
        logger.info("Successfully selected random WebElement");
        return list.get(random.nextInt(list.size()));
    }

    public String getTextFromListElement(WebElement element,String selector){
        return element.findElement(By.cssSelector(selector)).getText();
    }

    public String getTextFromElement(WebElement element, WebDriver driver){
        untilElementIsVisible(element,driver);
        return element.getText();
    }

    public void verifyIsElementDisplayed(WebElement element, WebDriver driver){
        element.isDisplayed();
        logger.info("The element is displayed");
    }

    public int numberOfItemsInCategory(List<WebElement> list){ return list.size();}

    public int getNumberFromText(String text,int word){
        String[] array= text.split(" ");
        int result = Integer.parseInt(array[word]);
        return result;
    }








}
