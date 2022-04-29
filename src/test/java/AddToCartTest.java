import Page.Objects.AddToCartPage;
import Page.Objects.CartConsistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AddToCartTest extends BaseTest {

    @Test
    void addToCartOne(){
        AddToCartPage addToCartPage = new AddToCartPage(driver);
        for(int x=0;x<3;x++) {
            addToCartPage
                    .shouldClickRandomCategory()
                    .shouldClickRandomProduct()
                    .shouldAddToCart()
                    .downloadDataFromPopUp()
                    .shouldChooseContinueShopping();
            Assertions.assertEquals(CartConsistence.cartConsistenceList, CartConsistence.popUpCcList);
        }
    }
}