package Page.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SummaryPage extends BasePage{

    @FindBy(css = "#order-details li:nth-child(2)")
    private WebElement paymentMethod;

    @FindBy(css = "#order-details li:nth-child(3)")
    private WebElement deliveryMethod;

    @FindBy(css = ".col-sm-4>span")
    private List<WebElement> productName;

    @FindBy(css = "#order-items .text-xs-left")
    private List<WebElement> productPrice;

    @FindBy(css = ".col-sm-6 :nth-child(2).text-sm-center")
    private List<WebElement> productQuantity;

    @FindBy(css = "#order-details>ul>li:nth-child(1)")
    private WebElement orderReference;

    @FindBy(css = "[title='Orders']")
    private WebElement orderHistory;

    public SummaryPage(WebDriver driver){ super(driver);}

    private String payMethodResult = null;
    private String deliveryResult = null;
    private String orderReferenceNumber = null;

    public String getPayMethodResult(){return payMethodResult;}
    public String getDeliveryResult(){return deliveryResult;}
    public String getOrderReferenceNumber(){return deliveryResult;}


    public SummaryPage shouldVerifyOrder(){
        for (int i =0;i<productName.size();i++){
            if(productName.get(i).equals(CartConsistence.cartConsistenceList.get(i).getName()));
            if(getFullPriceFromString(productPrice.get(i).getText()) == CartConsistence.cartConsistenceList.get(i).getPrice());
            if(getNumberFromString(productQuantity.get(i).getText(),0) == CartConsistence.cartConsistenceList.get(i).getQuantity());
        }
        return this;
    }

    public SummaryPage shouldGetOrderReferencePayAndDeliveryMethod(){
        getWait().until(ExpectedConditions.visibilityOf(orderReference));
        orderReferenceNumber = orderReference.getText();
        payMethodResult = paymentMethod.getText();
        deliveryResult =  deliveryMethod.getText();
        return this;
    }

    public OrderHistoryPage shouldGoToOrderHistory(){
        jse.executeScript("arguments[0].scrollIntoView(true);",orderHistory);
        orderHistory.click();
        return new OrderHistoryPage(driver);
    }

}
