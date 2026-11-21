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

public class UserStory8SelenideTest {

    private UserStory8Page page;

    @BeforeAll
    public static void setupAll() {
        Configuration.browser = "chrome";
        Configuration.browserSize = "550x671";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        page = new UserStory8Page();
    }

    @Test
    @Description("Valida a criação de partida pelo botão principal, registo de nickname e visualização do link de convite.")
    public void testPrimaryPlayAndInviteLink() {
        page.openPage();
        page.clickPrimaryPlayButton();
        page.enterNicknameAndShowInviteLink("PlayerISCTE129897");
    }

    @AfterEach
    public void tearDown() {
        closeWebDriver();
    }
}