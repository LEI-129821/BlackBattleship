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

public class UserStory22SelenideTest {

    private UserStory22Page page;

    @BeforeAll
    public static void setupAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "1550x926";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        page = new UserStory22Page();
    }

    @Test
    @Description("Valida o acesso à loja (Shop) e à secção de moedas virtuais (Coins).")
    public void testNavigateToShopCoins() {
        page.openPage();
        page.goToShop();
        assertTrue(url().contains("shop"), "Deve abrir a página da Loja.");

        page.goToCoins();
        assertTrue(url().contains("virtual-coins"), "Deve abrir a secção de Coins.");
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }
}