package Page.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    @FindBy(name = "confirmDeliveryOption")
    WebElement confirmDeliveryOption;

    @FindBy(css = ".delivery-option:nth-child(1) .col-sm-1")
    WebElement deliveryTesterSii;

    @FindBy(css = "[for='delivery_option_1'] .carrier-delay")
    WebElement deliveryOptionSiiText;

    @FindBy(css = "#delivery_option_2")
    WebElement deliveryMan;

    @FindBy(css = "[for='delivery_option_2'] .carrier-delay")
    WebElement deliveryOptionDelManText;

    @FindBy(css = "#payment-option-1")
    WebElement payByCheck;

    @FindBy(css = "#payment-option-2")
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

    private String addressValue;
    private List<WebElement> popUpList = new ArrayList<>();
    private List<String> deliveryList = new ArrayList<>();
    private List<String> payMethodList = new ArrayList<>();

    public String getAddressValue() {return addressValue;}
    public List<WebElement> getPopUpList() {return popUpList;}
    public List<String> getDeliveryList(){return deliveryList;}
    public List<String> getPayMethodList(){return payMethodList;}

    public static final Logger logger = LoggerFactory.getLogger("ProceedOrderPage.class");
    public ProceedOrderPage(WebDriver driver) {
        super(driver);
    }

    public ProceedOrderPage shouldFillFormByRandomUser() {
        shouldFillInput(userFactory.getRandomUser().getFirstName(), alias);
        shouldFillInput(userFactory.getRandomUser().getCompany(), company);
        shouldFillInput(userFactory.getRandomUser().getStreet(), address);
        shouldFillInput(userFactory.getRandomUser().getCity(), city);
        jse.executeScript("arguments[0].scrollIntoView(true);", city);
        shouldHandleSelectByIndex(getRandom().nextInt(59),selectState);
        shouldFillInput(userFactory.getRandomUser().getZipCode(), zipCode);
        Select select1 = new Select(selectCountry);
        select1.selectByValue("14");
        shouldFillInput(userFactory.getRandomUser().getPhone(), phone);
        continueButton.click();
        logger.info("Successfully filled the form");
        return this;
    }

    public ProceedOrderPage shouldFillFormByRegisteredUser() {
        addressValue = userFactory.getAlreadyRegisteredUser().getFirstName();
        shouldFillInput(userFactory.getAlreadyRegisteredUser().getFirstName(), alias);
        shouldFillInput(userFactory.getAlreadyRegisteredUser().getLastName(), company);
        shouldFillInput(addressValue, address);
        shouldFillInput(userFactory.getAlreadyRegisteredUser().getCity(), city);
        jse.executeScript("arguments[0].scrollIntoView(true);", city);
        shouldHandleSelectByIndex(getRandom().nextInt(59),selectState);
        shouldFillInput(userFactory.getAlreadyRegisteredUser().getZipCode(), zipCode);
        Select select1 = new Select(selectCountry);
        select1.selectByValue("14");
        shouldFillInput(userFactory.getAlreadyRegisteredUser().getPhone(), phone);
        continueButton.click();
        logger.info("Successfully filled the form");
        return this;
    }


    public ProceedOrderPage shouldChooseDelivery() {
        deliveryMan.click();
        if(deliveryTesterSii.isSelected()){deliveryList.add(deliveryOptionSiiText.getText());}
        else if(deliveryMan.isSelected()){deliveryList.add(deliveryOptionDelManText.getText());}
        getWait().until(ExpectedConditions.elementToBeClickable(confirmDeliveryOption));
        confirmDeliveryOption.click();
        logger.info("Successfully chosen delivery");
        return this;
    }

    public ProceedOrderPage shouldChoosePaymentOption() {
        payByBank.click();
        if (payByCheck.isSelected()) { getPayMethodList().add("Payment method: Payments by check");}
        else if (payByBank.isSelected()) { getPayMethodList().add("Payment method: Bank transfer");}
        logger.info("Successfully chosen payment option");
        return this;
    }

    public ProceedOrderPage shouldHandleTermsPopup() {
        termsOfService.click();
        getWait().until(ExpectedConditions.visibilityOfAllElements(popUpContent));
        for (WebElement e : popUpContent) {popUpList.add(e);}
        closePopup.click();
        agreeTerms.click();
        logger.info("Successfully verified the terms");
        return this;
    }

    public SummaryPage shouldPlaceAnOrder() {
        placeOrder.click();
        logger.info("Successfully placed an order");
        return new SummaryPage(driver);
    }
}
