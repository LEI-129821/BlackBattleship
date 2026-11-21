package iscteiul.ista.blackbattleship;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Classe de testes JUnit para o cenário UserStory8.
 * Valida a criação de partida e a apresentação do link de convite para partilhar com um amigo.
 */
public class UserStory8Test {

    private WebDriver driver;
    private UserStory8 userStoryPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        userStoryPage = new UserStory8(driver);
    }

    /**
     * Testa o clique no botão principal, a submissão do nickname e a exibição do link de convite.
     */
    @Test
    public void testClickPrimaryGameButtonAndShowInviteLink() {
        userStoryPage.openPage();
        userStoryPage.clickPrimaryPlayButton();
        assertTrue(userStoryPage.isNicknamePromptVisible(),
                "Deve pedir o nickname ao jogador não autenticado.");

        userStoryPage.enterNicknameAndConfirm("PlayerISCTE129897");
        assertTrue(userStoryPage.waitForInviteLinkAndPause(),
                "Após confirmar o nickname, deve apresentar o link de convite para enviar a um amigo.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}