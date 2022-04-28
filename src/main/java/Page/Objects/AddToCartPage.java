package Page.Objects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AddToCartPage extends BasePage{

    public AddToCartPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#top-menu>.category")
    private List<WebElement> categories;

    @FindBy(css = ".thumbnail-container>a")
    private List<WebElement> products;

    @FindBy(css = ".add-to-cart")
    private WebElement addToCartButton;

    @FindBy(css = "input[name='qty']")
    private WebElement productsInput;

    @FindBy(css = "#main [itemprop='name']")
    private WebElement pageProductName;

    @FindBy(css = ".product-name")
    private WebElement popupProductName;

    @FindBy(css = ".current-price>span")
    private WebElement pagePrice;

    @FindBy(css = ".subtotal.value")
    private WebElement popupPrice;

    @FindBy(css = ".product-quantity>strong")
    private WebElement popupProductQuantity;

    @FindBy(css =".subtotal")
    private WebElement subtotalPopupPrice;

    @FindBy(css = ".cart-products-count")
    private WebElement popupSummary;

    @FindBy(css= ".btn.btn-secondary")
    private WebElement continueShopping;

    @FindBy(css = "#_desktop_cart .cart-products-count")
    private WebElement cartQuantityProducts;

    @FindBy(css = "#blockcart-modal:nth-of-type(4) .product-price")
    private WebElement popupProductPrice;

    @FindBy(css = ".cart-products-count")
    private WebElement pageProductsInCart;

    private static int quantityOfProductsInCart;



    public AddToCartPage shouldClickRandomCategory(){
        wait.until(ExpectedConditions.visibilityOfAllElements(categories));
        getRandomWebElementFromList(categories,driver).click();
        return this;
    }

    public AddToCartPage shouldClickRandomProduct(){
        WebElement product = getRandomWebElementFromList(products,driver);
        jse.executeScript("arguments[0].scrollIntoView(true);",product);
        shouldClickElement(product, driver);
        return this;
    }

    public AddToCartPage shouldAddToCart(){
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

    public AddToCartPage shouldChooseContinueShopping(){
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
