package Page.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductDetails extends BasePage{

    @FindBy(css = ".product-title>a")
    private WebElement productName;

    public ProductDetails(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    private String displayedProductName = null;
//    public String getDisplayedProductName(){return displayedProductName;}

    public String getProductName(){
        displayedProductName = productName.getText();
        return displayedProductName;
    }







}
