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
 * Page Object Class para o cenário UserStory3.
 * Encapsula os localizadores e as interações de navegação entre jogos
 * (Chess, Connect 4, Tic Tac Toe) através do menu lateral da plataforma Papergames.
 */
public class UserStory3 {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cookieConsentButton = By.cssSelector(".fc-cta-consent, .fc-button.fc-cta-consent");
    private final By menuToggleButton = By.cssSelector(".flex-grow-1 > .mdc-icon-button > .mat-mdc-button-touch-target");
    private final By chessLink = By.linkText("Chess");
    private final By connect4Link = By.linkText("Connect 4");
    private final By ticTacToeLink = By.linkText("Tic Tac Toe");

    /**
     * Construtor da classe UserStory3.
     *
     * @param driver Instância do WebDriver utilizada para controlar o browser.
     */
    public UserStory3(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Abre a página inicial do Battleship, fecha o pop-up de cookies se existir
     * e ajusta a dimensão da janela.
     */
    public void openPageAndResize() {
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
     * Abre o menu de navegação lateral (modo responsivo/mobile).
     */
    public void openSideMenu() {
        WebElement toggle = wait.until(ExpectedConditions.elementToBeClickable(menuToggleButton));
        toggle.click();
        humanPause(1200);
    }

    /**
     * Seleciona o jogo Chess no menu.
     */
    public void navigateToChess() {
        safeClick(chessLink);
        wait.until(ExpectedConditions.urlContains("chess"));
        humanPause(1200);
    }

    /**
     * Seleciona o jogo Connect 4 no menu lateral.
     */
    public void navigateToConnect4() {
        safeClick(connect4Link);
        wait.until(ExpectedConditions.urlContains("connect"));
        humanPause(1200);
    }

    /**
     * Seleciona o jogo Tic Tac Toe no menu lateral.
     */
    public void navigateToTicTacToe() {
        safeClick(ticTacToeLink);
        wait.until(ExpectedConditions.urlContains("tic-tac-toe"));
        humanPause(1200);
    }

    /**
     * Garante que o elemento está presente e visível no DOM e efetua o clique
     * sem ser intercetado por banners de publicidade (iframes) em ecrãs pequenos.
     *
     * @param locator Localizador do elemento a clicar.
     */
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