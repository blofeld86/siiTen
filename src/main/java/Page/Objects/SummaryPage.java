package Page.Objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SummaryPage extends BasePage{

    public SummaryPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".col-sm-4>span")
    List<WebElement> productName;

    @FindBy(css = "#order-items .text-xs-left")
    List<WebElement> productPrice;

    @FindBy(css = ".col-sm-6 :nth-child(2).text-sm-center")
    List<WebElement> productQuantity;

    public SummaryPage shouldVerifyOrder(){
        for (int i =0;i<productName.size();i++){
            if(productName.get(i).equals(CartConsistence.cartConsistenceList.get(i).getName()));
            if(getFullPriceFromString(productPrice.get(i).getText()) == CartConsistence.cartConsistenceList.get(i).getPrice());
            if(getNumberFromString(productQuantity.get(i).getText(),0) == CartConsistence.cartConsistenceList.get(i).getQuantity());
        }
        return this;
    }

}
