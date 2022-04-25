package Page.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static Page.Objects.CartConsistence.Builder.*;
import static Page.Objects.CartConsistence.cartConsistenceList;
import static Page.Objects.CartConsistence.verifyCartConsistenceList;

import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BasketPage extends BasePage{

    public BasketPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
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


    public BasketPage shouldAddFiveRandomProducts(WebDriver driver){
        for(int i = 0;i<5;i++) {
            shouldClickElement(categories.get(random.nextInt(categories.size())), driver);
            shouldDoubleClick(products.get(random.nextInt(products.size())), driver);
            int quant = random.nextInt(4) + 1;
            shouldFillInput(Integer.toString(quant), input, driver);


            cartConsistenceList.add(i, new CartConsistence(new CartConsistence.Builder()
                    .buildName(productName.getText())
                    .buildPrice(getFullPriceFromString(price.getText()))
                    .buildQuantity(quant)));
            shouldClickElement(addToCartButton, driver);
            wait.until(ExpectedConditions.visibilityOf(popup));
            verifyCartConsistenceList.add(i, new CartConsistence(new CartConsistence.Builder()
                    .buildName(popupProductName.getText())
                    .buildPrice(getFullPriceFromString(popupPrice.getText()))
                    .buildQuantity(getNumberFromString(popupQuantity.getText(), 0))
                    .buildTotalOrderCost((getFullPriceFromString(popupTotal.getText())))));
            shouldClickElement(continueShopping, driver);
        }
         return this;
    }
}
