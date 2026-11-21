package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de testes JUnit para o cenário UserStory22.
 * Verifica o acesso à loja e à página de compra de moedas virtuais.
 */
public class UserStory22Test {

    private WebDriver driver;
    private UserStory22 userStoryPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        userStoryPage = new UserStory22(driver);
    }

    /**
     * Testa a navegação desde a página inicial até à secção de Virtual Coins.
     */
    @Test
    public void testNavigateToShopCoins() {
        userStoryPage.openPageDesktop();
        userStoryPage.goToShop();
        assertTrue(driver.getCurrentUrl().contains("shop"), "Deve abrir a página da Loja.");

        userStoryPage.goToCoins();
        assertTrue(driver.getCurrentUrl().contains("virtual-coins"), "Deve abrir a secção de Coins.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}