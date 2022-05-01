package Page.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class PricesPage extends BasePage{

    @FindBy(css = "#link-product-page-prices-drop-1")
    private WebElement pricesDrop;

    @FindBy(css = ".thumbnail-container")
    private List<WebElement> onSaleProducts;

    @FindBy(css= ".discount")
    private List<WebElement> discount;

    @FindBy(css = ".regular-price")
    private List<WebElement> originalPrice;

    @FindBy(css = "div>.price")
    private List<WebElement> actualPrice;

    private List<Boolean> areProductsDisplayedList = new ArrayList<>();
    private List<Boolean> discountProductsList = new ArrayList<>();
    private List<Boolean> regularPriceList = new ArrayList<>();
    private List<Boolean> discountedPriceList = new ArrayList<>();
    private List<Boolean> correctnessOfDiscountList = new ArrayList<>();

    public List<Boolean> getAreProductsDisplayedList(){ return areProductsDisplayedList;}
    public List<Boolean> getDiscountProductsList(){ return discountProductsList;}
    public List<Boolean> getRegularPriceList(){ return regularPriceList;}
    public List<Boolean> getDiscountedPriceList(){ return discountedPriceList;}
    public List<Boolean> getCorrectnessOfDiscountList(){ return correctnessOfDiscountList;}

    public static final Logger logger = LoggerFactory.getLogger("PricesPage.class");
    public PricesPage(WebDriver driver) { super(driver);}

    public PricesPage shouldClickPricesDrop(){
        jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        pricesDrop.click();
        logger.info("Successfully clicked prices drop");
        return this;
    }

    public PricesPage fillDisplayedObjectList(){
        getWait().until(ExpectedConditions.visibilityOfAllElements(onSaleProducts));
        for (WebElement element : onSaleProducts) { areProductsDisplayedList.add(element.isDisplayed());}
        return this;
    }

    public PricesPage fillPresenceOfDiscountList(){
        for (WebElement element : discount) { discountProductsList.add(element.isDisplayed());}
        return this;
    }

    public PricesPage fillBothPricesList(){
        for (WebElement element : originalPrice) { regularPriceList.add(element.isDisplayed());}
        for (WebElement element : actualPrice) { discountedPriceList.add(element.isDisplayed());}
        return this;
    }

    public PricesPage fillCorrectnessOfDiscountList(){
        for (int i = 0; i<onSaleProducts.size();i++) {
            double orgPrice = getFullPriceFromString(originalPrice.get(i).getText());
            double actPrice = getFullPriceFromString(actualPrice.get(i).getText());
            double prcDiscount = changeStringPercentToDouble(discount.get(i).getText());
            correctnessOfDiscountList.add(verifyDiscount(orgPrice, actPrice, prcDiscount));
        }
        return this;
    }

    public ProductPage shouldOpenRandomProduct(){
        (onSaleProducts.get(getRandom().nextInt(1))).click();
        logger.info("Successfully clicked discounted product");
        return new ProductPage(driver);
    }
}
