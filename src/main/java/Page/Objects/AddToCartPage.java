package Page.Objects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import javax.swing.tree.ExpandVetoException;
import java.util.List;

public class AddToCartPage extends BasePage{

    public AddToCartPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#top-menu>.category")
    List<WebElement> categories;

    @FindBy(css = ".thumbnail-container>a")
    List<WebElement> products;

    @FindBy(css = ".add-to-cart")
    WebElement addToCartButton;

    @FindBy(css = "input[name='qty']")
    WebElement productsInput;

    @FindBy(css = "#main [itemprop='name']")
    WebElement pageProductName;

    @FindBy(css = ".product-name")
    WebElement popupProductName;

    @FindBy(css = ".current-price>span")
    WebElement pagePrice;

    @FindBy(css = ".subtotal.value")
    WebElement popupPrice;

    @FindBy(css = ".product-quantity>strong")
    WebElement popupProductQuantity;

    @FindBy(css =".subtotal")
    WebElement subtotalPopupPrice;

    @FindBy(css = ".cart-products-count")
    WebElement popupSummary;

    @FindBy(css= ".btn.btn-secondary")
    WebElement continueShopping;

    @FindBy(css = "#_desktop_cart .cart-products-count")
    WebElement cartQuantityProducts;

    @FindBy(css = "#blockcart-modal:nth-of-type(4) .product-price")
    WebElement popupProductPrice;

    @FindBy(css = ".cart-products-count")
    WebElement pageProductsInCart;

    private static int quantityOfProductsInCart;



    public AddToCartPage shouldClickRandomCategory(WebDriver driver){
        wait.until(ExpectedConditions.visibilityOfAllElements(categories));
        getRandomWebElementFromList(categories,driver).click();
        return this;
    }

    public AddToCartPage shouldClickRandomProduct(WebDriver driver){
        WebElement product = getRandomWebElementFromList(products,driver);
        jse.executeScript("arguments[0].scrollIntoView(true);",product);
        shouldClickElement(product, driver);
        return this;
    }

    public AddToCartPage shouldAddToCart(WebDriver driver){
        quantityOfProductsInCart = random.nextInt(4);
        provideValue(productsInput,Integer.toString(quantityOfProductsInCart),driver);
        shouldClickElement(addToCartButton,driver);
        return this;
    }

    public AddToCartPage verifyPopupContent(){
        wait.until(ExpectedConditions.visibilityOf(popupProductName));
        Double pagePriceValue = getFullPriceFromString(pagePrice.getText());
        Double popupPriceValue = getFullPriceFromString(popupPrice.getText());
        Double subtotalPrice = getFullPriceFromString(subtotalPopupPrice.getText());
        if(pageProductName.getText().equals(popupProductName.getText()));
        if (pagePriceValue == popupPriceValue);
        if (subtotalPrice/popupPriceValue == Double.parseDouble(popupProductQuantity.getText()));
        if (getNumberFromString(popupSummary.getText(),0)==Integer.parseInt(popupProductQuantity.getText()));
        return this;
    }

    public AddToCartPage shouldChooseContinueShopping(WebDriver driver){
        ((JavascriptExecutor)driver).executeScript("document.location.reload()");
        actions.click(continueShopping);
        return this;
    }

    public AddToCartPage verifyQuantityOfProductsInCart(){
        int actValue = getNumberFromString(cartQuantityProducts.getText(),0);
        if(quantityOfProductsInCart == actValue);
        return this;
    }


}
