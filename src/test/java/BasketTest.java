import Page.Objects.BasketPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static Page.Objects.CartConsistence.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasketTest extends BaseTest{

    @Test
    void basketTestOne() throws InterruptedException {
        BasketPage basketPage = new BasketPage(driver);
        basketPage
                  .shouldAddRandomProducts(5,5,false)
                  .shouldGoToCart();
        assertEquals(cartConsistenceList,addedProductsList);
        assertEquals(basketPage.getDisplayedTotalPrice(),basketPage.getTotalPrice());
        basketPage
                  .changeQuantityOfProduct(0,5);
        assertEquals(basketPage.getDisplayedTotalPrice(),basketPage.getTotalPrice());
        basketPage
                  .increaseProductQuantity(0,6);
        assertEquals(basketPage.getDisplayedTotalPrice(),basketPage.getTotalPrice());
        basketPage
                  .reduceProductQuantity(0,5);
        for ( WebElement e: basketPage.getTrash()) {
            e.click();
            assertEquals(basketPage.getDisplayedTotalPrice(),basketPage.getTotalPrice());
        }
    }
}
