package Page.Objects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import static Page.Objects.CartConsistence.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BasketPage extends BasePage {

    @FindBy(css = "#top-menu>.category")
    private List<WebElement> categories;

    @FindBy(css = ".thumbnail-container>a")
    private List<WebElement> products;

    @FindBy(css = "#quantity_wanted")
    private WebElement input;

    @FindBy(css = ".add-to-cart")
    private WebElement addToCartButton;

    @FindBy(css = ".h1")
    private WebElement productName;

    @FindBy(css = "span[itemprop='price']")
    private WebElement price;

    @FindBy(css = "#blockcart-modal>.modal-dialog")
    private WebElement popup;

    @FindBy(css = ".product-name")
    private WebElement popupProductName;

    @FindBy(css = ".col-md-6>.product-price")
    private WebElement popupPrice;

    @FindBy(css = ".product-quantity>strong")
    private WebElement popupQuantity;

    @FindBy(css = ".subtotal.value")
    private WebElement popupTotal;

    @FindBy(css = ".btn-secondary")
    private WebElement continueShopping;

    @FindBy(css = ".header .cart-products-count")
    private WebElement cart;

    @FindBy(xpath = "//li[1][@class='cart-item']//input ")
    private WebElement inputCart;

    @FindBy(css = "li:nth-child(1) .js-increase-product-quantity")
    private WebElement arrowUp;

    @FindBy(css = "li:nth-child(1) .js-decrease-product-quantity")
    private WebElement arrowDown;

    @FindBy(css = "li:nth-child(1) .product-price>strong")
    private WebElement cartProductOne;

    @FindBy(css = ".cart-total>.value")
    private WebElement totalOrderValue;

    @FindBy(css = ".cart-item")
    private List<WebElement> cartItemList;

    @FindBy(css = ".remove-from-cart")
    private List<WebElement> trash;

    @FindBy(css = ".no-items")
    private WebElement noItemsCart;

    @FindBy(css = ".cart-content-btn>a")
    private WebElement proceedButton;

    @FindBy(css = ".checkout")
    private WebElement summaryProceedToCheckout;

    public BasketPage(WebDriver driver) { super(driver);}

    private double totalValueCost = 0;

    public BasketPage shouldAddRandomProducts( int nrOfProd,int qntOfProd,boolean proceed) {
        for (int i = 0; i < nrOfProd; i++) {
            (categories.get(getRandom().nextInt(categories.size()))).click();
            shouldDoubleClick(products.get(getRandom().nextInt(products.size())));
            int quant = (getRandom().nextInt(qntOfProd-1)) + 1;
            shouldFillInput(Integer.toString(quant), input, driver);
            addToCartConsistenceList(productName.getText(),
                    getFullPriceFromString(price.getText()), quant);
            addToCartButton.click();
            getWait().until(ExpectedConditions.visibilityOf(popup));
            for (CartConsistence c : cartConsistenceList) {
                if (popupProductName.getText().equals(c.getName())) ;
                if (Integer.parseInt(popupQuantity.getText()) == c.getQuantity()) ;
                if (getFullPriceFromString(popupPrice.getText()) == (c.getPrice() * c.getQuantity())) ;
                totalValueCost += c.getPrice() * c.getQuantity();
            }
            if(i != (nrOfProd-1)) {
                continueShopping.click();
            } else {
                if (proceed == false){
                    continueShopping.click();
                }else {
                    proceedButton.click();
                }
            }
        }
        return this;
    }

    public BasketPage changeQuantityOfProduct(int cartProductNumber, int quantity) {
        getWait().until(ExpectedConditions.elementToBeClickable(cart));
        cart.click();
        inputCart.sendKeys(Keys.CONTROL + "a");
        inputCart.sendKeys(Keys.DELETE);
        inputCart.sendKeys(Integer.toString(quantity), Keys.ENTER);
        cartConsistenceList.get(cartProductNumber).setQuantity(quantity);
        return this;
    }

    public BasketPage verifyTotalValue( int cartProductNumber, int listProductNumber, int actualQuantity) {
        double listProductPrice = cartConsistenceList.get(listProductNumber).getPrice();
        if (getFullPriceFromString(cartProductOne.getText()) == (listProductPrice * actualQuantity)) ;
        cartConsistenceList.get(cartProductNumber).setQuantity(actualQuantity);
        return this;
    }


    public BasketPage verifyExtendingProductQuantity( int cartProductNumber, int actualProductQuantity) {
        getWait().until(ExpectedConditions.elementToBeClickable(arrowUp));
        arrowUp.click();
        ((JavascriptExecutor) driver).executeScript("document.location.reload()");
        cartConsistenceList.get(cartProductNumber).setQuantity(actualProductQuantity);
        double listProductPrice = cartConsistenceList.get(cartProductNumber).getPrice();
        if (getFullPriceFromString(cartProductOne.getText()) == (listProductPrice * actualProductQuantity));
        return this;
    }

    public BasketPage verifyTotalOrderValue(){
        double total = 0;
        for (CartConsistence e: cartConsistenceList) {
             total += (e.getPrice() * e.getQuantity());
        }
        if (total == getFullPriceFromString(totalOrderValue.getText()));
        return this;
    }

    public BasketPage verifyReducingProductQuantity( int cartProductNumber, int actualProductQuantity) {
        getWait().until(ExpectedConditions.elementToBeClickable(arrowUp));
        arrowDown.click();
        ((JavascriptExecutor) driver).executeScript("document.location.reload()");
        cartConsistenceList.get(cartProductNumber).setQuantity(actualProductQuantity);
        double listProductPrice = cartConsistenceList.get(cartProductNumber).getPrice();
        if (getFullPriceFromString(cartProductOne.getText()) == (listProductPrice * actualProductQuantity));
        return this;
    }

    public BasketPage removeProductAndVerify(){
        for (WebElement element : trash) {
            getWait().until(ExpectedConditions.visibilityOfAllElements(cartItemList));
            element.sendKeys(Keys.ENTER);
            cartConsistenceList.remove(0);
            verifyTotalOrderValue();
        }
        return this;
    }
    public BasketPage verifyCartLastContent(){
        getWait().until(ExpectedConditions.visibilityOf(noItemsCart));
        if(noItemsCart.getText().equals("There are no more items in your cart"));
        return this;
    }

    public ProceedOrderPage shouldProceedToCheckOut(){
        jse.executeScript("arguments[0].scrollIntoView(true);",summaryProceedToCheckout);
        summaryProceedToCheckout.click();
        return new ProceedOrderPage(driver);
    }

}