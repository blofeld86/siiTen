package Page.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class OrderHistoryPage extends BasePage {

    @FindBy(css = "th[scope='row']")
    private WebElement orderReference;

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

    @FindBy(css = "#order-products td:nth-child(4)")
    private List<WebElement> productTotalPrices;

    @FindBy(css = "#delivery-address>address")
    private WebElement deliveryAddress;

    @FindBy(css = "#invoice-address>address")
    private WebElement invoiceAddress;

    public OrderHistoryPage(WebDriver driver) { super(driver);}

    private String date = null;
    private double totalPriceValue = 0;
    private String statusValue = null;
    private List<CartConsistence> orderHistoryList = new ArrayList<>();

    private String delivAddress = null;
    private String invAddress = null;

    public String getDate(){return date;}
    public double getTotalPriceValue(){return totalPriceValue;}
    public String getStatusValue(){return statusValue;}
    public List<CartConsistence> getOrderHistoryList(){return orderHistoryList;}
    public String getDelivAddress(){return delivAddress;}
    public String getInvAddress(){return invAddress;}


    public OrderHistoryPage shouldUploadOrderData() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate localDate = LocalDate.now();
        date = dateTimeFormatter.format(localDate);
        totalPriceValue = getFullPriceFromString(totalPrice.getText());
        statusValue = orderStatus.getText();
        return this;
    }

    public OrderHistoryPage shouldOpenDetails() {
        details.click();
        return this;
    }

    public OrderHistoryPage shouldFillListByValues() {
        for (int i = 0; i < productNames.size(); i++) {
            shouldFillOrderList(productNames.get(i).getText(),
                    Integer.parseInt(productsQuantity.get(i).getText()),
            getFullPriceFromString(productTotalPrices.get(i).getText()));
        }
        return this;
    }

    public OrderHistoryPage shouldGetTheCustomerData(){
        String[] split = deliveryAddress.getText().split("\n");
        String[] split2 = invoiceAddress.getText().split("\n");
        delivAddress = split[2];
        invAddress = split2[2];
        return this;
    }

    public void shouldFillOrderList(String name, int quantity, double totalPriceValue){
        orderHistoryList.add(new CartConsistence(new CartConsistence.Builder()
                .buildName(name)
                .buildQuantity(quantity)
                .buildTotalOrderCost(totalPriceValue)));
    }
}