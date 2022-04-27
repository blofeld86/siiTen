import Page.Objects.PricesPage;
import org.junit.jupiter.api.Test;

public class PricesTest extends BaseTest{

    @Test
    void pricesTesOne() throws InterruptedException {
        PricesPage pricesPage = new PricesPage(driver);
        pricesPage
                  .shouldClickPricesDrop()
                  .verifyDisplayedObjects()
                  .verifyPresenceOfDiscounts()
                  .verifyDisplayOfBothPrices()
                  .verifyCorrectnessOfDiscount()
                  .shouldOpenRandomProduct()
                  .shouldDisplayDiscountLabel()
                  .shouldDisplayBothPrices()
                  .shouldVerifyCorrectnessOfDiscount();
        Thread.sleep(4000);
    }
}
