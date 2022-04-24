package Page.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductPage extends BasePage{

    public ProductPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".discount-percentage")
    WebElement save20percentLabel;

    @FindBy(css = ".regular-price")
    WebElement originalPrice;

    @FindBy(css = "span[itemprop='price']")
    WebElement actualPrice;

    public ProductPage shouldDisplayDiscountLabel(){
        wait.until(ExpectedConditions.visibilityOf(save20percentLabel));
        save20percentLabel.isDisplayed();
        return this;
    }

    public ProductPage shouldDisplayBothPrices(){
        originalPrice.isDisplayed();
        actualPrice.isDisplayed();
        return this;
    }

    public ProductPage shouldVerifyCorrectnessOfDiscount(){
        double orgPrice = getFullPriceFromString(originalPrice.getText());
        double actPrice = getFullPriceFromString(actualPrice.getText());
        double proDiscount = changeStringPercentToDouble(save20percentLabel.getText());
        verifyDiscount(orgPrice,actPrice,proDiscount);
        return this;
    }
}
