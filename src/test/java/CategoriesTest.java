import Page.Objects.LandingPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoriesTest extends BaseTest{


    @Test
    void name() throws InterruptedException {
        LandingPage landingPage = new LandingPage(driver);
        landingPage
                .iterateThroughCategories()
                .iterateThroughSubCategories();
        Assertions.assertEquals(landingPage.getCategoriesListOfNamesOfFirstPage(),landingPage.getCategoriesListOfNamesOfSecondPage());
        Assertions.assertEquals(landingPage.getSubCategoriesListOfNamesOfFirstPage(),landingPage.getSubCategoriesListOfNamesOfSecondPagePage());
        Assertions.assertEquals(landingPage.getDisplayedListOfCategoryItems(),landingPage.getSummaryListOfCategoryItems());
        Assertions.assertEquals(landingPage.getDisplayedListOfSubCategoryItems(),landingPage.getSummaryListOfSubCategoryItems());
    }
}
