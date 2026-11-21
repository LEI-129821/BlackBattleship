package iscteiul.ista.blackbattleship.testsuite129897.pages;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Page Object Selenide para o cenário UserStory22.
 * Encapsula a navegação até à loja (Shop) e à secção de moedas virtuais (Coins).
 */
public class UserStory22Page {

    private final SelenideElement cookieConsentBtn = $(".fc-cta-consent, .fc-button.fc-cta-consent");
    private final SelenideElement shopLink = $(byLinkText("Shop"));
    private final SelenideElement coinsLink = $(byLinkText("Coins"));

    public void openPage() {
        open("https://papergames.io/en/battleship");
        acceptCookiesIfPresent();
        sleep(1000);
    }

    private void acceptCookiesIfPresent() {
        try {
            cookieConsentBtn.shouldBe(visible, Duration.ofSeconds(6)).click();
            sleep(500);
        } catch (Throwable ignored) {
        }
    }

    public void goToShop() {
        acceptCookiesIfPresent();
        shopLink.shouldBe(visible).click(ClickOptions.usingJavaScript());
        sleep(1200);
    }

    public void goToCoins() {
        coinsLink.shouldBe(visible).click(ClickOptions.usingJavaScript());
        sleep(1500);
    }
}