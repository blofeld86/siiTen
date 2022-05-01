package Page.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static Page.Objects.CartConsistence.addedProductsList;

public class SummaryPage extends BasePage{

    @FindBy(css = "#order-details li:nth-child(2)")
    private WebElement paymentMethod;

    @FindBy(css = "#order-details li:nth-child(3)")
    private WebElement deliveryMethod;

    @FindBy(css = ".details")
    private List<WebElement> productName;

    @FindBy(css = "#order-items .text-xs-left")
    private List<WebElement> productPrice;

    @FindBy(css = ".col-sm-6 :nth-child(2).text-sm-center")
    private List<WebElement> productQuantity;

    @FindBy(css = "#order-details>ul>li:nth-child(1)")
    private WebElement orderReference;

    @FindBy(css = "[title='Orders']")
    private WebElement orderHistory;

    private String payMethodResult = null;
    private String deliveryResult = null;
    private String orderReferenceNumber = null;
    private String dateValue;

    public String getPayMethodResult(){return payMethodResult;}
    public String getDeliveryResult(){return deliveryResult;}
    public String getOrderReferenceNumber(){return deliveryResult;}
    public String getDateValue(){return dateValue;}

    public static final Logger logger = LoggerFactory.getLogger("SummaryPage.class");
    public SummaryPage(WebDriver driver){ super(driver);}

    public SummaryPage getOrderData(){
        for (int i =0;i<productName.size();i++) {
            addedProductsList.add(new CartConsistence(new CartConsistence.Builder().
                    buildName(productName.get(i).getText()).
                    buildPrice(getFullPriceFromString(productPrice.get(i).getText())).
                    buildQuantity(Integer.parseInt(productQuantity.get(i).getText()))));
        }
        addedProductsList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        return this;
    }

    public SummaryPage shouldGetOrderReferencePayAndDeliveryMethod(){
        getWait().until(ExpectedConditions.visibilityOf(orderReference));
        orderReferenceNumber = orderReference.getText();
        payMethodResult = paymentMethod.getText();
        deliveryResult =  getDeliveryOptionResult();
        return this;
    }

    public OrderHistoryPage shouldGoToOrderHistory(){
        jse.executeScript("arguments[0].scrollIntoView(true);",orderHistory);
        orderHistory.click();
        logger.info("Successfully went to an order history");
        return new OrderHistoryPage(driver);
    }

    public boolean doesStringContainsString(String x, String y){
        if(x.contains(y)){return true;}
        return false;
    }

    private String getDeliveryOptionResult(){
        String[] split = deliveryMethod.getText().split("\n");
        return split[1];
    }

}