import Page.Objects.FiltersPage;
import org.junit.jupiter.api.Test;

public class FiltersTest extends BaseTest {


    @Test
    void sliderFirstValue(){
        FiltersPage filtersPage = new FiltersPage(driver);
        filtersPage
                .goToArtCategory()
                .selectCorrectPrice(9,10)
                .verifyDisplayedProducts()
                .clearFilter();
    }

    @Test
    void sliderSecondValue(){
        FiltersPage filtersPage = new FiltersPage(driver);
        filtersPage
                .goToArtCategory()
                .selectCorrectPrice(28,29)
                .verifyDisplayedProducts()
                .clearFilter();
    }

    @Test
    void sliderThirdValue(){
        FiltersPage filtersPage = new FiltersPage(driver);
        filtersPage
                .goToArtCategory()
                .selectCorrectPrice(10,28)
                .verifyDisplayedProducts()
                .clearFilter();
    }
}
