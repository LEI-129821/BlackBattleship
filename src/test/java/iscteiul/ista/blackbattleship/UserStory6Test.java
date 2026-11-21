package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de testes JUnit para o cenário UserStory6.
 * Valida a abertura do pedido de nickname e a entrada na sala do segundo modo de jogo.
 */
public class UserStory6Test {

    private WebDriver driver;
    private UserStory6 userStoryPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        userStoryPage = new UserStory6(driver);
    }

    /**
     * Testa a seleção do segundo modo de jogo e a submissão do nickname.
     */
    @Test
    public void testClickSecondaryGameButtonAndEnterNickname() {
        userStoryPage.openPage();
        userStoryPage.clickSecondaryPlayButton();
        assertTrue(userStoryPage.isNicknamePromptVisible(),
                "Deve pedir o nickname ao jogador não autenticado.");

        userStoryPage.enterNicknameAndConfirm("PlayerISCTE129897");
        assertTrue(userStoryPage.isNicknamePromptClosed(),
                "Após confirmar o nickname, o diálogo deve fechar e avançar para a sala de jogo.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}