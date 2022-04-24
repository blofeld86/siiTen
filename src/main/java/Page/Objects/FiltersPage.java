package Page.Objects;

import org.checkerframework.checker.units.qual.K;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class FiltersPage extends BasePage{

    public FiltersPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);}

    @FindBy(css = "#category-9>a")
    WebElement art;

    @FindBy(css = ".ui-slider")
    WebElement slider;

    @FindBy(css = "a.ui-slider-handle:nth-of-type(1)")
    WebElement leftSliderButton;

    @FindBy(css = "a.ui-slider-handle:nth-of-type(2)")
    WebElement rightSliderButton;

    @FindBy(css = ".faceted-slider p")
    WebElement price;

    @FindBy(css = "span.price")
    List<WebElement> displayedProductsPrice;

    @FindBy(xpath = "//div[@id='_desktop_search_filters_clear_all']/button")
    WebElement clearButton;


    public FiltersPage goToArtCategory(WebDriver driver){
        shouldClickElement(art,driver);
        return this;
    }

    public FiltersPage selectCorrectPrice(WebDriver driver,int lowerPriceLimit, int higherPriceLimit){
        jse.executeScript("arguments[0].scrollIntoView(true);",slider);
        moveSlider(leftSliderButton,driver,lowerPriceLimit,getNumberFromString(price.getText(),0));
        moveSlider(rightSliderButton,driver,higherPriceLimit,getNumberFromString(price.getText(), 2));
        log().info("Successfully selected corrected price");
        return this;
    }

    public FiltersPage verifyDisplayedProducts(WebDriver driver){
        jse.executeScript("arguments[0].scrollIntoView(true);",slider);
        for (int i=0;i<displayedProductsPrice.size();i++){
            verifyPrice(getNumberFromString(displayedProductsPrice.get(i).getText()
                    , 0),getNumberFromString(price.getText()
                    , 0),getNumberFromString(price.getText()
                    ,2));
        }
        log().info("Successfully verified displayed products");
        return this;
    }

    public FiltersPage clearFilter(WebDriver driver){
        shouldClickElement(clearButton,driver);
        log().info("Successfully cleared filter");
        return this;
    }



}