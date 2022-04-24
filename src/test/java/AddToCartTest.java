import Page.Objects.AddToCartPage;
import org.junit.jupiter.api.Test;

public class AddToCartTest extends BaseTest {

    @Test
    void addToCartOne() throws InterruptedException {
        AddToCartPage addToCartPage = new AddToCartPage(driver);
        addToCartPage
                     .shouldClickRandomCategory(driver)
                     .shouldClickRandomProduct(driver)
                     .shouldAddToCart(driver)
                     .verifyPopupContent()
                     .shouldChooseContinueShopping(driver)
                     .verifyQuantityOfProductsInCart()
                        .shouldClickRandomCategory(driver)
                        .shouldClickRandomProduct(driver)
                        .shouldAddToCart(driver)
                        .verifyPopupContent()
                        .shouldChooseContinueShopping(driver)
                        .verifyQuantityOfProductsInCart()
                           .shouldClickRandomCategory(driver)
                           .shouldClickRandomProduct(driver)
                           .shouldAddToCart(driver)
                           .verifyPopupContent()
                           .shouldChooseContinueShopping(driver)
                           .verifyQuantityOfProductsInCart();
        Thread.sleep(4000);
    }
}
