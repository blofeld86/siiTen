package Page.Objects;

import models.UserFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import test.helpers.WebElementHandler;

public class BasePage  extends WebElementHandler {

    protected WebDriver driver;
    protected JavascriptExecutor jse;
    protected UserFactory userFactory;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    protected Logger log() { return logger;}

    public BasePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        jse = ((JavascriptExecutor)driver);
        userFactory = new UserFactory();
        PageFactory.initElements(driver,this);
    }
}