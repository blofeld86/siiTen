package Page.bjects;

import com.fasterxml.jackson.databind.ser.Serializers;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class LandingPage extends BasePage {

    public LandingPage(WebDriver driver){ PageFactory.initElements(driver,this);}

    @FindBy(css = "div[itemprop='itemListElement']")
    List<WebElement> listOfElements;

    @FindBy(css = "input[type='text']")
    WebElement input;

    @FindBy(css = "button[type='submit']")
    WebElement searchButton;

    @FindBy(css = "span.product")
    WebElement dropdownProduct;

    @FindBy(css = "#_desktop_top_menu>ul>li>a")
    List <WebElement> listOfCategories;

    @FindBy(css = ".dropdown-submenu")
    List<WebElement> listOfSubCategories;

    @FindBy(css = ".category-top-menu a.h6")
    WebElement categoryName;

    @FindBy(css = "#search_filters")
    WebElement filters;

    @FindBy(css = ".product")
    List <WebElement> itemList;

    @FindBy(css = ".total-products")
    WebElement numberOfProducts;

    public String value = null;
    public String dropdownValue = null;

    public LandingPage enterRandomProductNameIntoField(WebDriver driver){
        WebElement element = getWebElementHandler()
                .getRandomWebElementFromList(listOfElements,driver);
        value = getWebElementHandler().getTextFromListElement(element,"div.product-description a");
        getWebElementHandler().shouldFillInput(value,input,driver);
        dropdownValue = getWebElementHandler().getTextFromElement(dropdownProduct,driver);
        return this;
    }

    public ResultPage shouldClickSearchButton(WebDriver driver){
        getWebElementHandler().shouldClickElement(searchButton,driver);
        return new ResultPage();
    }

    public LandingPage iterateThroughCategories(WebDriver driver) throws InterruptedException {
        for(int i = 0;i<listOfCategories.size();i++){
                String nameOfFirstPage = listOfCategories.get(i).getText();
                listOfCategories.get(i).click();
                String nameOfSecondPage = getWebElementHandler().getTextFromElement(categoryName, driver);
                getWebElementHandler().verifyIsElementDisplayed(filters, driver);
                int x = getWebElementHandler().numberOfItemsInCategory(itemList);
                int y = getWebElementHandler().getNumberFromText(numberOfProducts.getText(), 2);
                if (nameOfFirstPage.equals(nameOfSecondPage) && (x==y)){}
                driver.navigate().back();
        }
        return this;
    }

    public LandingPage iterateThroughSubCategories(WebDriver driver) throws InterruptedException {
        for(int i = 0;i<listOfSubCategories.size();i++){
            String nameOfFirstPage = listOfSubCategories.get(i).getText();
            getJse().clickElement(listOfSubCategories.get(i),driver);
            String nameOfSecondPage = getWebElementHandler().getTextFromElement(categoryName, driver);
            getWebElementHandler().verifyIsElementDisplayed(filters, driver);
            int x = getWebElementHandler().numberOfItemsInCategory(itemList);
            int y = getWebElementHandler().getNumberFromText(numberOfProducts.getText(), 2);
            if (nameOfFirstPage.equals(nameOfSecondPage) && (x==y)){}
            driver.navigate().back();
        }
        return this;
    }








}
