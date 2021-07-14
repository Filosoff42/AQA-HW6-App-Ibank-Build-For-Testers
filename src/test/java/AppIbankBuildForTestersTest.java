import lombok.val;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import page.DashboardPage;
import page.DataHelper;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AppIbankBuildForTestersTest {

//    private static CardObject card1 = new CardObject("92df3f1c-a033-48e6-8390-206f6b1f56c0", "**** **** **** 0001", 10000);
//    private static CardObject card2 = new CardObject("0f3f5c2a-249e-4c3d-8287-09f7a039391d", "**** **** **** 0002", 10000);

    @BeforeEach
    void openPage() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.resetCardsBalance();
        open("http://localhost:9999");
    }


    @Test
    public void shouldTransfer5000FromCard1ToCard2() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.transferAmountFromOneCardToAnother(5000, "5559 0000 0000 0001", "5559 0000 0000 0002");
        assertEquals(15000, dashboardPage.getCardBalance("0002"));
    }

    @Test
    public void shouldTransfer10000FromCard2ToCard1() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verificationCode);
        dashboardPage.transferAmountFromOneCardToAnother(10000, "5559 0000 0000 0002", "5559 0000 0000 0001");
        assertEquals(20000, dashboardPage.getCardBalance("0001"));
    }

}
