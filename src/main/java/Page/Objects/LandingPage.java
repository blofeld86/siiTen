package Page.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class LandingPage extends BasePage {

    @FindBy(css = ".product-title>a")
    private List<WebElement> listOfElementNames;

    @FindBy(css = "input[type='text']")
    private WebElement input;

    @FindBy(css = "form>[type='submit']>i")
    private WebElement searchButton;

    @FindBy(css = "span.product")
    private WebElement dropdownProduct;

    @FindBy(css = "#_desktop_top_menu>ul>li>a")
    private List <WebElement> listOfCategories;

    @FindBy(css = ".dropdown-submenu")
    private List<WebElement> listOfSubCategories;

    @FindBy(css = ".category-top-menu a.h6")
    private WebElement categoryName;

    @FindBy(css = "#search_filters")
    private WebElement filters;

    @FindBy(css = ".product")
    private List <WebElement> itemList;

    @FindBy(css = ".total-products")
    private WebElement numberOfProducts;

    public LandingPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    private String inputProductName = null;
    private String dropdownProductName = null;
    public String getInputProductName(){return inputProductName;}
    public String getDropDownProductName(){return dropdownProduct.getText();}

    public LandingPage enterRandomProductNameIntoField(){
        WebElement element = getRandomWebElementFromList(listOfElementNames,driver);
        inputProductName = element.getText();
        input.sendKeys(inputProductName);
        wait.until(ExpectedConditions.visibilityOf(dropdownProduct));
        dropdownProductName = dropdownProduct.getText();
        return this;
    }

    public ProductDetails shouldClickSearchButton(){
        searchButton.click();
        return new ProductDetails(driver);
    }

    public LandingPage iterateThroughCategories() throws InterruptedException {
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

    public LandingPage iterateThroughSubCategories() throws InterruptedException {
        for(int i = 0;i<listOfSubCategories.size();i++){
            String nameOfFirstPage = listOfSubCategories.get(i).getText();
            jse.executeScript("arguments[0].click();",listOfSubCategories.get(i));
            actions.moveToElement(listOfCategories.get(i)).click().perform();
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
