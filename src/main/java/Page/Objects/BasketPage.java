package Page.Objects;

import org.apache.commons.math3.util.Precision;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static Page.Objects.CartConsistence.*;

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

    @FindBy(css = ".product-line-grid a.label")
    private List<WebElement> productNameList;

    @FindBy(css = ".product-line-grid span.price")
    private List<WebElement> productPriceList;

    @FindBy(css = ".product-line-grid [type='number']")
    private List<WebElement> productQuantityList;

    @FindBy(css = ".cart-total .value")
    private WebElement totalPrice;

    @FindBy(css = "span[itemprop='price']")
    private WebElement price;

    @FindBy(css = "#blockcart-modal>.modal-dialog")
    private WebElement popup;

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

    private double totalValueCost = 0;
    private double displayedTotalPrice = 0;

    public double getDisplayedTotalPrice(){return displayedTotalPrice;}
    public List<WebElement> getTrash(){return  trash;}

    public static final Logger logger = LoggerFactory.getLogger("BasketPage.class");
    public BasketPage(WebDriver driver) { super(driver);}

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
        logger.info("Successfully added random products");
        return this;
    }

    public BasePage shouldGoToCart() throws InterruptedException {
        cart.click();
        getWait().until(ExpectedConditions.visibilityOfAllElements(productNameList));
        for(int i=0; i<productNameList.size();i++)
        addedProductsList.add(new CartConsistence(new CartConsistence.Builder().
                buildName(productNameList.get(i).getText()).
                buildPrice(getFullPriceFromString(productPriceList.get(i).getText())).
                buildQuantity(Integer.parseInt(productQuantityList.get(i).getAttribute("value")))));
        addedProductsList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        displayedTotalPrice = getFullPriceFromString(totalPrice.getText());
        logger.info("Successfully went to cart");
        return this;
    }
    public BasketPage changeQuantityOfProduct(int cartProductNumber, int quantity) {
        inputCart.sendKeys(Keys.CONTROL + "a");
        inputCart.sendKeys(Keys.DELETE);
        inputCart.sendKeys(Integer.toString(quantity), Keys.ENTER);
        cartConsistenceList.get(cartProductNumber).setQuantity(quantity);
        logger.info("Successfully changed the quantity of the product");
        return this;
    }

    public BasketPage increaseProductQuantity(int cartProductNumber, int actualProductQuantity) {
        getWait().until(ExpectedConditions.elementToBeClickable(arrowUp));
        arrowUp.click();
        cartConsistenceList.get(cartProductNumber).setQuantity(actualProductQuantity);
        logger.info("Successfully increased product quantity");
        return this;
    }


    public BasketPage reduceProductQuantity( int cartProductNumber, int actualProductQuantity) {
        getWait().until(ExpectedConditions.elementToBeClickable(arrowUp));
        arrowDown.click();
        cartConsistenceList.get(cartProductNumber).setQuantity(actualProductQuantity);
        logger.info("Successfully reduced product quantity");
        return this;
    }

    public BasketPage removeProduct(){
        getWait().until(ExpectedConditions.visibilityOfAllElements(cartItemList));
        trash.get(0).click();
        logger.info("Successfully removed product");
        return this;
    }

    public ProceedOrderPage shouldProceedToCheckOut(){
        jse.executeScript("arguments[0].scrollIntoView(true);",summaryProceedToCheckout);
        summaryProceedToCheckout.click();
        return new ProceedOrderPage(driver);
    }

    public double getTotalPrice(){
        double sum = 0;
        for(int i=0;i<addedProductsList.size();i++){
            sum += (addedProductsList.get(i).getPrice() * addedProductsList.get(i).getQuantity());
        }
        return Precision.round( sum + 7,2);
    }


}