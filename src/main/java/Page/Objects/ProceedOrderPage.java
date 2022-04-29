package Page.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ProceedOrderPage extends BasePage {

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

    @FindBy(css = ".delivery-option:nth-child(1) .col-sm-1")
    WebElement deliveryTesterSii;

    @FindBy(css = "#delivery_option_2")
    WebElement deliveryMan;

    @FindBy(css = "#payment-option-1")
    WebElement payByCheck;

    @FindBy(css ="#payment-option-2")
    WebElement payByBank;

    @FindBy(css = "#cta-terms-and-conditions-0")
    WebElement termsOfService;

    @FindBy(css = ".js-modal-content p")
    List<WebElement> popUpContent;

    @FindBy(css = ".modal-content>.close")
    WebElement closePopup;

    @FindBy(css = ".custom-checkbox")
    WebElement agreeTerms;

    @FindBy(css = "#payment-confirmation button")
    WebElement placeOrder;

    public ProceedOrderPage(WebDriver driver) { super(driver);}

    private String payMethod;
    private String deliveryMethod;
    private String addressValue;
    private List<WebElement> popUpList = new ArrayList<>();

    public String getPayMethod(){return payMethod;}
    public String getDeliveryMethod(){return deliveryMethod;}
    public String getAddressValue(){return addressValue;}
    public List<WebElement> getPopUpList(){return popUpList;}



    public ProceedOrderPage shouldFillForm(){
        addressValue = userFactory.getRandomUser().getStreet();
        shouldFillInput(userFactory.getRandomUser().getFirstName(),alias,driver);
        shouldFillInput(userFactory.getRandomUser().getCompany(),company,driver);
        shouldFillInput(addressValue,address,driver);
        shouldFillInput(userFactory.getRandomUser().getCity(),city,driver);
        Select select = new Select(selectState);
        jse.executeScript("arguments[0].scrollIntoView(true);",select);
        select.selectByIndex(getRandom().nextInt(59));
        shouldFillInput(userFactory.getRandomUser().getZipCode(),zipCode,driver);
        Select select1 = new Select(selectCountry);
        select1.selectByValue("14");
        shouldFillInput(userFactory.getRandomUser().getPhone(),phone,driver);
        continueButton.click();
        return this;
    }

    public ProceedOrderPage shouldChooseDelivery(){
        deliveryMan.click();
        if(deliveryTesterSii.isSelected()){
            deliveryMethod = "Pick up in-store";
        }else if(deliveryMan.isSelected()){
            deliveryMethod = "Delivery next day!";
        }
        confirmDeliveryOption.click();
        return this;
    }

    public ProceedOrderPage shouldChoosePaymentOption(){
        payByBank.click();
        if(payByCheck.isSelected()){
            payMethod = "Payment method: Payments by check";
        } else if (payByBank.isSelected()){
            payMethod = "Payment method: Bank transfer";
        }
        return this;
    }

    public ProceedOrderPage shouldHandleTermsPopup(){
        termsOfService.click();
        getWait().until(ExpectedConditions.visibilityOfAllElements(popUpContent));
        for (WebElement e:popUpContent) { popUpList.add(e);}
        closePopup.click();
        agreeTerms.click();
        return this;
    }

    public SummaryPage shouldPlaceAnOrder(){
        placeOrder.click();
        return new SummaryPage(driver);
    }




}
