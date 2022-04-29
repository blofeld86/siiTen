package test.helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class WebElementHandler {


    private Actions actions;
    private Random random;
    private WebDriverWait wait;

    public Actions getActions() {return actions;}
    public Random getRandom() {return random;}
    public WebDriverWait getWait(){return wait;}

    public WebElementHandler(WebDriver driver) {
        actions = new Actions(driver);
        random = new Random();
        wait = new WebDriverWait(driver, Duration.of(12, ChronoUnit.SECONDS));
    }

    private static final Logger logger = LoggerFactory.getLogger("WebElementHandler.class");



    public void shouldClickElementByActions(WebElement element){
        actions.click(element).perform();
    }

    public void provideValue(WebElement element, String string){
        element.clear();
        element.sendKeys(string);
    }

    public void shouldDoubleClick(WebElement element){
        actions.doubleClick(element).perform();
    }

    public void moveToElementAndClick(WebElement element){
        actions.moveToElement(element).click(element).perform();
    }

    public void shouldFillInput(String text,WebElement element,WebDriver driver){
        element.clear();
        element.sendKeys(text);
        logger.info("Successfully filled the input by the value {}",text);
    }

    public void shouldHandleSelectByIndex(int i,WebElement element, WebDriver driver){
        Select select = new Select(element);
        select.selectByIndex(i);
        logger.info("Successfully selected the element with index number: "+i);
    }

    public void shouldHandleSelectByValue(String value, WebElement element, WebDriver driver){
        Select select = new Select(element);
        select.selectByValue(value);
        logger.info("Successfully selected the element with the visible text: "+ value);
    }

    public void shouldHandleMultipleSelect(int i, WebElement element, WebDriver driver){
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

    public WebElement getRandomWebElementFromList(List<WebElement> list){
        logger.info("Successfully selected random WebElement");
        return list.get(random.nextInt(list.size()));
    }

    public String getTextFromListElement(WebElement element,String selector){
        return element.findElement(By.cssSelector(selector)).getText();
    }

    public String getTextFromElement(WebElement element, WebDriver driver){
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

    public int getNumberFromString(String s, int positionOfNumber) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                String  num = "";
                while (i < s.length() && Character.isDigit(s.charAt(i))) {
                    num += s.charAt(i++);
                }
                list.add(Integer.parseInt(num));
            }
        }
        return list.get(positionOfNumber);
    }

    public void moveSlider(WebElement element, int expectedValue, int actualValue) {
        if(expectedValue < actualValue){
             int result = actualValue - expectedValue;
             actions.clickAndHold(element).perform();
             for (int i =0; i<result;i++){
                actions.moveByOffset(-10,0).perform();
             }
             actions.release().perform();
        } else if (expectedValue > actualValue){
             int result = expectedValue - actualValue;
             actions.clickAndHold(element).perform();
             for (int i =0; i<result;i++){
                 actions.moveByOffset(10,0).perform();
             }
             actions.release().perform();
        }
    }

    public boolean verifyPrice(int price, int lowerLimit, int higherLimit) {
        if (price >= lowerLimit && price <= higherLimit) {
            return true;
        }
        return false;
    }

    public boolean verifyDiscount(double orgPrice, double actPrice, double discount){
        if (actPrice == orgPrice * (1-discount)){ return true;}
        return false;
    }

    public boolean verifyIsDisplayed(WebElement element){
        if (element.isDisplayed()){
            return true;
        }
        return false;
    }

    public double getFullPriceFromString(String value){
        int first = getNumberFromString(value,0);
        int second = getNumberFromString(value,1);
        String result = Integer.toString(first)+"."+Integer.toString(second);
        return Double.parseDouble(result);
    }

    public double changeStringPercentToDouble(String value){
        return getNumberFromString(value,0)/100.00;
    }

    public void switchToNewWindow(WebDriver driver){
        String parentWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()){
            String newWindow = iterator.next();
            if (!parentWindow.equalsIgnoreCase(newWindow)){
                driver.switchTo().window(newWindow);
            }
        }
        logger.info("Successfully switched to the new window");
    }

    public void switchAndClosePreviousWindow(WebDriver driver){
        String parentWindow = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()){
            String newWindow = iterator.next();
            if(!parentWindow.equalsIgnoreCase(newWindow)){
                driver.close();
                driver.switchTo().window(newWindow);
            }
        }
        logger.info("Successfully switched and closed the previous window");
    }










}
