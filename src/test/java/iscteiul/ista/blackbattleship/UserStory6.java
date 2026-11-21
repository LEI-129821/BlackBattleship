package iscteiul.ista.blackbattleship;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Page Object Class para o cenário UserStory6.
 * Encapsula a seleção do segundo botão de modo de jogo e o preenchimento do nickname.
 */
public class UserStory6 {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cookieConsentButton = By.cssSelector(".fc-cta-consent, .fc-button.fc-cta-consent");
    private final By secondaryActionButton = By.cssSelector(".w-100:nth-child(2) > .btn .flex-grow-1");
    private final By nicknameInput = By.cssSelector("mat-dialog-container input, input[type='text']");
    private final By confirmNicknameButton = By.cssSelector("mat-dialog-container button[type='submit'], mat-dialog-container app-juicy-button button, mat-dialog-container button.btn-primary");

    /**
     * Construtor da classe UserStory6.
     *
     * @param driver Instância do WebDriver utilizada para controlar o browser.
     */
    public UserStory6(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Abre a página inicial do Battleship, fecha o pop-up de cookies se existir
     * e ajusta a dimensão da janela.
     */
    public void openPage() {
        driver.get("https://papergames.io/en/battleship");
        driver.manage().window().setSize(new Dimension(550, 671));
        acceptCookiesIfPresent();
        humanPause(1000);
    }

    /**
     * Fecha o diálogo de consentimento de cookies caso esteja visível.
     */
    private void acceptCookiesIfPresent() {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement consentBtn = shortWait.until(ExpectedConditions.elementToBeClickable(cookieConsentButton));
            consentBtn.click();
            humanPause(500);
        } catch (Exception ignored) {
            // O pop-up não apareceu nesta sessão
        }
    }

    /**
     * Clica no segundo botão de modo de jogo na página inicial.
     */
    public void clickSecondaryPlayButton() {
        safeClick(secondaryActionButton);
        humanPause(1000);
    }

    /**
     * Verifica se o formulário de pedido de nickname está visível no ecrã.
     *
     * @return true se o campo de nickname estiver visível.
     */
    public boolean isNicknamePromptVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(nicknameInput)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Preenche o nickname pedido e clica no botão de confirmação do diálogo.
     *
     * @param nickname Nome de utilizador a utilizar na partida.
     */
    public void enterNicknameAndConfirm(String nickname) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(nicknameInput));
        input.clear();
        input.sendKeys(nickname);
        humanPause(800);

        WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(confirmNicknameButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", confirmBtn);
        humanPause(1500);
    }

    /**
     * Verifica se o diálogo de nickname fechou após a confirmação.
     *
     * @return true se o modal de nickname já não estiver visível.
     */
    public boolean isNicknamePromptClosed() {
        try {
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(nicknameInput));
        } catch (Exception e) {
            return false;
        }
    }

    private void safeClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
        humanPause(300);
        js.executeScript("arguments[0].click();", element);
    }

    private void humanPause(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}