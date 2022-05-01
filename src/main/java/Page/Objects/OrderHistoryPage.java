package Page.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import static Page.Objects.CartConsistence.*;

public class OrderHistoryPage  extends BasePage {

    @FindBy(css = "td.text-xs-right")
    private WebElement totalPrice;

    @FindBy(css = "table .label-pill")
    private WebElement orderStatus;

    @FindBy(xpath = "//a[contains(text(),'Details')]")
    private WebElement details;

    @FindBy(xpath = "//td/strong")
    private List<WebElement> productNames;

    @FindBy(css = "#order-products td:nth-child(2)")
    private List<WebElement> productsQuantity;

    @FindBy(css = "#order-products td:nth-child(3)")
    private List<WebElement> productSinglePrices;

    @FindBy(css = "#delivery-address>address")
    private WebElement deliveryAddress;

    @FindBy(css = "#invoice-address>address")
    private WebElement invoiceAddress;

    @FindBy(css = "tbody>tr>td:nth-of-type(1)")
    private WebElement date;


    private double totalPriceValue = 0;

    private List<String> statusValueList = new ArrayList<>();


    private String delivAddress = null;
    private String invAddress = null;

    public WebElement getDate(){return date;}
    public double getTotalPriceValue(){return totalPriceValue;}
    public List<String> getStatusValueList(){return statusValueList;}
    public String getDelivAddress(){return delivAddress;}
    public String getInvAddress(){return invAddress;}

    public static final Logger logger = LoggerFactory.getLogger("OrderHistoryPage.class");
    public OrderHistoryPage(WebDriver driver) { super(driver);}


    public OrderHistoryPage shouldUploadOrderData() {
        totalPriceValue = getFullPriceFromString(totalPrice.getText());
        statusValueList.add(orderStatus.getText());
        return this;
    }

    public OrderHistoryPage shouldOpenDetails() {
        details.click();
        logger.info("Successfully opened the details");
        return this;
    }

    public OrderHistoryPage shouldFillListByValues() {
        for (int i = 0; i < productNames.size(); i++) {
            orderHistoryList.add(new CartConsistence(new CartConsistence.Builder()
                            .buildName(productNames.get(i).getText())
                            .buildPrice(getFullPriceFromString(productSinglePrices.get(i).getText()))
                            .buildQuantity(Integer.parseInt(productsQuantity.get(i).getText()))));
        }
        orderHistoryList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        return this;
    }

    public OrderHistoryPage shouldGetTheCustomerData(){
        getWait().until(ExpectedConditions.visibilityOf(deliveryAddress));
        String[] split = deliveryAddress.getText().split("\n");
        String[] split2 = invoiceAddress.getText().split("\n");
        delivAddress = split[2];
        invAddress = split2[2];
        return this;
    }
}
