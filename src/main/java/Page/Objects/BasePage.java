package Page.Objects;

import models.UserFactory;
import org.openqa.selenium.support.PageFactory;
import test.helpers.ActionsHandler;
import test.helpers.WebElementHandler;
import wait.WaitForTheElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class BasePage extends WebElementHandler{

    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait wait;
    protected JavascriptExecutor jse;
    protected Random random;
    protected UserFactory userFactory;

    public BasePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.of(12, ChronoUnit.SECONDS));
        jse = ((JavascriptExecutor)driver);
        random = new Random();
        userFactory = new UserFactory();
        PageFactory.initElements(driver,this);
    }

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private WaitForTheElement waitForTheElement = new WaitForTheElement();
    private ActionsHandler actionsHandler = new ActionsHandler();

    protected Logger log() { return logger;}
    public WaitForTheElement getWaitForTheElement() { return waitForTheElement;}
    public ActionsHandler getActionsHandler() { return actionsHandler;}
}
