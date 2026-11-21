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
 * Page Object Selenide para o cenário UserStory3.
 * Encapsula a navegação sequencial entre jogos (Chess, Connect 4, Tic Tac Toe) pelo menu lateral.
 */
public class UserStory3Page {

    private final SelenideElement cookieConsentBtn = $(".fc-cta-consent, .fc-button.fc-cta-consent");
    private final SelenideElement menuToggleBtn = $(".flex-grow-1 > .mdc-icon-button > .mat-mdc-button-touch-target");
    private final SelenideElement chessLink = $(byLinkText("Chess"));
    private final SelenideElement connect4Link = $(byLinkText("Connect 4"));
    private final SelenideElement ticTacToeLink = $(byLinkText("Tic Tac Toe"));

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

    public void openSideMenu() {
        acceptCookiesIfPresent();
        menuToggleBtn.shouldBe(visible).click(ClickOptions.usingJavaScript());
        sleep(1000);
    }

    public void navigateToChess() {
        chessLink.scrollIntoView("{block: 'center'}").click(ClickOptions.usingJavaScript());
        sleep(1200);
    }

    public void navigateToConnect4() {
        connect4Link.scrollIntoView("{block: 'center'}").click(ClickOptions.usingJavaScript());
        sleep(1200);
    }

    public void navigateToTicTacToe() {
        ticTacToeLink.scrollIntoView("{block: 'center'}").click(ClickOptions.usingJavaScript());
        sleep(1200);
    }
}