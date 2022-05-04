import Page.Objects.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import test.helpers.WebElementHandler;
import static Page.Objects.CartConsistence.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CheckOutTest extends BaseTest{

    // Tutaj ustawiłem, żeby zmieniać shouldProvideRandomFirstAndLastName() na shouldProvideRegisteredFirstAndLastName
    // Oraz żeby zamieniać shouldFillFormByRandomUser() na  shouldFillFormByRegisteredUser()
    // Nie zrobiłem podstawiania zarejestrowanego maila - bo wtedy wyrzuca że adres już istnieje i test wyrzuca błąd


    @Test
    @DisplayName("Validation of the all order making process")
    void checkOutOne(){
        LandingPage landingPage = new LandingPage(driver);
        ProceedOrderPage proceedOrderPage = new ProceedOrderPage(driver);
        SummaryPage summaryPage = new SummaryPage(driver);
        OrderHistoryPage orderHistoryPage = new OrderHistoryPage(driver);
        BasketPage basketPage = new BasketPage(driver);
        landingPage
                    .signOpenRegisterForm()
                    .shouldChooseMale()
                    .shouldProvideRandomFirstAndLastName()
//                    .shouldProvideRegisteredFirstAndLastName()
                    .shouldProvideRandomMailAndPassword()
                    .shouldProvideBirthdate(9,11,86)
                    .shouldMarkCheckBoxes()
                    .shouldRegister()
                    .shouldAddRandomProducts(5,3,true)
                    .shouldProceedToCheckOut()
                    .shouldFillFormByRandomUser()
//                    .shouldFillFormByRegisteredUser()
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

        assertEquals(orderHistoryPage.getDate().getText(), WebElementHandler.getTodaysDate());
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

