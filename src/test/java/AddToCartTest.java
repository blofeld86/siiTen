import Page.Objects.AddToCartPage;
import org.junit.jupiter.api.Test;

public class AddToCartTest extends BaseTest {

    @Test
    void addToCartOne() throws InterruptedException {
        AddToCartPage addToCartPage = new AddToCartPage(driver);
        addToCartPage
                     .shouldClickRandomCategory()
                     .shouldClickRandomProduct()
                     .shouldAddToCart()
                     .verifyPopupContent()
                     .shouldChooseContinueShopping()
                     .verifyQuantityOfProductsInCart()
                        .shouldClickRandomCategory()
                        .shouldClickRandomProduct()
                        .shouldAddToCart()
                        .verifyPopupContent()
                        .shouldChooseContinueShopping()
                        .verifyQuantityOfProductsInCart()
                           .shouldClickRandomCategory()
                           .shouldClickRandomProduct()
                           .shouldAddToCart()
                           .verifyPopupContent()
                           .shouldChooseContinueShopping()
                           .verifyQuantityOfProductsInCart();
        Thread.sleep(4000);
    }
}
