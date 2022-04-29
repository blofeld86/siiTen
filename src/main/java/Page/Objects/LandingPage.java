package Page.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
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

    private String inputProductName = null;
    private String dropdownProductName = null;

    private List<String> categoriesListOfNamesOfFirstPage = new ArrayList<>();
    private List<String> categoriesListOfNamesOfSecondPage = new ArrayList<>();
    private List<String> subCategoriesListOfNamesOfFirstPage = new ArrayList<>();
    private List<String> subCategoriesListOfNamesOfSecondPage = new ArrayList<>();
    private List<Integer> displayedListOfCategoryItems = new ArrayList<>();
    private List<Integer> summaryListOfCategoryItems = new ArrayList<>();
    private List<Integer> displayedListOfSubCategoryItems = new ArrayList<>();
    private List<Integer> summaryListOfSubCategoryItems = new ArrayList<>();



    public String getInputProductName(){return inputProductName;}
    public String getDropDownProductName(){return dropdownProduct.getText();}
    public List<String> getCategoriesListOfNamesOfFirstPage(){return categoriesListOfNamesOfFirstPage;}
    public List<String> getCategoriesListOfNamesOfSecondPage(){return categoriesListOfNamesOfSecondPage;}
    public List<String> getSubCategoriesListOfNamesOfFirstPage(){return subCategoriesListOfNamesOfFirstPage;}
    public List<String> getSubCategoriesListOfNamesOfSecondPagePage(){return subCategoriesListOfNamesOfSecondPage;}
    public List<Integer> getDisplayedListOfCategoryItems(){return displayedListOfCategoryItems;}
    public List<Integer> getSummaryListOfCategoryItems(){return summaryListOfCategoryItems;}
    public List<Integer> getDisplayedListOfSubCategoryItems(){return displayedListOfSubCategoryItems;}
    public List<Integer> getSummaryListOfSubCategoryItems(){return summaryListOfSubCategoryItems;}

    public LandingPage(WebDriver driver){ super(driver);}


    public LandingPage enterRandomProductNameIntoField(){
        WebElement element = getRandomWebElementFromList(listOfElementNames);
        inputProductName = element.getText();
        input.sendKeys(inputProductName);
        getWait().until(ExpectedConditions.visibilityOf(dropdownProduct));
        dropdownProductName = dropdownProduct.getText();
        return this;
    }

    public ProductDetails shouldClickSearchButton(){
        searchButton.click();
        return new ProductDetails(driver);
    }

    public LandingPage iterateThroughCategories() throws InterruptedException {
        for(int i = 0;i<listOfCategories.size()-1;i++){
                categoriesListOfNamesOfFirstPage.add(listOfCategories.get(i).getText());
                listOfCategories.get(i).click();
                categoriesListOfNamesOfSecondPage.add(getTextFromElement(categoryName, driver));
                displayedListOfCategoryItems.add(numberOfItemsInCategory(itemList));
                summaryListOfCategoryItems.add(getNumberFromText(numberOfProducts.getText(), 2));
                driver.navigate().back();
        }
        return this;
    }

    public LandingPage iterateThroughSubCategories() throws InterruptedException {
        for(int i = 0;i<listOfSubCategories.size()-1;i++){
            subCategoriesListOfNamesOfFirstPage.add(listOfCategories.get(i).getText());
            listOfCategories.get(i).click();
            subCategoriesListOfNamesOfSecondPage.add(getTextFromElement(categoryName, driver));
            verifyIsElementDisplayed(filters, driver);
            displayedListOfSubCategoryItems.add(numberOfItemsInCategory(itemList));
            summaryListOfSubCategoryItems.add(getNumberFromText(numberOfProducts.getText(), 2));
            driver.navigate().back();
        }
        return this;
    }








}
