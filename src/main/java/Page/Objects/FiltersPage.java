package Page.Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class FiltersPage extends BasePage{

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

    @FindBy(css = "#_desktop_search_filters_clear_all>button")
    private WebElement clearButton;

    private List<Boolean> booleanList = new ArrayList<>();

    public List<Boolean> getBooleanList(){return booleanList;}

    public FiltersPage(WebDriver driver){ super(driver);}

    public FiltersPage goToArtCategory(){
        getWait().until(ExpectedConditions.elementToBeClickable(art));
        art.click();
        return this;
    }

    public FiltersPage selectCorrectPrice(int lowerPriceLimit, int higherPriceLimit){
        getWait().until(ExpectedConditions.visibilityOf(art));
        jse.executeScript("arguments[0].scrollIntoView(true);",slider);
        moveSlider(leftSliderButton,lowerPriceLimit,getNumberFromString(price.getText(),0));
        moveSlider(rightSliderButton,higherPriceLimit,getNumberFromString(price.getText(), 2));
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".filter-block")));
        log().info("Successfully selected corrected price");
        return this;
    }

    public FiltersPage enterValuesForVerifying(){
        jse.executeScript("arguments[0].scrollIntoView(true);",slider);
        getWait().until(ExpectedConditions.visibilityOfAllElements(displayedProductsPrice));
        for (int i=0;i<displayedProductsPrice.size()-1;i++){
            boolean value = verifyPrice(
                    getNumberFromString(displayedProductsPrice.get(i).getText(), 0),
                    getNumberFromString(price.getText(), 0),
                    getNumberFromString(price.getText(),2));
           booleanList.add(Boolean.valueOf(value));
        }
        log().info("Successfully verified displayed products");
        return this;
    }

    public FiltersPage clearFilter(){
        clearButton.click();
        log().info("Successfully cleared filter");
        return this;
    }



}