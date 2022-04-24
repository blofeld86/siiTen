package Page.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LandingPage extends BasePage {

    public LandingPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);}

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
        WebElement element = getRandomWebElementFromList(listOfElements,driver);
        value = getTextFromListElement(element,"div.product-description a");
        shouldFillInput(value,input,driver);
        dropdownValue = getTextFromElement(dropdownProduct,driver);
        return this;
    }

    public ResultPage shouldClickSearchButton(WebDriver driver){
        shouldClickElement(searchButton,driver);
        return new ResultPage(driver);
    }

    public LandingPage iterateThroughCategories(WebDriver driver) throws InterruptedException {
        for(int i = 0;i<listOfCategories.size();i++){
                String nameOfFirstPage = listOfCategories.get(i).getText();
                listOfCategories.get(i).click();
                String nameOfSecondPage = getTextFromElement(categoryName, driver);
                verifyIsElementDisplayed(filters, driver);
                int x = numberOfItemsInCategory(itemList);
                int y = getNumberFromText(numberOfProducts.getText(), 2);
                if (nameOfFirstPage.equals(nameOfSecondPage) && (x==y)){}
                driver.navigate().back();
        }
        return this;
    }

    public LandingPage iterateThroughSubCategories(WebDriver driver) throws InterruptedException {
        for(int i = 0;i<listOfSubCategories.size();i++){
            String nameOfFirstPage = listOfSubCategories.get(i).getText();
            jse.executeScript("arguments[0].click();",listOfSubCategories.get(i));
            String nameOfSecondPage = getTextFromElement(categoryName, driver);
            verifyIsElementDisplayed(filters, driver);
            int x = numberOfItemsInCategory(itemList);
            int y = getNumberFromText(numberOfProducts.getText(), 2);
            if (nameOfFirstPage.equals(nameOfSecondPage) && (x==y)){}
            driver.navigate().back();
        }
        return this;
    }








}
