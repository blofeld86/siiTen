import Page.Objects.LandingPage;
import Page.Objects.ProductDetails;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchTest extends BaseTest{

    @Test
    void searchTest(){
        LandingPage landingPage = new LandingPage(driver);
        ProductDetails productDetails = landingPage
                   .enterRandomProductNameIntoField()
                   .shouldClickSearchButton();
        assertEquals(landingPage.getInputProductName(),productDetails.getProductName());
    }

    @Test
    void searchTestDropdown(){
        LandingPage landingPage = new LandingPage(driver);
        landingPage
                .enterRandomProductNameIntoField();
        assertEquals(landingPage.getInputProductName(),landingPage.getDropDownProductName());
    }

}
