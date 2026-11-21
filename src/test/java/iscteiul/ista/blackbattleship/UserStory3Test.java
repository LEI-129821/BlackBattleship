package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de testes JUnit para o cenário UserStory3.
 * Valida a navegação sequencial entre diferentes jogos disponíveis no menu.
 */
public class UserStory3Test {

    private WebDriver driver;
    private UserStory3 userStoryPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        userStoryPage = new UserStory3(driver);
    }

    @Test
    public void testNavigateBetweenGames() {
        userStoryPage.openPageAndResize();
        userStoryPage.openSideMenu();
        userStoryPage.navigateToChess();
        assertTrue(driver.getCurrentUrl().contains("chess"), "Deve navegar para a página de Chess.");

        userStoryPage.openSideMenu();
        userStoryPage.navigateToConnect4();
        assertTrue(driver.getCurrentUrl().contains("connect"), "Deve navegar para a página de Connect 4.");

        userStoryPage.openSideMenu();
        userStoryPage.navigateToTicTacToe();
        assertTrue(driver.getCurrentUrl().contains("tic-tac-toe"), "Deve navegar para a página de Tic Tac Toe.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}