import Page.Objects.BasketPage;
import Page.Objects.CheckOutPage;
import org.junit.jupiter.api.Test;

public class CheckOutTest extends BaseTest{

    @Test
    void checkOutOne() throws InterruptedException {
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        checkOutPage
                    .signOpenRegisterForm()
                    .shouldChooseMale()
                    .shouldProvideFirstAndLastName()
                    .shouldProvideMailAndPassword()
                    .shouldProvideBirthdate(9,11,86)
                    .shouldMarkCheckBoxes()
                    .shouldRegister()
                    .shouldAddRandomProducts(5,3,true)
                    .shouldProceedToCheckOut()
                    .shouldFillForm()
                    .shouldChooseDelivery()
                    .shouldChoosePaymentOption()
                    .shouldHandleTermsPopup()
                    .shouldPlaceAnOrder()
                    .shouldVerifyOrder();

        Thread.sleep(4000);
    }
}
