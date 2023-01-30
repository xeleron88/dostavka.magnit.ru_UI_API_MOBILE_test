package ru.magnit.dostavka.tests.web;

import config.Project;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import ru.magnit.dostavka.tests.web.domain.HeaderSectionLinks;

@Tag("Web")
@Epic("Web")
@Feature("MainPage")
@Owner("xeleron88")

public class MainPageTests extends WebTestBase {

    @Test
    @DisplayName("Overview page is open by click on magnit logo")
    void checkClickOnLogo() {
        mainPage.clickOnMagnitLogo()
                .checkPageIsOpen(Project.config.baseUrl() + "/");
    }


    @ParameterizedTest(name = "{arguments}")
    @EnumSource(HeaderSectionLinks.class)
    @DisplayName("Ability to switch to each bottom header section links:")
    void checkHeaderTabsOpening(HeaderSectionLinks headerSectionLinks) {
        Allure.getLifecycle().updateTestCase(test ->
                test.setName("Ability to switch to each bottom header section links: " + headerSectionLinks.toString()));

        mainPage.openHeaderCatItem(headerSectionLinks)
                .checkPageIsOpen(headerSectionLinks.getUrl());
    }

    @Test
    @DisplayName("Verify correct phone number")
    void checkPhoneNumber() {
        mainPage.checkPhoneNumber("8 800 707-68-88");
    }

}
