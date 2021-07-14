package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int getCardBalance(String lastFourDigits) {
        val text = cards.findBy(text(lastFourDigits)).text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public DashboardPage transferAmountFromOneCardToAnother(Integer amount, String oneCard, String anotherCard) {
        val lastFourDigitsAnotherCard = anotherCard.substring(oneCard.length() - 4);
        cards.findBy(text(lastFourDigitsAnotherCard)).find("[data-test-id=action-deposit]").click();
        $("[data-test-id=amount] input").setValue(amount.toString());
        $("[data-test-id=from] input").setValue(oneCard);
        $("[data-test-id=action-transfer]").click();
        return new DashboardPage();
    }

    public DashboardPage resetCardsBalance() {
        val currentBalanceCard1 = getCardBalance("0001");
        val currentBalanceCard2 = getCardBalance("0002");
        if (currentBalanceCard1 > 10000) {
            val amountRequired = 10000 - currentBalanceCard1;
            transferAmountFromOneCardToAnother(amountRequired, "5559 0000 0000 0001", "5559 0000 0000 0002");
        }
        if (currentBalanceCard1 < 10000) {
            val amountRequired = 10000 - currentBalanceCard2;
            transferAmountFromOneCardToAnother(amountRequired, "5559 0000 0000 0002", "5559 0000 0000 0001");
        }
        return new DashboardPage();
    }

}
