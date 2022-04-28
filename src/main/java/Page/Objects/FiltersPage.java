package Page.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class FiltersPage extends BasePage{

    public FiltersPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);}

    @FindBy(css = "#category-9>a")
    private WebElement art;

    @FindBy(css = ".ui-slider")
    private WebElement slider;

    @FindBy(css = "a.ui-slider-handle:nth-of-type(1)")
    private WebElement leftSliderButton;

    @FindBy(css = "a.ui-slider-handle:nth-of-type(2)")
    private WebElement rightSliderButton;

    @FindBy(css = ".faceted-slider p")
    private WebElement price;

    @FindBy(css = "span.price")
    private List<WebElement> displayedProductsPrice;

    @FindBy(xpath = "//div[@id='_desktop_search_filters_clear_all']/button")
    private WebElement clearButton;


    public FiltersPage goToArtCategory(){
        shouldClickElement(art,driver);
        return this;
    }

    public FiltersPage selectCorrectPrice(int lowerPriceLimit, int higherPriceLimit){
        jse.executeScript("arguments[0].scrollIntoView(true);",slider);
        moveSlider(leftSliderButton,driver,lowerPriceLimit,getNumberFromString(price.getText(),0));
        moveSlider(rightSliderButton,driver,higherPriceLimit,getNumberFromString(price.getText(), 2));
        log().info("Successfully selected corrected price");
        return this;
    }

    public FiltersPage verifyDisplayedProducts(){
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

    public FiltersPage clearFilter(){
        shouldClickElement(clearButton,driver);
        log().info("Successfully cleared filter");
        return this;
    }



}