import Page.Objects.PricesPage;
import org.junit.jupiter.api.Test;

public class PricesTest extends BaseTest{

    @Test
    void pricesTesOne() throws InterruptedException {
        PricesPage pricesPage = new PricesPage(driver);
        pricesPage
                  .shouldClickPricesDrop(driver)
                  .verifyDisplayedObjects()
                  .verifyPresenceOfDiscounts()
                  .verifyDisplayOfBothPrices()
                  .verifyCorrectnessOfDiscount()
                  .shouldOpenRandomProduct(driver)
                  .shouldDisplayDiscountLabel()
                  .shouldDisplayBothPrices()
                  .shouldVerifyCorrectnessOfDiscount();
        Thread.sleep(4000);
    }
}
