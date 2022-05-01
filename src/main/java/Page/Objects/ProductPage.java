package Page.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ProductPage extends BasePage{


    @FindBy(css = ".discount-percentage")
    WebElement save20percentLabel;

    @FindBy(css = ".regular-price")
    WebElement originalPrice;

    @FindBy(css = "span[itemprop='price']")
    WebElement actualPrice;

    private List<Boolean> priceCorrectnessList = new ArrayList<>();

    public WebElement getSave20percentLabel(){ return save20percentLabel;}
    public WebElement getOriginalPrice(){ return originalPrice;}
    public WebElement getActualPrice(){ return actualPrice;}
    public List<Boolean> getPriceCorrectnessList() { return priceCorrectnessList;}

    public static final Logger logger = LoggerFactory.getLogger("ProductPage.class");
    public ProductPage(WebDriver driver){ super(driver);}

    public ProductPage shouldUploadCorrectnessOfDiscount() {
        double orgPrice = getFullPriceFromString(originalPrice.getText());
        double actPrice = getFullPriceFromString(actualPrice.getText());
        double proDiscount = changeStringPercentToDouble(save20percentLabel.getText());
        priceCorrectnessList.add(verifyDiscount(orgPrice,actPrice,proDiscount));
        return this;
    }
}