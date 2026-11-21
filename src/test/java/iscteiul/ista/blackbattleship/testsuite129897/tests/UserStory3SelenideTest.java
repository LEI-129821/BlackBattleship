package iscteiul.ista.blackbattleship.testsuite129897.tests;

import iscteiul.ista.blackbattleship.testsuite129897.pages.*;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserStory3SelenideTest {

    private UserStory3Page page;

    @BeforeAll
    public static void setupAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "550x671";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        page = new UserStory3Page();
    }

    @Test
    @Description("Valida a navegação sequencial entre Chess, Connect 4 e Tic Tac Toe através do menu lateral.")
    public void testNavigateBetweenGames() {
        page.openPage();
        page.openSideMenu();
        page.navigateToChess();
        assertTrue(url().contains("chess"), "Deve navegar para a página de Chess.");

        page.openSideMenu();
        page.navigateToConnect4();
        assertTrue(url().contains("connect"), "Deve navegar para a página de Connect 4.");

        page.openSideMenu();
        page.navigateToTicTacToe();
        assertTrue(url().contains("tic-tac-toe"), "Deve navegar para a página de Tic Tac Toe.");
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }
}