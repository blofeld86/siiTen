import Page.Objects.FiltersPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FiltersTest extends BaseTest {

    // W TYM TEŚCIE PARAMETRY WGRAŁEM Z PLIKU YAML - CHOCIAŻ MOŻNA WSTAWIĆ ZWYKŁE INTY
    // PÓŹNIEJ, CHCIAŁEM POKAZAĆ ŻE DZIAŁA I TAK I TAK
    @Test
    void sliderFirstValue(){
        FiltersPage filtersPage = new FiltersPage(driver);
        filtersPage
                .goToArtCategory()
                .selectCorrectPrice(Integer.parseInt(System.getProperty("filterTestOneLowerPrice"))
                        ,Integer.parseInt(System.getProperty("filterTestOneHigherPrice")))
                .enterValuesForVerifying()
                .clearFilter();
        for (int i=0;i<filtersPage.getBooleanList().size()-1;i++)
        { boolean value = filtersPage.getBooleanList().get(i).booleanValue();
            assertTrue(value);
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
            assertTrue(value);
        }
    }

    @Test
    void sliderThirdValue(){
        FiltersPage filtersPage = new FiltersPage(driver);
        filtersPage
                .goToArtCategory()
                .selectCorrectPrice(10,Integer.parseInt(System.getProperty("filterTestThreeHigherPrice")))
                .enterValuesForVerifying()
                .clearFilter();
        for (int i=0;i<filtersPage.getBooleanList().size()-1;i++)
        { boolean value = filtersPage.getBooleanList().get(i).booleanValue();
            assertTrue(value);
        }
    }
}
