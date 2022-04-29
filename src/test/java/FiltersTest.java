import Page.Objects.FiltersPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FiltersTest extends BaseTest {


    @Test
    void sliderFirstValue(){
        FiltersPage filtersPage = new FiltersPage(driver);
        filtersPage
                .goToArtCategory()
                .selectCorrectPrice(9,10)
                .enterValuesForVerifying()
                .clearFilter();
        for (int i=0;i<filtersPage.getBooleanList().size()-1;i++)
        { boolean value = filtersPage.getBooleanList().get(i).booleanValue();
            Assertions.assertTrue(value);
        }
    }

    @Test
    void sliderSecondValue(){
        FiltersPage filtersPage = new FiltersPage(driver);
        filtersPage
                .goToArtCategory()
                .selectCorrectPrice(28,29)
                .enterValuesForVerifying()
                .clearFilter();
        for (int i=0;i<filtersPage.getBooleanList().size()-1;i++)
        { boolean value = filtersPage.getBooleanList().get(i).booleanValue();
            Assertions.assertTrue(value);
        }
    }

    @Test
    void sliderThirdValue(){
        FiltersPage filtersPage = new FiltersPage(driver);
        filtersPage
                .goToArtCategory()
                .selectCorrectPrice(10,28)
                .enterValuesForVerifying()
                .clearFilter();
        for (int i=0;i<filtersPage.getBooleanList().size()-1;i++)
        { boolean value = filtersPage.getBooleanList().get(i).booleanValue();
            Assertions.assertTrue(value);
        }
    }
}
