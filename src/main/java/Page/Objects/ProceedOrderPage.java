package Page.Objects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProceedOrderPage extends BasePage {

    public ProceedOrderPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[name='alias']")
    WebElement alias;

    @FindBy(css = "[name='company']")
    WebElement company;

    @FindBy(css = "[name='address1']")
    WebElement address;

    @FindBy(css = "[name='city']")
    WebElement city;

    @FindBy(css = "[name='id_state']")
    WebElement selectState;

    @FindBy(css = "[name='postcode']")
    WebElement zipCode;

    @FindBy(css = "[name='id_country']")
    WebElement selectCountry;

    @FindBy(css = "[name='phone']")
    WebElement phone;

    @FindBy(css = "[name='confirm-addresses']")
    WebElement continueButton;

    @FindBy(css = "[name='confirmDeliveryOption']")
    WebElement confirmDeliveryOption;

    @FindBy(css = "#delivery_option_2")
    WebElement deliveryMan;

    @FindBy(css ="#payment-option-2")
    WebElement payByBank;

    @FindBy(css = "#cta-terms-and-conditions-0")
    WebElement termsOfService;

    @FindBy(css = "#modal p")
    List<WebElement> popUpContent;

    @FindBy(css = ".modal-content>.close")
    WebElement closePopup;

    @FindBy(css = ".custom-checkbox")
    WebElement agreeTerms;

    @FindBy(css = "#payment-confirmation button")
    WebElement placeOrder;




    public ProceedOrderPage shouldFillForm(){
        shouldFillInput(userFactory.getRandomUser().getFirstName(),alias,driver);
        shouldFillInput(userFactory.getRandomUser().getCompany(),company,driver);
        shouldFillInput(userFactory.getRandomUser().getStreet(),address,driver);
        shouldFillInput(userFactory.getRandomUser().getCity(),city,driver);
        Select select = new Select(selectState);
        jse.executeScript("arguments[0].scrollIntoView(true);",select);
        select.selectByIndex(random.nextInt(59));
        shouldFillInput(userFactory.getRandomUser().getZipCode(),zipCode,driver);
        Select select1 = new Select(selectCountry);
        select1.selectByValue("14");
        shouldFillInput(userFactory.getRandomUser().getPhone(),phone,driver);
        continueButton.click();
        return this;
    }

    public ProceedOrderPage shouldChooseDelivery(){
        deliveryMan.click();
        confirmDeliveryOption.click();
        return this;
    }

    public ProceedOrderPage shouldChoosePaymentOption(){
        payByBank.click();
        return this;
    }

    public ProceedOrderPage shouldHandleTermsPopup(){
        termsOfService.click();
        if(!popUpContent.isEmpty());
        wait.until(ExpectedConditions.elementToBeClickable(closePopup));
        closePopup.click();
        agreeTerms.click();
        return this;
    }

    public SummaryPage shouldPlaceAnOrder(){
        placeOrder.click();
        return new SummaryPage(driver);
    }





}
