package ru.magnit.dostavka.tests.web;

import config.App;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.magnit.dostavka.tests.web.pages.WebTestBase;

@Tag("Web")
@Epic("Web")
@Feature("Login")
@Owner("xeleron88")

public class LoginTests extends WebTestBase {

    @Test
    @DisplayName("Ability to open login Popup")
    void checkLoginPopup() {
        mainPage.clickLoginIcon()
                .checkLoginPopupIsOpen();
    }

    @Test
    @Story("Login by email")
    @DisplayName("Successful login")
    void authorization() {
        mainPage.login(App.config.login(), App.config.password())
                .clickLoginIcon();
        profilePage.checkProfileTitle("Личные данные");

    }

}
