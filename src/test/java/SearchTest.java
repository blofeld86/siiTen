import Page.Objects.LandingPage;
import Page.Objects.ProductDetails;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchTest extends BaseTest{

    @Test
    @DisplayName("Validation of searching the product")
    void searchTest(){
        LandingPage landingPage = new LandingPage(driver);
        ProductDetails productDetails = landingPage
                   .enterRandomProductNameIntoField()
                   .shouldClickSearchButton();
        assertEquals(landingPage.getInputProductName(),productDetails.getProductName());
    }

    @Test
    @DisplayName("Validation of the correctness of the search input field")
    void searchTestDropdown(){
        LandingPage landingPage = new LandingPage(driver);
        landingPage
                .enterRandomProductNameIntoField();
        assertEquals(landingPage.getInputProductName(),landingPage.getDropDownProductName());
    }

}
