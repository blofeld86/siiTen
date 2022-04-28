import Page.Objects.BasketPage;
import org.junit.jupiter.api.Test;

public class BasketTest extends BaseTest{

    @Test
    void basketTestOne() {
        BasketPage basketPage = new BasketPage(driver);
        basketPage
                  .shouldAddRandomProducts(5,5,false)
                  .changeQuantityOfProduct(0,5)
                  .verifyTotalValue(0,1,5)
                  .verifyExtendingProductQuantity(0,6)
                  .verifyTotalOrderValue()
                  .verifyReducingProductQuantity(0,5)
                  .verifyTotalOrderValue()
                  .removeProductAndVerify()
                  .verifyCartLastContent();
    }
}
