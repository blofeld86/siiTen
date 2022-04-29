package Page.Objects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AddToCartPage extends BasePage{

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

    @FindBy(css = "span[itemprop='price']")
    private WebElement pagePrice;

    @FindBy(css = ".subtotal.value")
    private WebElement popupSubTotalPrice;

    @FindBy(css = ".product-quantity>strong")
    private WebElement popupProductQuantity;

    @FindBy(css = "#blockcart-modal .product-price")
    private WebElement popUpPrice;

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

    public AddToCartPage(WebDriver driver){ super(driver);}

    public AddToCartPage shouldClickRandomCategory(){
        getWait().until(ExpectedConditions.visibilityOfAllElements(categories));
        getRandomWebElementFromList(categories).click();
        return this;
    }

    public AddToCartPage shouldClickRandomProduct(){
        WebElement product = getRandomWebElementFromList(products);
        jse.executeScript("arguments[0].scrollIntoView(true);",product);
        product.click();
        return this;
    }

    public AddToCartPage shouldAddToCart(){
        int pgProdQuantity = getRandom().nextInt(4)+1;
        double pPrice = getFullPriceFromString(pagePrice.getText());
        provideValue(productsInput,Integer.toString(pgProdQuantity));
        CartConsistence.cartConsistenceList.add(new CartConsistence(new CartConsistence.Builder().
                buildName(pageProductName.getText()).
                buildPrice(pPrice).
                buildQuantity(pgProdQuantity).
                buildTotalOrderCost(pgProdQuantity * pPrice)));
        addToCartButton.click();
        return this;
    }

    public AddToCartPage downloadDataFromPopUp(){
        getWait().until(ExpectedConditions.visibilityOf(popupProductName));
        double pPrice = getFullPriceFromString(popUpPrice.getText());
        int quant = Integer.parseInt(popupProductQuantity.getText());
        CartConsistence.popUpCcList.add(new CartConsistence(new CartConsistence.Builder()
                .buildName(popupProductName.getText())
                .buildPrice(pPrice)
                .buildQuantity(quant)
                .buildTotalOrderCost(pPrice * quant)));
        return this;
    }

    public AddToCartPage shouldChooseContinueShopping(){
        continueShopping.click();
        return this;
    }
}
