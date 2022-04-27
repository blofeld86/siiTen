import Page.Objects.LandingPage;
import Page.Objects.ProductDetails;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class SearchTest extends BaseTest{

    @Test
    void searchTest() throws InterruptedException {
        LandingPage landingPage = new LandingPage(driver);
        ProductDetails productDetails = landingPage
                   .enterRandomProductNameIntoField()
                   .shouldClickSearchButton();
        Assertions.assertEquals(landingPage.getInputProductName(),productDetails.getProductName());
    }

    @Test
    void searchTestDropdown() throws InterruptedException {
        LandingPage landingPage = new LandingPage(driver);
        landingPage
                .enterRandomProductNameIntoField();
        Assertions.assertEquals(landingPage.getInputProductName(),landingPage.getDropDownProductName());
    }


    @Test
    void name() throws InterruptedException {
        LandingPage landingPage = new LandingPage(driver);
        landingPage
                  .iterateThroughCategories()
                  .iterateThroughSubCategories();
    }
}
