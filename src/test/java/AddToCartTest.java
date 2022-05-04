import Page.Objects.AddToCartPage;
import Page.Objects.CartConsistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static Page.Objects.CartConsistence.*;

public class AddToCartTest extends BaseTest {

    @Test
    @DisplayName("Validation of adding to the cart")
    void addToCartOne(){
        AddToCartPage addToCartPage = new AddToCartPage(driver);
        for(int x=0;x<3;x++) {
            addToCartPage
                    .shouldClickRandomCategory()
                    .shouldClickRandomProduct()
                    .shouldAddToCart()
                    .downloadDataFromPopUp()
                    .shouldChooseContinueShopping();
           assertEquals(cartConsistenceList, popUpCcList);
        }
    }
}