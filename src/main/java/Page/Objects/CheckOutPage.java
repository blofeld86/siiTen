package Page.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckOutPage extends BasePage{

    public CheckOutPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".user-info>a")
    private WebElement signIn;

    @FindBy(css = ".no-account>a")
    private WebElement registerUser;

    @FindBy(css = ".radio-inline:nth-child(2)")
    private WebElement maleSex;

    @FindBy(css = "[name='firstname']")
    private WebElement firstName;

    @FindBy(css = "[name='lastname']")
    private WebElement lastName;

    @FindBy(css = ".col-md-6>[name='email']")
    private WebElement email;

    @FindBy(css = "[name='password']")
    private WebElement password;

    @FindBy(css = "[name='birthday']")
    private WebElement birthdate;

    @FindBy(css = "[name='optin']")
    private WebElement receiveOffer;

    @FindBy(css = "[name='customer_privacy']")
    private WebElement customerPrivacy;

    @FindBy(css = "[name='newsletter']")
    private WebElement newsletter;

    @FindBy(css = "[name='psgdpr']")
    private WebElement privacyPolitic;

    @FindBy(css = "footer>[type='submit']")
    private WebElement register;

    public CheckOutPage signOpenRegisterForm(){
        shouldClickElement(signIn,driver);
        shouldClickElement(registerUser, driver);
        return this;
    }

    public CheckOutPage shouldChooseMale(){
        shouldClickElement(maleSex,driver);
        return this;
    }

    public CheckOutPage shouldProvideFirstAndLastName(){
        shouldFillInput(userFactory.getRandomUser().getFirstName(),firstName,driver);
        shouldFillInput(userFactory.getRandomUser().getLastName(),lastName,driver);
        return this;
    }

    public CheckOutPage shouldProvideMailAndPassword(){
        shouldFillInput(userFactory.getRandomUser().getMail(),email,driver);
        shouldFillInput(userFactory.getRandomUser().getPassword(),password,driver);
        return this;
    }

    public CheckOutPage shouldProvideBirthdate( int month, int day, int year){
        String birthday = Integer.toString(month)+"/"+Integer.toString(day)+"/"+Integer.toString(year);
        shouldFillInput(birthday,birthdate,driver);
        return this;
    }

    public CheckOutPage shouldMarkCheckBoxes(){
        jse.executeScript("arguments[0].scrollIntoView(true);",receiveOffer);
        receiveOffer.click();
        customerPrivacy.click();
        newsletter.click();
        privacyPolitic.click();
        return this;
    }

    public BasketPage shouldRegister(){
        wait.until(ExpectedConditions.elementToBeClickable(register));
        register.click();
        return new BasketPage(driver);
    }


}
