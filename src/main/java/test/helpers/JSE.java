package test.helpers;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSE {

    public void scrollToElement(WebElement element, WebDriver driver){
        ((JavascriptExecutor)driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void scrollToBottomOfPage(WebDriver driver){
        ((JavascriptExecutor)driver)
                .executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void clickElement(WebElement element, WebDriver driver){
        ((JavascriptExecutor)driver)
                .executeScript("arguments[0].click();",element);
    }
}
