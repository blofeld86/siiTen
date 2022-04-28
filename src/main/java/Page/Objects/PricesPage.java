package Page.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PricesPage extends BasePage{


    public PricesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

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


    public PricesPage shouldClickPricesDrop(){
        jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        shouldClickElement(pricesDrop,driver);
        return this;
    }

    public PricesPage verifyDisplayedObjects(){
        wait.until(ExpectedConditions.visibilityOfAllElements(onSaleProducts));
        for (WebElement element : onSaleProducts) {
            element.isDisplayed();
        }
        return this;
    }

    public PricesPage verifyPresenceOfDiscounts(){
        for (WebElement element : discount) {
            if(element.getText().equals("-20%")){
                element.isDisplayed();
            }
        }
        return this;
    }

    public PricesPage verifyDisplayOfBothPrices(){
        for (WebElement element : onSaleProducts) {
            element.findElement(By.cssSelector(".regular-price")).isDisplayed();
            element.findElement(By.cssSelector("div>.price")).isDisplayed();
        }
        return this;
    }

    public PricesPage verifyCorrectnessOfDiscount(){
        for (int i = 0; i<onSaleProducts.size();i++) {
            double orgPrice = getFullPriceFromString(originalPrice.get(i).getText());
            double actPrice = getFullPriceFromString(actualPrice.get(i).getText());
            double prcDiscount = changeStringPercentToDouble(discount.get(i).getText());
            verifyDiscount(orgPrice, actPrice, prcDiscount);
        }
        return this;
    }

    public ProductPage shouldOpenRandomProduct(){
        shouldClickElement(onSaleProducts.get(random.nextInt(1)),driver);
        return new ProductPage(driver);
    }
}
