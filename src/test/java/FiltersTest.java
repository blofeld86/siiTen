import Page.Objects.FiltersPage;
import org.junit.jupiter.api.Test;

public class FiltersTest extends BaseTest {


    @Test
    void sliderFirstValue(){
        FiltersPage filtersPage = new FiltersPage(driver);
        filtersPage
                .goToArtCategory(driver)
                .selectCorrectPrice(driver,9,10)
                .verifyDisplayedProducts(driver)
                .clearFilter(driver);
    }

    @Test
    void sliderSecondValue(){
        FiltersPage filtersPage = new FiltersPage(driver);
        filtersPage
                .goToArtCategory(driver)
                .selectCorrectPrice(driver,28,29)
                .verifyDisplayedProducts(driver)
                .clearFilter(driver);
    }

    @Test
    void sliderThirdValue(){
        FiltersPage filtersPage = new FiltersPage(driver);
        filtersPage
                .goToArtCategory(driver)
                .selectCorrectPrice(driver,10,28)
                .verifyDisplayedProducts(driver)
                .clearFilter(driver);
    }
}
