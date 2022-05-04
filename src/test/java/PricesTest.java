import Page.Objects.PricesPage;
import Page.Objects.ProductPage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PricesTest extends BaseTest{

    @Test
    @DisplayName("Validation of the correctness of the discounts")
    void pricesTesOne(){
        PricesPage pricesPage = new PricesPage(driver);
        ProductPage productPage = new ProductPage(driver);
        pricesPage
                  .shouldClickPricesDrop()
                  .fillDisplayedObjectList()
                  .fillPresenceOfDiscountList()
                  .fillBothPricesList()
                  .fillCorrectnessOfDiscountList()
                  .shouldOpenRandomProduct()
                  .shouldUploadCorrectnessOfDiscount();

        assertTrue(productPage.getSave20percentLabel().isDisplayed());
        assertTrue(productPage.getOriginalPrice().isDisplayed());
        assertTrue(productPage.getActualPrice().isDisplayed());

        for (boolean value : pricesPage.getAreProductsDisplayedList()) {assertTrue(value);}
        for (boolean value : pricesPage.getDiscountProductsList()) {assertTrue(value);}
        for (boolean value : pricesPage.getRegularPriceList()) {assertTrue(value);}
        for (boolean value : pricesPage.getDiscountedPriceList()) {assertTrue(value);}
        for (boolean value : pricesPage.getCorrectnessOfDiscountList()) {assertTrue(value);}
        for (boolean value : productPage.getPriceCorrectnessList()) {assertTrue(value);}
    }
}
