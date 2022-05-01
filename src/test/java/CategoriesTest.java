import Page.Objects.LandingPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoriesTest extends BaseTest{


    @Test
    void name() {
        LandingPage landingPage = new LandingPage(driver);
        landingPage
                   .iterateThroughCategories()
                   .iterateThroughSubCategories();

        assertEquals(landingPage.getCategoriesListOfNamesOfFirstPage(),landingPage.getCategoriesListOfNamesOfSecondPage());
        assertEquals(landingPage.getSubCategoriesListOfNamesOfFirstPage(),landingPage.getSubCategoriesListOfNamesOfSecondPagePage());
        assertEquals(landingPage.getDisplayedListOfCategoryItems(),landingPage.getSummaryListOfCategoryItems());
        assertEquals(landingPage.getDisplayedListOfSubCategoryItems(),landingPage.getSummaryListOfSubCategoryItems());
    }
}
