import Page.Objects.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CheckOutTest extends BaseTest{

    @Test
    void checkOutOne() throws InterruptedException {
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        ProceedOrderPage proceedOrderPage = new ProceedOrderPage(driver);
        SummaryPage summaryPage = new SummaryPage(driver);
        OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
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
                    .shouldVerifyOrder()
                    .shouldGetOrderReferencePayAndDeliveryMethod()
                    .shouldGoToOrderHistory()
                    .shouldUploadOrderData()
                    .shouldOpenDetails()
                    .shouldFillListByValues()
                    .shouldGetTheCustomerData();
        Assertions.assertEquals(proceedOrderPage.getDeliveryMethod(),summaryPage.getDeliveryResult());
        Assertions.assertEquals(proceedOrderPage.getPayMethod(),summaryPage.getPayMethodResult());
        Assertions.assertEquals(proceedOrderPage.getAddressValue(),orderHistoryPage.getDelivAddress());

        Thread.sleep(4000);
    }
}
