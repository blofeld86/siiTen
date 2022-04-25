import Page.Objects.BasketPage;
import Page.Objects.CartConsistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static Page.Objects.CartConsistence.*;

public class BasketTest extends BaseTest{

    @Test
    void basketTestOne() throws InterruptedException {
        BasketPage basketPage = new BasketPage(driver);
        basketPage
                  .shouldAddFiveRandomProducts(driver);

        System.out.println(cartConsistenceList.size());
        for (CartConsistence c : cartConsistenceList) {
            System.out.println(c);
        }
        Thread.sleep(4000);
    }
}
