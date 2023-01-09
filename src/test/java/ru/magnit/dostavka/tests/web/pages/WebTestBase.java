package ru.magnit.dostavka.tests.web.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import ru.magnit.dostavka.tests.TestBase;

import static com.codeborne.selenide.Selenide.open;

public class WebTestBase extends TestBase {

    public static final MainPage mainPage = new MainPage();
    public static final ProfilePage profilePage = new ProfilePage();

    @BeforeEach
    @Override
    @Step("Open {Project.config.baseUrl()}")
    public void beforeEach() {
        open("/");
    }
}
