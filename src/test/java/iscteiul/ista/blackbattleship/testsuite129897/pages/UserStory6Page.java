package iscteiul.ista.blackbattleship.testsuite129897.pages;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.disappear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;

/**
 * Page Object Selenide para o cenário UserStory6.
 * Encapsula a seleção do segundo modo de jogo e a introdução do nickname.
 */
public class UserStory6Page {

    private final SelenideElement cookieConsentBtn = $(".fc-cta-consent, .fc-button.fc-cta-consent");
    private final SelenideElement secondaryPlayBtn = $(".w-100:nth-child(2) > .btn .flex-grow-1");
    private final SelenideElement nicknameInput = $("input[formcontrolname='username']");
    private final SelenideElement confirmNicknameBtn = $("mat-dialog-container button[type='submit'], mat-dialog-container app-juicy-button button, mat-dialog-container button.btn-primary");

    /**
     * Abre a página inicial do Battleship e fecha o banner de cookies se existir.
     */
    public void openPage() {
        open("https://papergames.io/en/battleship");
        acceptCookiesIfPresent();
        sleep(1000);
    }

    /**
     * Fecha o diálogo de consentimento de cookies caso esteja visível.
     */
    private void acceptCookiesIfPresent() {
        try {
            cookieConsentBtn.shouldBe(visible, Duration.ofSeconds(6)).click();
            sleep(500);
        } catch (Throwable ignored) {
        }
    }

    /**
     * Clica no segundo botão de modo de jogo.
     */
    public void clickSecondaryPlayButton() {
        acceptCookiesIfPresent();
        secondaryPlayBtn.scrollIntoView("{block: 'center'}").click(ClickOptions.usingJavaScript());
        sleep(1000);
    }

    /**
     * Preenche o nickname, confirma o diálogo e aguarda que a página final do jogo fique visível.
     *
     * @param nickname Nickname a registar.
     */
    public void enterNicknameAndConfirm(String nickname) {
        nicknameInput.shouldBe(visible).setValue(nickname);
        sleep(800);
        confirmNicknameBtn.shouldBe(visible).click(ClickOptions.usingJavaScript());
        nicknameInput.should(disappear, Duration.ofSeconds(8));
        sleep(5000);
    }
}