import Page.Objects.PricesPage;
import Page.Objects.ProductPage;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PricesTest extends BaseTest{

    @Test
    void pricesTesOne() throws InterruptedException {
        PricesPage pricesPage = new PricesPage(driver);
        ProductPage productPage = new ProductPage(driver);
        pricesPage
                  .shouldClickPricesDrop()
                  .verifyDisplayedObjects()
                  .verifyPresenceOfDiscounts()
                  .verifyDisplayOfBothPrices()
                  .verifyCorrectnessOfDiscount()
                  .shouldOpenRandomProduct()
                  .shouldUploadCorrectnessOfDiscount();

        for (boolean value : pricesPage.getAreProductsDisplayedList()) {Assertions.assertTrue(value);}
        for (boolean value : pricesPage.getDiscountProductsList()) {Assertions.assertTrue(value);}
        for (boolean value : pricesPage.getRegularPriceList()) {Assertions.assertTrue(value);}
        for (boolean value : pricesPage.getDiscountedPriceList()) {Assertions.assertTrue(value);}
        for (boolean value : pricesPage.getCorrectnessOfDiscountList()) {Assertions.assertTrue(value);}
        Assertions.assertTrue(productPage.getSave20percentLabel().isDisplayed());
        Assertions.assertTrue(productPage.getOriginalPrice().isDisplayed());
        Assertions.assertTrue(productPage.getActualPrice().isDisplayed());
        for (boolean value : productPage.getPriceCorrectnessList()) {Assertions.assertTrue(value);}


        Thread.sleep(4000);
    }
}
