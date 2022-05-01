package Page.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private List<WebElement> listOfCategories;

    @FindBy(css = ".dropdown-submenu")
    private List<WebElement> listOfSubCategories;

    @FindBy(css = ".category-top-menu a.h6")
    private WebElement categoryName;

    @FindBy(css = "#search_filters")
    private WebElement filters;

    @FindBy(css = ".product")
    private List<WebElement> itemList;

    @FindBy(css = ".total-products")
    private WebElement numberOfProducts;

    @FindBy(css = ".user-info>a")
    private WebElement signIn;

    @FindBy(css = ".no-account>a")
    private WebElement registerUser;

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


    public String getInputProductName () {
        return inputProductName;
    }
    public String getDropDownProductName () {
        return dropdownProduct.getText();
    }
    public List<String> getCategoriesListOfNamesOfFirstPage () {
        return categoriesListOfNamesOfFirstPage;
    }
    public List<String> getCategoriesListOfNamesOfSecondPage () {
        return categoriesListOfNamesOfSecondPage;
    }
    public List<String> getSubCategoriesListOfNamesOfFirstPage () {
        return subCategoriesListOfNamesOfFirstPage;
    }
    public List<String> getSubCategoriesListOfNamesOfSecondPagePage () {
        return subCategoriesListOfNamesOfSecondPage;
    }
    public List<Integer> getDisplayedListOfCategoryItems () {
        return displayedListOfCategoryItems;
    }
    public List<Integer> getSummaryListOfCategoryItems () {
        return summaryListOfCategoryItems;
    }
    public List<Integer> getDisplayedListOfSubCategoryItems () {
        return displayedListOfSubCategoryItems;
    }
    public List<Integer> getSummaryListOfSubCategoryItems () {
        return summaryListOfSubCategoryItems;
    }

    public static final Logger logger = LoggerFactory.getLogger("LandingPage.class");
    public LandingPage(WebDriver driver) {
        super(driver);
    }


    public LandingPage enterRandomProductNameIntoField () {
        WebElement element = getRandomWebElementFromList(listOfElementNames);
        inputProductName = element.getText();
        input.sendKeys(inputProductName);
        getWait().until(ExpectedConditions.visibilityOf(dropdownProduct));
        dropdownProductName = dropdownProduct.getText();
        logger.info("Successfully provided product name");
        return this;
    }

    public ProductDetails shouldClickSearchButton () {
        searchButton.click();
        logger.info("Successfully clicked search button");
        return new ProductDetails(driver);
    }

    public LandingPage iterateThroughCategories (){
        for (int i = 0; i < listOfCategories.size() - 1; i++) {
            categoriesListOfNamesOfFirstPage.add(listOfCategories.get(i).getText());
            listOfCategories.get(i).click();
            categoriesListOfNamesOfSecondPage.add(getTextFromElement(categoryName));
            displayedListOfCategoryItems.add(numberOfItemsInCategory(itemList));
            summaryListOfCategoryItems.add(getNumberFromTextDividedBySpaces(numberOfProducts.getText(), 2));
        }
        logger.info("Successfully iterated through main categories");
        return this;
    }

    public LandingPage iterateThroughSubCategories() {
        for (int i = 0; i < listOfSubCategories.size() - 1; i++) {
            subCategoriesListOfNamesOfFirstPage.add(listOfCategories.get(i).getText());
            listOfCategories.get(i).click();
            subCategoriesListOfNamesOfSecondPage.add(getTextFromElement(categoryName));
            displayedListOfSubCategoryItems.add(numberOfItemsInCategory(itemList));
            summaryListOfSubCategoryItems.add(getNumberFromTextDividedBySpaces(numberOfProducts.getText(), 2));
        }
        logger.info("Successfully iterated through subcategories");
        return this;
    }

    public CheckOutPage signOpenRegisterForm(){
        signIn.click();
        registerUser.click();
        logger.info("Successfully registered user");
        return new CheckOutPage(driver);
    }
}
