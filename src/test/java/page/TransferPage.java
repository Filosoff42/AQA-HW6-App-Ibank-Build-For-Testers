package page;

import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {
    private SelenideElement heading = $("h1");

    public TransferPage() {
        heading.shouldBe(visible).shouldHave(text("Пополнение карты"));
    }

    public DashboardPage transferAmountFromThisCard(Integer amount, String thisCard) {
        $("[data-test-id=amount] input").setValue(amount.toString());
        $("[data-test-id=from] input").setValue(thisCard);
        $("[data-test-id=action-transfer]").click();
        return new DashboardPage();
    }
}
