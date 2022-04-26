package Page.Objects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static Page.Objects.CartConsistence.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class BasketPage extends BasePage {

    public BasketPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#top-menu>.category")
    List<WebElement> categories;

    @FindBy(css = ".thumbnail-container>a")
    List<WebElement> products;

    @FindBy(css = "#quantity_wanted")
    WebElement input;

    @FindBy(css = ".add-to-cart")
    WebElement addToCartButton;

    @FindBy(css = ".h1")
    WebElement productName;

    @FindBy(css = "span[itemprop='price']")
    WebElement price;

    @FindBy(css = "#blockcart-modal>.modal-dialog")
    WebElement popup;

    @FindBy(css = ".product-name")
    WebElement popupProductName;

    @FindBy(css = ".col-md-6>.product-price")
    WebElement popupPrice;

    @FindBy(css = ".product-quantity>strong")
    WebElement popupQuantity;

    @FindBy(css = ".subtotal.value")
    WebElement popupTotal;

    @FindBy(css = ".btn-secondary")
    WebElement continueShopping;

    @FindBy(css = ".header .cart-products-count")
    WebElement cart;

    @FindBy(xpath = "//li[1][@class='cart-item']//input ")
    WebElement inputCart;

    @FindBy(css = "li:nth-child(1) .js-increase-product-quantity")
    WebElement arrowUp;

    @FindBy(css = "li:nth-child(1) .js-decrease-product-quantity")
    WebElement arrowDown;

    @FindBy(css = "li:nth-child(1) .product-price>strong")
    WebElement cartProductOne;

    @FindBy(css = ".cart-total>.value")
    WebElement totalOrderValue;

    @FindBy(css = "li:nth-child(1) .remove-from-cart")
    WebElement trash;

    // ZWERYFIKOWAĆ ZWARTOŚĆ
    public BasketPage shouldAddFiveRandomProducts(WebDriver driver) {
        for (int i = 0; i < 5; i++) {
            shouldClickElement(categories.get(random.nextInt(categories.size())), driver);
            shouldDoubleClick(products.get(random.nextInt(products.size())), driver);
            int quant = random.nextInt(4) + 1;
            shouldFillInput(Integer.toString(quant), input, driver);
            addToCartConsistenceList(productName.getText(),
                    getFullPriceFromString(price.getText()), quant);
            shouldClickElement(addToCartButton, driver);
            wait.until(ExpectedConditions.visibilityOf(popup));
            for (CartConsistence c : cartConsistenceList) {
                if (popupProductName.getText().equals(c.getName())) ;
                if (Integer.parseInt(popupQuantity.getText()) == c.getQuantity()) ;
                if (getFullPriceFromString(popupPrice.getText()) == (c.getPrice() * c.getQuantity())) ;
            }
            shouldClickElement(continueShopping, driver);
        }
        return this;
    }

    public BasketPage changeQuantityOfProduct(WebDriver driver,int cartProductNumber, int quantity) {
        wait.until(ExpectedConditions.elementToBeClickable(cart));
        cart.click();
        inputCart.sendKeys(Keys.CONTROL + "a");
        inputCart.sendKeys(Keys.DELETE);
        inputCart.sendKeys(Integer.toString(quantity), Keys.ENTER);
        cartConsistenceList.get(cartProductNumber).setQuantity(quantity);
        return this;
    }

    public BasketPage verifyTotalValue(WebDriver driver, int cartProductNumber, int listProductNumber, int actualQuantity) {
        double listProductPrice = cartConsistenceList.get(listProductNumber).getPrice();
        if (getFullPriceFromString(cartProductOne.getText()) == (listProductPrice * actualQuantity)) ;
        cartConsistenceList.get(cartProductNumber).setQuantity(actualQuantity);
        return this;
    }


    public BasketPage verifyExtendingProductQuantity(WebDriver driver, int cartProductNumber, int actualProductQuantity) {
        wait.until(ExpectedConditions.elementToBeClickable(arrowUp));
        arrowUp.click();
        ((JavascriptExecutor) driver).executeScript("document.location.reload()");
        cartConsistenceList.get(cartProductNumber).setQuantity(actualProductQuantity);
        double listProductPrice = cartConsistenceList.get(cartProductNumber).getPrice();
        if (getFullPriceFromString(cartProductOne.getText()) == (listProductPrice * actualProductQuantity));
        return this;
    }

    public BasketPage verifyTotalOrderValue(WebDriver driver){
        double total = 0;
        for (CartConsistence e: cartConsistenceList) {
             total += (e.getPrice() * e.getQuantity());
        }
        if (total == getFullPriceFromString(totalOrderValue.getText()));
        return this;
    }

    public BasketPage verifyReducingProductQuantity(WebDriver driver, int cartProductNumber, int actualProductQuantity) {
        wait.until(ExpectedConditions.elementToBeClickable(arrowUp));
        arrowDown.click();
        ((JavascriptExecutor) driver).executeScript("document.location.reload()");
        cartConsistenceList.get(cartProductNumber).setQuantity(actualProductQuantity);
        double listProductPrice = cartConsistenceList.get(cartProductNumber).getPrice();
        if (getFullPriceFromString(cartProductOne.getText()) == (listProductPrice * actualProductQuantity));
        return this;
    }

    public BasketPage removeProduct(WebDriver driver){
        trash.sendKeys(Keys.ENTER);
        cartConsistenceList.remove(0);
        ((JavascriptExecutor) driver).executeScript("document.location.reload()");
        return this;
    }


}