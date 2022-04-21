package TestHelpers;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Set;

public class HandleWindow {

    private Logger logger = LoggerFactory.getLogger("HandleWindow.class");

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
