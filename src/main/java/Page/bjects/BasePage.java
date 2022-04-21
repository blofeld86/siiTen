package Page.bjects;

import TestHelpers.ActionsHandler;
import TestHelpers.JSE;
import TestHelpers.WebElementHandler;
import Wait.WaitForTheElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasePage {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private WaitForTheElement waitForTheElement = new WaitForTheElement();
    private JSE jse = new JSE();
    private ActionsHandler actionsHandler = new ActionsHandler();
    private WebElementHandler webElementHandler = new WebElementHandler();


    protected Logger log() { return logger;}
    public WaitForTheElement getWaitForTheElement() { return waitForTheElement;}
    public JSE getJse() {return jse;}
    public ActionsHandler getActionsHandler() { return actionsHandler;}
    public WebElementHandler getWebElementHandler() { return webElementHandler;}
}
