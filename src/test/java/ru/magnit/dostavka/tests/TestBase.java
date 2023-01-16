package ru.magnit.dostavka.tests;

import com.codeborne.selenide.Configuration;
import config.Project;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.magnit.dostavka.drivers.BrowserstackAndroidDriver;
import ru.magnit.dostavka.drivers.LocalAndRemoteWebDriver;
import ru.magnit.dostavka.drivers.LocalAndroidDriver;
import ru.magnit.dostavka.helpers.Attachments;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;

public class TestBase {

    @BeforeAll
    public static void setup() {
        addListener("AllureSelenide", new AllureSelenide());
        selectDriver();
    }

    private static void selectDriver() {
        switch (Project.config.runIn()) {
            case "browser_selenoid":
            case "browser_local":
                LocalAndRemoteWebDriver.configure();
                break;
            case "android_browserstack":
                Configuration.browser = BrowserstackAndroidDriver.class.getName();
                break;
            case "android_emulator":
                Configuration.browser = LocalAndroidDriver.class.getName();
                break;
        }
    }

    @BeforeEach
    @Step("Open the application(or browser)")
    public void beforeEach() {
        open();
    }

    @AfterEach
    @Step("Save artifacts and close webdriver")
    public void afterEach() {
//        Attachments.screenshotAs("Last screenshot");
        Attachments.pageSource();
        attachEnvDependingTestArtifacts();
        closeWebDriver();
    }

    private void attachEnvDependingTestArtifacts() {
        String sessionId = Attachments.getSessionId();
        switch (Project.config.runIn()) {
            case "android_browserstack":
                Attachments.videoBrowserstack(sessionId);
                Attachments.browserstackFullInfoLink(sessionId);
                break;
            case "browser_selenoid":
                Attachments.videoSelenoid(sessionId);
            case "browser_local":
                if (!Project.config.browser().equals("firefox")) {
                    Attachments.browserConsoleLogs();
                }
                break;
        }
    }
}
