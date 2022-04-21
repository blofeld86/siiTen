package Page.bjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class StorePage {

    @FindBy(css = ".category-top-menu a.h6")
    WebElement clothes;
    @FindBy(css= ".category-sub-menu a:nth-child(1)")
    WebElement men;
    @FindBy(css= ".category-sub-menu a:nth-child(2)")
    WebElement woman;
    @FindBy(css = ".category-top-menu a.h6")
    WebElement accessories;
    @FindBy(css = ".category-sub-menu a:nth-child(1)")
    WebElement stationery;
    @FindBy(css = ".category-sub-menu a:nth-child(2)")
    WebElement homeAccessories;
    @FindBy(css = ".category-top-menu a.h6")
    WebElement art;




}
