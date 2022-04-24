import Page.Objects.LandingPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class SearchTest extends BaseTest{

    @Test
    void searchTest() throws InterruptedException {
        LandingPage landingPage = new LandingPage(driver);
        landingPage
                .enterRandomProductNameIntoField(driver)
                        .shouldClickSearchButton(driver);
        Assertions.assertEquals(landingPage.value, driver.findElement(By.cssSelector(".product-title>a")).getText());
    }

    @Test
    void searchTestDropdown() throws InterruptedException {
        LandingPage landingPage = new LandingPage(driver);
        landingPage
                .enterRandomProductNameIntoField(driver);
        Assertions.assertEquals(landingPage.dropdownValue,driver.findElement(By.cssSelector("span.product")).getText());
    }


    @Test
    void name() throws InterruptedException {
        LandingPage landingPage = new LandingPage(driver);
        landingPage
                  .iterateThroughCategories(driver)
                  .iterateThroughSubCategories(driver);
    }
}
