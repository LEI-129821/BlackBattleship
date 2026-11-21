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
 * Page Object Selenide para o cenário UserStory8.
 * Encapsula a criação de partida pelo botão principal, submissão do nickname e exibição do link de convite.
 */
public class UserStory8Page {

    private final SelenideElement cookieConsentBtn = $(".fc-cta-consent, .fc-button.fc-cta-consent");
    private final SelenideElement primaryPlayBtn = $(".w-100:nth-child(1) > .btn-lg .flex-grow-1");
    private final SelenideElement nicknameInput = $("input[formcontrolname='username']");
    private final SelenideElement confirmNicknameBtn = $("mat-dialog-container button[type='submit'], mat-dialog-container app-juicy-button button, mat-dialog-container button.btn-primary");

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

    public void clickPrimaryPlayButton() {
        acceptCookiesIfPresent();
        primaryPlayBtn.scrollIntoView("{block: 'center'}").click(ClickOptions.usingJavaScript());
        sleep(1000);
    }

    public void enterNicknameAndShowInviteLink(String nickname) {
        nicknameInput.shouldBe(visible).setValue(nickname);
        sleep(800);
        confirmNicknameBtn.shouldBe(visible).click(ClickOptions.usingJavaScript());
        nicknameInput.should(disappear, Duration.ofSeconds(8));
        sleep(4000); // Pausa de 4 segundos para visualizar o link de convite no ecrã
    }
}