import Page.Objects.*;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import test.helpers.WebElementHandler;
import static Page.Objects.CartConsistence.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CheckOutTest extends BaseTest{

    @Test
    void checkOutOne() throws InterruptedException {
        LandingPage landingPage = new LandingPage(driver);
        ProceedOrderPage proceedOrderPage = new ProceedOrderPage(driver);
        SummaryPage summaryPage = new SummaryPage(driver);
        OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
        BasketPage basketPage = new BasketPage(driver);
        landingPage
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
                    .getOrderData()
                    .shouldGetOrderReferencePayAndDeliveryMethod()
                    .shouldGoToOrderHistory()
                    .shouldUploadOrderData()
                    .shouldOpenDetails()
                    .shouldFillListByValues()
                    .shouldGetTheCustomerData();

        assertEquals(orderHistoryPage.getDate().getText(), WebElementHandler.getTodayDate());
        assertEquals(basketPage.getDisplayedTotalPrice(),orderHistoryPage.getTotalPriceValue());
        assertEquals(proceedOrderPage.getAddressValue(),orderHistoryPage.getDelivAddress());
        assertEquals(proceedOrderPage.getAddressValue(),orderHistoryPage.getInvAddress());
        for ( WebElement e: proceedOrderPage.getPopUpList()) {assertTrue(e.isDisplayed());}
        for (String s : proceedOrderPage.getDeliveryList()){ assertEquals(s,summaryPage.getDeliveryResult());}
        for (String s : proceedOrderPage.getPayMethodList()){ assertEquals(s,summaryPage.getPayMethodResult());}
        for (String s : orderHistoryPage.getStatusValueList()){ assertEquals(s, "Awaiting bank wire payment");}
        for(int i=0;i<cartConsistenceList.size();i++) {
            assertTrue(summaryPage.doesStringContainsString(addedProductsList.get(i).getName(),cartConsistenceList.get(i).getName()));
            assertEquals(cartConsistenceList.get(i).getPrice(),addedProductsList.get(i).getPrice());
            assertEquals(cartConsistenceList.get(i).getQuantity(),addedProductsList.get(i).getQuantity());
        }
        for(int i =0;i<cartConsistenceList.size();i++){
            assertTrue(summaryPage.doesStringContainsString(orderHistoryList.get(i).getName(),cartConsistenceList.get(i).getName()));
            assertEquals(orderHistoryList.get(i).getPrice(),cartConsistenceList.get(i).getPrice());
            assertEquals(orderHistoryList.get(i).getQuantity(),cartConsistenceList.get(i).getQuantity());
        }
    }
}

