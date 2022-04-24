package Page.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

public class PricesPage extends BasePage{


    public PricesPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "#link-product-page-prices-drop-1")
    WebElement pricesDrop;

    @FindBy(css = ".thumbnail-container")
    List<WebElement> onSaleProducts;

    @FindBy(css= ".discount")
    List<WebElement> discount;

    @FindBy(css = ".regular-price")
    List<WebElement> originalPrice;

    @FindBy(css = "div>.price")
    List<WebElement> actualPrice;


    public PricesPage shouldClickPricesDrop(WebDriver driver){
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

    public ProductPage shouldOpenRandomProduct(WebDriver driver){
        shouldClickElement(onSaleProducts.get(random.nextInt(1)),driver);
        return new ProductPage(driver);
    }
}
