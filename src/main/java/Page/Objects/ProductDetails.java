package Page.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductDetails  extends BasePage{

    @FindBy(css = ".product-title>a")
    private WebElement productName;

    public static final Logger logger = LoggerFactory.getLogger("ProductDetails.class");
    public ProductDetails(WebDriver driver) {super(driver);}

    private String displayedProductName = null;

    public String getProductName(){
        displayedProductName = productName.getText();
        return displayedProductName;
    }

    @Override
    public int hashCode() {
        int result = getProductName() != null ? getProductName().hashCode() : 0;
        result = 31 * result + (displayedProductName != null ? displayedProductName.hashCode() : 0);
        return result;
    }
}