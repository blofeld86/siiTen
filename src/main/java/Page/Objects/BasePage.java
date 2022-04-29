package Page.Objects;

import models.UserFactory;
import org.openqa.selenium.support.PageFactory;
import test.helpers.ActionsHandler;
import test.helpers.WebElementHandler;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class BasePage extends WebElementHandler{

    protected WebDriver driver;
    protected JavascriptExecutor jse;
    protected UserFactory userFactory;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private ActionsHandler actionsHandler = new ActionsHandler();

    protected Logger log() { return logger;}
    public ActionsHandler getActionsHandler() { return actionsHandler;}

    public BasePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        jse = ((JavascriptExecutor)driver);
        userFactory = new UserFactory();
        PageFactory.initElements(driver,this);
    }


}
