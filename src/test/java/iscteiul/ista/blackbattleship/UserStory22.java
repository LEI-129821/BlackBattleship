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
 * Page Object Class para o cenário UserStory22.
 * Encapsula a navegação até à loja (Shop) e à secção de moedas virtuais (Coins).
 */
public class UserStory22 {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By cookieConsentButton = By.cssSelector(".fc-cta-consent, .fc-button.fc-cta-consent");
    private final By shopLink = By.linkText("Shop");
    private final By coinsLink = By.linkText("Coins");

    /**
     * Construtor da classe UserStory22.
     *
     * @param driver Instância do WebDriver utilizada para controlar o browser.
     */
    public UserStory22(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Abre a página inicial em resolução desktop (1550x926) e fecha o pop-up de cookies se existir.
     */
    public void openPageDesktop() {
        driver.get("https://papergames.io/en/battleship");
        driver.manage().window().setSize(new Dimension(1550, 926));
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
     * Clica na ligação "Shop" do menu principal.
     */
    public void goToShop() {
        safeClick(shopLink);
        wait.until(ExpectedConditions.urlContains("shop"));
        humanPause(1200);
    }

    /**
     * Clica na ligação "Coins" dentro da página da loja.
     */
    public void goToCoins() {
        safeClick(coinsLink);
        wait.until(ExpectedConditions.urlContains("virtual-coins"));
        humanPause(1500);
    }

    /**
     * Garante que o elemento está presente e visível no DOM e efetua o clique
     * sem ser intercetado por elementos sobrepostos.
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